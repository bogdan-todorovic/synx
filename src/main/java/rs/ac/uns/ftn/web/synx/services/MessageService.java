package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Message;

public interface MessageService extends CrudService<Message, String> {
	
	List<Message> getMessagesByReceiver(String username);
}
