package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Message;
import rs.ac.uns.ftn.web.synx.services.MessageService;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class MessageServiceImpl implements MessageService {
	
	private Map<String, Message> messages = MyDatabase.getMessages();
	
	@Override
	public Message findOne(String id) {
		return null;
	}

	@Override
	public List<Message> findAll() {
		return new ArrayList<>(messages.values());
	}

	@Override
	public Message create(Message entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);
		messages.put(id, entity);
		Serializer.save(PathManager.MESSAGES, messages);
		return entity;
	}

	@Override
	public void remove(String id) {
	}

	@Override
	public Map<String, List<Message>> getMessagesByReceiver(String username) {
		List<Message> allMessages = findAll();
		Map<String, List<Message>> receivedMessages = new HashMap<>();
		
		for (Message message : allMessages) {
			if (message.getReceiver().equals(username)) {
				String key = message.getSender();
				
				if (receivedMessages.containsKey(key)) {
					receivedMessages.get(key).add(message);
				}
				else {
					receivedMessages.put(key, new ArrayList<>());
					receivedMessages.get(key).add(message);
				}
			}
		}
		return receivedMessages;
	}

}
