package rs.ac.uns.ftn.web.synx.services;

import rs.ac.uns.ftn.web.synx.model.User;

public interface UserService extends CrudService<User, String> {

	User authenticate(String username, String password);
	/* Returns true if role is successfully changed */
	boolean changeRole(User user, String newRole);

}
