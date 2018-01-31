package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Topic;

public interface TopicService extends CrudService<Topic, String> {
	
	List<Topic> findAllBySubforumId(String subforumId);
	Topic addComment(String topicId, String commentId);
	Topic update(Topic updatedTopic, String title);
}
