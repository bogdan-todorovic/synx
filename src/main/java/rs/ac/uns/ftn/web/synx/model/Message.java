package rs.ac.uns.ftn.web.synx.model;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 8172346389666057493L;
	private String id;
	private String sender;
	private String receiver;
	private String content;
	private boolean isSeen;
	
	public Message() {}
	
	public Message(String id, String sender, String reciever, String content, boolean isSeen) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = reciever;
		this.content = content;
		this.isSeen = isSeen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String reciever) {
		this.receiver = reciever;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}
	
	
	
}
