package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.services.SubforumService;
import rs.ac.uns.ftn.web.synx.services.TopicService;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class TopicServiceImpl implements TopicService {
	
	private Map<String, Topic> topics = MyDatabase.getTopics();
	
	@Override
	public Topic findOne(String id) {
		if (topics.containsKey(id)) {
			return topics.get(id);
		}
		return null;
	}

	@Override
	public List<Topic> findAll() {
		return new ArrayList<>(topics.values());
	}

	@Override
	public Topic create(Topic entity) {
		if (topics.containsKey(entity.getTitle())) {
			return null;
		}
		// find the subforum and add topic
		// return updated subforum
		SubforumService subforumService = new SubforumServiceImpl();
		Subforum updatedSubforum = subforumService.addTopic(entity.getSubforum(), entity.getTitle());
		if (updatedSubforum == null) {
			return null;
		}
		entity.setCreationDate(new Date());
		topics.put(entity.getTitle(), entity);
		Serializer.save(PathManager.TOPICS, topics);
		return entity;
	}

	@Override
	public void remove(String id) {
		if (topics.containsKey(id)) {
			Topic topic = topics.get(id);
			// removing topic from subforum
			SubforumService subforumService = new SubforumServiceImpl();
			subforumService.removeTopic(topic.getSubforum(), topic.getTitle());
			topics.remove(id);
			Serializer.save(PathManager.TOPICS, topics);
		}

	}

	@Override
	public List<Topic> findAllBySubforumId(String subforumId) {
		List<Topic> allTopics = findAll();
		List<Topic> topicsFromSubforum = new ArrayList<>();
		for(Topic t : allTopics) {
			if (t.getSubforum().equals(subforumId)) {
				topicsFromSubforum.add(t);
			}
		}
		return topicsFromSubforum;
	}

	@Override
	public Topic addComment(String topicId, String commentId) {
		Topic topic = findOne(topicId);
		if (topic == null) {
			return null;
		}
		topic.getComments().add(commentId);
		Serializer.save(PathManager.TOPICS, topics);
		return topic;
	}

	@Override
	public Topic update(Topic updatedTopic, String title) {
		if (topics.containsKey(title)) {
			topics.remove(title);
			topics.put(title, updatedTopic);
			Serializer.save(PathManager.TOPICS, topics);
			return updatedTopic;
		}
		return null;
	}

}
