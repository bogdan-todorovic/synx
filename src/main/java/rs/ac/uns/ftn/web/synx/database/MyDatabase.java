package rs.ac.uns.ftn.web.synx.database;

import java.util.HashMap;
import java.util.Map;

import rs.ac.uns.ftn.web.synx.model.User;

public class MyDatabase {
	
	private static Map<String, User> users = new HashMap<>();
	
	
	public static Map<String, User> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, User> users) {
		MyDatabase.users = users;
	}
	
}
