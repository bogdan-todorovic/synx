package rs.ac.uns.ftn.web.synx.services;

import java.util.List;

import rs.ac.uns.ftn.web.synx.model.Comment;

public interface CommentService extends CrudService<Comment, String> {
	List<Comment> findAllByTopicId(String topicId);
}
