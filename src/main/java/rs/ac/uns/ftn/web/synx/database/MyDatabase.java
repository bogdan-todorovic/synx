package rs.ac.uns.ftn.web.synx.database;

import java.util.Map;

import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.model.Message;
import rs.ac.uns.ftn.web.synx.model.Report;
import rs.ac.uns.ftn.web.synx.model.Subforum;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.model.User;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class MyDatabase {
	
	private static Map<String, User> users = Serializer.load(PathManager.USERS);
	private static Map<String, Subforum> subforums = Serializer.load(PathManager.SUBFORUMS);
	private static Map<String, Topic> topics = Serializer.load(PathManager.TOPICS);
	private static Map<String, Comment> comments = Serializer.load(PathManager.COMMENTS);
	private static Map<String, Message> messages = Serializer.load(PathManager.MESSAGES);
	private static Map<String, Report> reports = Serializer.load(PathManager.REPORTS);
	
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

	public static Map<String, Message> getMessages() {
		return messages;
	}

	public static void setMessages(Map<String, Message> messages) {
		MyDatabase.messages = messages;
	}

	public static Map<String, Report> getReports() {
		return reports;
	}

	public static void setReports(Map<String, Report> reports) {
		MyDatabase.reports = reports;
	}
	
	
}
