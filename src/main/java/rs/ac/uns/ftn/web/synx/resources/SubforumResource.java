package rs.ac.uns.ftn.web.synx.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.services.SubforumService;
import rs.ac.uns.ftn.web.synx.services.memory.SubforumServiceImpl;

@Path("/subforums")
public class SubforumResource {
	
	private SubforumService subforumService = new SubforumServiceImpl();
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSubforum(Subforum subforum) {
		Subforum createdSubforum = subforumService.create(subforum);
		
		if (createdSubforum == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.CREATED).build();
		
	}
}
