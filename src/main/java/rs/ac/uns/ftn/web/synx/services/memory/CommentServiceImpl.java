package rs.ac.uns.ftn.web.synx.services.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import rs.ac.uns.ftn.web.synx.database.MyDatabase;
import rs.ac.uns.ftn.web.synx.model.Comment;
import rs.ac.uns.ftn.web.synx.model.Topic;
import rs.ac.uns.ftn.web.synx.services.CommentService;
import rs.ac.uns.ftn.web.synx.services.TopicService;
import rs.ac.uns.ftn.web.synx.util.PathManager;
import rs.ac.uns.ftn.web.synx.util.Serializer;

public class CommentServiceImpl implements CommentService {
	
	private Map<String, Comment> comments = MyDatabase.getComments();
	private TopicService topicService = new TopicServiceImpl();
	
	@Override
	public Comment findOne(String id) {
		if(comments.containsKey(id)) {
			return comments.get(id);
		}
		return null;
	}

	@Override
	public List<Comment> findAll() {
		return new ArrayList<>(comments.values());
	}

	@Override
	public Comment create(Comment entity) {
		String commentId = UUID.randomUUID().toString();
		entity.setId(commentId);
		entity.setCreationDate(new Date());
		
		// check if it is a root comment
		if (entity.getParentComment() == null) {
			// update topic's list of comments
			Topic updatedTopic = topicService.addComment(entity.getTopic(), commentId);
			if (updatedTopic == null) {
				return null;
			}
			
		}
		else {
			// if it is not a root comment, add it to the parent's subcomments list
			Comment parentComment = findOne(entity.getParentComment());
			parentComment.getSubcomments().add(entity);
		}
		comments.put(commentId, entity);
		Serializer.save(PathManager.COMMENTS, comments);
		return entity;
	}

	@Override
	public void remove(String id) {
		Comment comment = comments.get(id);
		comment.setDeleted(true);
		Serializer.save(PathManager.COMMENTS, comments);
	}

	@Override
	public List<Comment> findAllByTopicId(String topicId) {
		List<Comment> allComments = findAll();
		List<Comment> commentsOfTheTopic = new ArrayList<>();
		for (Comment comment : allComments) {
			if (comment.getTopic().equals(topicId) && comment.getParentComment() == null) {
				commentsOfTheTopic.add(comment);
			}
		}
		return commentsOfTheTopic;
	}

	@Override
	public Comment update(String id, Comment comment) {
		if (comments.containsKey(id)) {
			Comment c = comments.get(id);
			c.setContent(comment.getContent());
			c.setEdited(comment.isEdited());
			c.setNumberOfLikes(comment.getNumberOfLikes());
			c.setNumberOfDislikes(comment.getNumberOfDislikes());
			Serializer.save(PathManager.COMMENTS, comments);
			return comment;
		}
		return null;
	}

}
