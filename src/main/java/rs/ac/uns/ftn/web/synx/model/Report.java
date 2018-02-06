package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;
import java.util.Date;

import rs.ac.uns.ftn.web.synx.util.ReportDecree;

public class Report implements Serializable {

	private static final long serialVersionUID = 3141571559237090248L;
	private String id;
	private String content;
	private Date creationDate;
	private String author;
	private String subforum;
	private String topic;
	private String comment;
	private ReportDecree decree;
	
	public Report() {}
	
	public Report(String content, Date creationDate, String author, String subforum, String topic, String comment,
			ReportDecree decree) {
		super();
		this.content = content;
		this.creationDate = creationDate;
		this.author = author;
		this.subforum = subforum;
		this.topic = topic;
		this.comment = comment;
		this.decree = decree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubforum() {
		return subforum;
	}

	public void setSubforum(String subforum) {
		this.subforum = subforum;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ReportDecree getDecree() {
		return decree;
	}

	public void setDecree(ReportDecree decree) {
		this.decree = decree;
	}
}
