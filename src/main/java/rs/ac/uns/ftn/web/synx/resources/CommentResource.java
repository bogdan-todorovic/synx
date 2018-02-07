package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.services.CommentService;
import rs.ac.uns.ftn.web.synx.services.memory.CommentServiceImpl;

@Path("/comments")
public class CommentResource {
	
	private CommentService commentService = new CommentServiceImpl();
	
	@PermitAll
	@GET
	@Path("/{topicId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCommentsByTopicId(@PathParam("topicId") String topicId) {
		List<Comment> comments = commentService.findAllByTopicId(topicId);
		return Response.ok().entity(comments).build();
	}
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComment(Comment comment, @Context UriInfo uriInfo) {
		Comment createdComment = commentService.create(comment);
		if (createdComment == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(createdComment.getId());
		return Response.created(uriBuilder.build()).entity(createdComment).build();
	}
	
	@PermitAll
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateComment(@PathParam("id") String id, Comment comment) {
		Comment updatedComment = commentService.update(id, comment);
		if (updatedComment == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(updatedComment).build();
	}
	
	@PermitAll
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeComment(@PathParam("id") String id) {
		if (commentService.findOne(id) != null) {
			commentService.remove(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
}
