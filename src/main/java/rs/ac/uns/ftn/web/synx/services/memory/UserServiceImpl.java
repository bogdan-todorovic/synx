package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.services.UserService;
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
		Serializer.save("users.ser", users);
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


}
