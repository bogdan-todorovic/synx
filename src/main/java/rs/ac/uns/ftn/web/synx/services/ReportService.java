package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Report;

public interface ReportService extends CrudService<Report, String> {

	List<Report> getUndecreedReports();
	Report update(String id, Report report, String admin);
	
}
