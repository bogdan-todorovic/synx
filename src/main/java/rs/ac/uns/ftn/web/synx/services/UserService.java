package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.User;

public interface UserService extends CrudService<User, String> {

	User authenticate(String username, String password);
	
	/* Returns true if role is successfully changed otherwise false */
	boolean changeRole(User user, String newRole);
	
	List<User> findAllModerators();
	User update(User updatedUser, String username);
	void removeFollowedSubforum(String subforumId);
}
