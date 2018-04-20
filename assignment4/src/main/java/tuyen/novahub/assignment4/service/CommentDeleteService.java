package tuyen.novahub.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.dao.CommentDeleteRepository;
import tuyen.novahub.assignment4.model.CommentDelete;

@Service
@Transactional
public class CommentDeleteService {
	
	@Autowired
	CommentDeleteRepository commentDeleteRepository;
	
	public CommentDelete save(CommentDelete commentDelete) {
		return commentDeleteRepository.save(commentDelete);
	}
	
}
