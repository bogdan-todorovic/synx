package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.model.User;

public interface UserService extends CrudService<User, String> {

	User authenticate(String username, String password);
	
	/* Returns true if role is successfully changed otherwise false */
	boolean changeRole(User user, String newRole);
	
	List<User> findAllModerators();
	User update(User updatedUser, String username);
	void removeFollowedSubforum(String subforumId);
	List<Topic> getSavedTopics(String username);
	List<Comment> getSavedComments(String username);
	List<Topic> getUpvotedTopics(String username);
	List<Topic> getDownvotedTopics(String username);
	
}
