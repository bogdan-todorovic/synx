package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.services.SubforumService;
import rs.ac.uns.ftn.web.synx.services.memory.SubforumServiceImpl;

@Path("/subforums")
public class SubforumResource {

	private SubforumService subforumService = new SubforumServiceImpl();

	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSubforums() {
		List<Subforum> subforums = subforumService.findAll();
		return Response.status(Response.Status.OK).entity(subforums).build();
	}

	@PermitAll
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubforum(@PathParam("id") String id) {
		Subforum foundedSubforum = subforumService.findOne(id);
		if (foundedSubforum == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).entity(foundedSubforum).build();
	}

	@RolesAllowed({"MODERATOR", "ADMIN"})
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createSubforum(Subforum subforum) {
		if(subforum.getTitle() == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Subforum createdSubforum = subforumService.create(subforum);
		if (createdSubforum == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.CREATED).entity(createdSubforum).build();
	}

	@PermitAll
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteSubforum(@PathParam("id") String id) {
		Subforum foundedSubforum = subforumService.findOne(id);
		if (foundedSubforum == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		subforumService.remove(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
