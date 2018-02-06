package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Message;
import rs.ac.uns.ftn.web.synx.model.Report;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.services.MessageService;
import rs.ac.uns.ftn.web.synx.services.ReportService;
import rs.ac.uns.ftn.web.synx.services.SubforumService;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.ReportDecree;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class ReportServiceImpl implements ReportService {
	
	private Map<String, Report> reports = MyDatabase.getReports();
	
	@Override
	public Report findOne(String id) {
		if (reports.containsKey(id)) {
			return reports.get(id);
		}
		return null;
	}

	@Override
	public List<Report> findAll() {
		return new ArrayList<>(reports.values());
	}

	@Override
	public Report create(Report entity) {
		entity.setId(UUID.randomUUID().toString());
		entity.setCreationDate(Calendar.getInstance().getTime());
		reports.put(entity.getId(), entity);
		Serializer.save(PathManager.REPORTS, reports);
		return entity;
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Report> getUndecreedReports() {
		List<Report> all = findAll();
		List<Report> undecreed = new ArrayList<>();
		
		for (Report report : all) {
			if (report.getDecree() == null) {
				undecreed.add(report);
			}
		}
		return undecreed;
	}

	@Override
	public Report update(String id, Report report, String admin) {
		if (reports.containsKey(id)) {
			
			MessageService messageService = new MessageServiceImpl();
			Message mForReportAuthor = createMessageForReportAuthor(report.getAuthor(), admin);
			Message mForSubforumAuthor = createMessageForEntityAuthor(report.getSubforum(), admin);
			
			if (report.getDecree().equals(ReportDecree.ACCEPTED)) {
				mForReportAuthor.setContent("Your report has been accepted. " + report.getSubforum() + " subforum will be deleted.");
				mForSubforumAuthor.setContent("Report from user " + report.getAuthor() + " on " + report.getSubforum() + " subforum has been accepted. Subforum will be deleted.");
				SubforumService subforumService = new SubforumServiceImpl();
				subforumService.remove(report.getSubforum());
			}
			else if (report.getDecree().equals(ReportDecree.WARNING)) {
				mForReportAuthor.setContent("Your report for " + report.getSubforum() + " subforum has been adopted. Author of the subforum will be warned.");
				mForSubforumAuthor.setContent("Report from user " + report.getAuthor() + " on " + report.getSubforum() + " subforum has been adopted. You need to update content on your subforum.");
			}
			else if (report.getDecree().equals(ReportDecree.REJECTED)) {
				mForReportAuthor.setContent("Your report for " + report.getSubforum() + " has been rejected.");
			}
			
			messageService.create(mForReportAuthor);
			messageService.create(mForSubforumAuthor);
		}
		reports.put(id, report);
		Serializer.save(PathManager.REPORTS, reports);
		return report;
	}
	
	private Message createMessageForReportAuthor(String author, String admin) {
		Message message = new Message();
		message.setSender(admin);
		message.setReceiver(author);
		return message;
	}
	
	private Message createMessageForEntityAuthor(String subforumId, String admin) {
		Message message = new Message();
		message.setSender(admin);
		
		// finding author of the reported subforum
		SubforumService subforumService = new SubforumServiceImpl();
		Subforum subforum = subforumService.findOne(subforumId);
		message.setReceiver(subforum.getLeadModerator());
		return message;
	}

}
