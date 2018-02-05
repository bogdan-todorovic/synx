package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.web.synx.model.Message;
import rs.ac.uns.ftn.web.synx.services.MessageService;
import rs.ac.uns.ftn.web.synx.services.memory.MessageServiceImpl;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageServiceImpl();
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Message> messages = messageService.findAll();
		return Response.ok(messages).build();
	}
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createMessage(Message message) {
		Message createdMessage = messageService.create(message);
		if (createdMessage == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PermitAll
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessagesByReceiver(@PathParam("username") String username) {
		Map<String, List<Message>> messages = messageService.getMessagesByReceiver(username);
		return Response.ok(messages).build();
	}
}
