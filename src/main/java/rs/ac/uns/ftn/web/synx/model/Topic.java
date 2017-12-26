package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.Date;

import rs.ac.uns.ftn.web.synx.util.TopicType;

public class Topic implements Serializable {

	private static final long serialVersionUID = -6330537221980047310L;
	private String subforum;
	private String title;
	private TopicType topicType;
	private String author;
	private String content;
	private Date creationDate;
	private int numberOfLikes;
	private int numberOfDislikes;
	
	public Topic() {}

	public Topic(String subforum, String title, TopicType topicType, String author, String content, Date creationDate,
			int numberOfLikes, int numberOfDislikes) {
		super();
		this.subforum = subforum;
		this.title = title;
		this.topicType = topicType;
		this.author = author;
		this.content = content;
		this.creationDate = creationDate;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
	}

	public String getSubforum() {
		return subforum;
	}

	public void setSubforum(String subforum) {
		this.subforum = subforum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TopicType getTopicType() {
		return topicType;
	}

	public void setTopicType(TopicType topicType) {
		this.topicType = topicType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	
	
}
