package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.web.synx.model.Report;
import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.services.ReportService;
import rs.ac.uns.ftn.web.synx.services.memory.ReportServiceImpl;
import rs.ac.uns.ftn.web.synx.util.TokenGenerator;

@Path("/reports")
public class ReportResource {
	
	private ReportService reportService = new ReportServiceImpl();
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Report> reports = reportService.findAll();
		return Response.ok(reports).build();
	}
	
	@PermitAll
	@GET
	@Path("/admin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUndecreedReports() {
		List<Report> undecreed = reportService.getUndecreedReports();
		return Response.ok(undecreed).build();
	}
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportForum(Report report) {
		Report createdReport = reportService.create(report);
		return Response.status(Response.Status.CREATED).entity(createdReport).build();
	}
	
	@PermitAll
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, Report report, @HeaderParam("x-auth-token")String token) {
		TokenGenerator t = new TokenGenerator();
		User user = t.parseUserFromToken(token);
		
		Report updatedReport = reportService.update(id, report, user.getUsername());
		if (updatedReport == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(updatedReport).build();
	}
	
	
}
