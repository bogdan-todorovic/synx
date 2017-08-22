package rs.ac.uns.ftn.web.synx.database;

import java.util.Map;

import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class MyDatabase {
	
	private static Map<String, User> users = Serializer.load("users.ser");
	
	
	public static Map<String, User> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, User> users) {
		MyDatabase.users = users;
	}
	
}
