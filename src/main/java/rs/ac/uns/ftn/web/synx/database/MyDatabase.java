package rs.ac.uns.ftn.web.synx.database;

import java.util.Map;

import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class MyDatabase {
	
	private static Map<String, User> users = Serializer.load("users.ser");
	private static Map<String, Subforum> subforums = Serializer.load("subforums.ser");
	private static Map<String, Topic> topics = Serializer.load("topics.ser");
	private static Map<String, Comment> comments = Serializer.load("comments.ser");
	
	public static Map<String, User> getUsers() {
		return users;
	}

	public static void setUsers(Map<String, User> users) {
		MyDatabase.users = users;
	}

	public static Map<String, Subforum> getSubforums() {
		return subforums;
	}

	public static void setSubforums(Map<String, Subforum> subforums) {
		MyDatabase.subforums = subforums;
	}
	
	public static Map<String, Topic> getTopics() {
		return topics;
	}

	public static void setTopics(Map<String, Topic> topics) {
		MyDatabase.topics = topics;
	}

	public static Map<String, Comment> getComments() {
		return comments;
	}

	public static void setComments(Map<String, Comment> comments) {
		MyDatabase.comments = comments;
	}
	
	
}
