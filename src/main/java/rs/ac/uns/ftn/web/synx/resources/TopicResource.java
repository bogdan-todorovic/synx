package rs.ac.uns.ftn.web.synx.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.services.TopicService;
import rs.ac.uns.ftn.web.synx.services.memory.TopicServiceImpl;

@Path("/topics")
public class TopicResource {
	
	private TopicService topicService = new TopicServiceImpl();
	
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTopics() {
		List<Topic> topics = topicService.findAll();
		return Response.ok(topics).build();
	}
	
	@PermitAll
	@GET
	@Path("/subforum/{subforumId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTopicsBySubforum(@PathParam("subforumId") String subforumId) {
		List<Topic> topics = topicService.findAllBySubforumId(subforumId);
		return Response.ok(topics).build();
	}
	
	@PermitAll
	@GET
	@Path("/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopic(@PathParam("title") String title) {
		Topic topic = topicService.findOne(title);
		if (topic == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(topic).build();
	}
	
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTopic(Topic topic, @Context UriInfo uriInfo) {
		Topic createdTopic = topicService.create(topic);
		if (createdTopic == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(createdTopic.getTitle());
		return Response.created(uriBuilder.build()).entity(createdTopic).build();
	
	}
}
