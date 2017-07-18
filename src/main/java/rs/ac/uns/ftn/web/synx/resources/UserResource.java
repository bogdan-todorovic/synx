package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.services.UserService;
import rs.ac.uns.ftn.web.synx.services.memory.UserServiceImpl;

@Path("/users")

public class UserResource {
	
	private UserService userService = new UserServiceImpl();
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create() {
		return null;
	}
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		List<User> users = userService.findAll();
		return Response.status(Response.Status.OK).entity(users).build();
	}
}
