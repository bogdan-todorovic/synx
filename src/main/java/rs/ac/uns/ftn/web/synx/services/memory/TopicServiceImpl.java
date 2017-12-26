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
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class TopicServiceImpl implements TopicService {
	
	private Map<String, Topic> topics = MyDatabase.getTopics();
	private SubforumService subforumService = new SubforumServiceImpl();
	
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
		Subforum updatedSubforum = subforumService.addTopic(entity.getSubforum(), entity.getTitle());
		if (updatedSubforum == null) {
			return null;
		}
		entity.setCreationDate(new Date());
		topics.put(entity.getTitle(), entity);
		Serializer.save("topics.ser", topics);
		return entity;
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

}
