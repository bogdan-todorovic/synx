package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import rs.ac.uns.ftn.web.synx.util.TokenGenerator;

@Path("/users")

public class UserResource {
	
	private UserService userService = new UserServiceImpl();
	
	@PermitAll
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		
		User createdUser = userService.create(user);
		if (createdUser == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		else {
			return Response.status(Response.Status.CREATED).build();
		}
				
	}
	
	@PermitAll
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(User user) {
		
		if(user == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
		if(authenticatedUser == null) {
			return Response.status(Response.Status.FORBIDDEN).entity("Incorrect username or password").build();
		}
		
		TokenGenerator tokenGen = new TokenGenerator();
		String token = tokenGen.issueToken(authenticatedUser);
		return Response.status(Response.Status.OK).header("x-auth-token", token).entity(authenticatedUser).build();
		
	}
	
	@RolesAllowed("USER")
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		List<User> users = userService.findAll();
		return Response.status(Response.Status.OK).entity(users).build();
	}
}
