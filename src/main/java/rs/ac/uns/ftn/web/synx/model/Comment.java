package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {

	private static final long serialVersionUID = 4175130516220133218L;
	private String id;
	private String topic;
	private String author;
	private Date creationDate;
	private String parentComment;
	private List<String> subcomments = new ArrayList<>();
	private String content;
	private int numberOfLikes;
	private int numberOfDislikes;
	private boolean isEdited;
	
	public Comment() {}
	
	public Comment(String id, String topic, String author, Date creationDate, String parentComment,
			List<String> subcomments, String content, int numberOfLikes, int numberOfDislikes, boolean isEdited) {
		super();
		this.id = id;
		this.topic = topic;
		this.author = author;
		this.creationDate = creationDate;
		this.parentComment = parentComment;
		this.subcomments = subcomments;
		this.content = content;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.isEdited = isEdited;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getParentComment() {
		return parentComment;
	}

	public void setParentComment(String parentComment) {
		this.parentComment = parentComment;
	}

	public List<String> getSubcomments() {
		return subcomments;
	}

	public void setSubcomments(List<String> subcomments) {
		this.subcomments = subcomments;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
}
