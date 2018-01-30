package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.services.SubforumService;
import rs.ac.uns.ftn.web.synx.services.TopicService;
import rs.ac.uns.ftn.web.synx.services.UserService;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class SubforumServiceImpl implements SubforumService {

	private Map<String, Subforum> subforums = MyDatabase.getSubforums(); 
	
	@Override
	public List<Subforum> findAll() {
		return new ArrayList<>(subforums.values());
	}
	
	@Override
	public Subforum findOne(String id) {
		if (subforums.containsKey(id)) {
			return subforums.get(id);
		}
		return null;
	}

	@Override
	public Subforum create(Subforum entity) {
		if (subforums.containsKey(entity.getTitle())) {
			return null;
		}
		
		subforums.put(entity.getTitle(), entity);
		Serializer.save("subforums.ser", subforums);
		return entity;
	}

	@Override
	public void remove(String id) {
		if (subforums.containsKey(id)) {
			Subforum subforum = subforums.get(id);
			TopicService topicService = new TopicServiceImpl();
			for (String topicId : subforum.getTopics()) {
				topicService.remove(topicId);
			}
			UserService userService = new UserServiceImpl();
			userService.removeFollowedSubforum(id);
			subforums.remove(id);
			Serializer.save("subforums.ser", subforums);
		}
	}

	@Override
	public Subforum update(String id, Subforum newSubforum) {
		remove(id);
		Subforum updatedSubforum = create(newSubforum);
		Serializer.save("subforums.ser", subforums);
		return updatedSubforum;
	}

	@Override
	public Subforum addTopic(String subforumId, String topicId) {
		Subforum subforum = findOne(subforumId);
		if (subforum == null) {
			return null;
		}
		subforum.getTopics().add(topicId);
		Serializer.save("subforums.ser", subforums);
		return subforum;
	}

}
