package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.services.CommentService;
import rs.ac.uns.ftn.web.synx.services.TopicService;
import rs.ac.uns.ftn.web.synx.services.UserService;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.Serializer;
import rs.ac.uns.ftn.web.synx.util.UserRole;

public class UserServiceImpl implements UserService {
	
	private Map<String, User> users = MyDatabase.getUsers();
	
	@Override
	public User findOne(String id) {
		if (users.containsKey(id)) {
			return users.get(id);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		return new ArrayList<>(users.values());
	}

	@Override
	public User create(User entity) {
		if (users.containsKey(entity.getUsername())) {
			return null;
		}
		
		entity.setRole(UserRole.USER);
		entity.setRegistrationDate(new Date());
		
		users.put(entity.getUsername(), entity);
		Serializer.save(PathManager.USERS, users);
		return entity;
	}

	@Override
	public void remove(String id) {
		if (users.get(id) != null) {
			users.remove(id);
		}
	}
	
	@Override
	public User authenticate(String username, String password) {
		if (users.containsKey(username)) {
			User u = users.get(username);
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	@Override
	public boolean changeRole(User user, String newRole) {
		if (newRole.equals("USER") || newRole.equals("MODERATOR") || newRole.equals("ADMIN")) {
			user.setRole(UserRole.valueOf(newRole));
			Serializer.save(PathManager.USERS, users);
			return true;
		}
		return false;
	}

	@Override
	public List<User> findAllModerators() {
		List<User> moderators = new ArrayList<>();
		for (User user : users.values()) {
			if (user.getRole() == UserRole.MODERATOR) {
				moderators.add(user);
			}
		}
		return moderators;
	}

	@Override
	public User update(User updatedUser, String username) {
		if (users.containsKey(username)) {
			users.remove(username);
			users.put(username, updatedUser);
			Serializer.save(PathManager.USERS, users);
			return updatedUser;
		}
		return null;
	}

	@Override
	public void removeFollowedSubforum(String subforumId) {
		for (User user : users.values()) {
			if (user.getFollowedSubforums().contains(subforumId)) {
				user.getFollowedSubforums().remove(subforumId);
			}
		}
		Serializer.save(PathManager.USERS, users);
		
	}

	@Override
	public List<Topic> getSavedTopics(String username) {
		User user = findOne(username);
		if (user != null) {
			List<Topic> savedTopics = new ArrayList<>();
			TopicService topicService = new TopicServiceImpl();
			for (String topicId : user.getSavedTopics()) {
				Topic foundedTopic = topicService.findOne(topicId);
				if (foundedTopic != null) {
					savedTopics.add(foundedTopic);
				}
			}
			return savedTopics;
		}
		return null;
	}
	
	@Override
	public List<Comment> getSavedComments(String username) {
		User user = findOne(username);
		if (user != null) {
			List<String> savedComments = user.getSavedComments();
			List<Comment> savedCommentsObjects = new ArrayList<>();
			CommentService commentService = new CommentServiceImpl();
			
			for (String commentId : savedComments) {
				Comment foundedComment = commentService.findOne(commentId);
				if (foundedComment != null) {
					savedCommentsObjects.add(foundedComment);
				}
			}
			return savedCommentsObjects;
		}
		return null;
	}

	@Override
	public List<Topic> getUpvotedTopics(String username) {
		User user = findOne(username);
		if (user != null) {
			List<Topic> upvoted = new ArrayList<>();
			TopicService topicService = new TopicServiceImpl();
			for (String topicId : user.getLikedTopics()) {
				Topic foundedTopic = topicService.findOne(topicId);
				if (foundedTopic != null) {
					upvoted.add(foundedTopic);
				}
			}
			return upvoted;
		}
		return null;
	}

	@Override
	public List<Topic> getDownvotedTopics(String username) {
		User user = findOne(username);
		if (user != null) {
			List<Topic> downvoted = new ArrayList<>();
			TopicService topicService = new TopicServiceImpl();
			for (String topicId : user.getDislikedTopics()) {
				Topic foundedTopic = topicService.findOne(topicId);
				if (foundedTopic != null) {
					downvoted.add(foundedTopic);
				}
			}
			return downvoted;
		}
		return null;
	}


}
