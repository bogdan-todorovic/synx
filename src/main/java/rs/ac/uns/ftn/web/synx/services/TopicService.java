package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Topic;

public interface TopicService extends CrudService<Topic, String> {
	
	public List<Topic> findAllBySubforumId(String subforumId);
	public Topic addComment(String topicId, String commentId);
}
