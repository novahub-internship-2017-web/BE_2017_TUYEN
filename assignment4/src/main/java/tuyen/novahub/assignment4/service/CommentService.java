package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.repository.CommentRepository;

@Service
@Transactional
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	public List<Comment> findByIdBook(int idBook) {
		return commentRepository.findByIdBook(idBook);
	}

	public List<Comment> findByIdUser(int idUser) {
		return commentRepository.findByIdUser(idUser);
	}

	public Comment findByIdComment(int idComment) {
		return commentRepository.findByIdComment(idComment);
	}

	public Comment save(Comment objComment) {
		return save(objComment);
	}

//	public List<Comment> findAllByRemove(int i) {
//		return commentRepository.findAllByRemove(i);
//	}
//
//	public List<Comment> findByIdBook(int idBook) {
//		return commentRepository.findByIdBook(idBook);
//	}
//
//	public List<Comment> findByIdBookAndRemove(int idBook, int remove) {
//		return commentRepository.findByIdBookAndRemove(idBook,remove);
//	}
//
//	public Comment findByIdComment(int idComment) {
//		return commentRepository.findByIdComment(idComment);
//	}
//
//	public void save(Comment del) {
//		commentRepository.save(del);
//	}
//
//	public List<Comment> findAllByRemoveAndIdBook(int remove, int idBook) {
//		return commentRepository.findAllByRemoveAndIdBook(remove,idBook);
//	}
//
//	public List<Comment> findByIdUser(int idUser) {
//		return commentRepository.findByIdUser(idUser);
//	}
//
//	public List<Comment> findAllByIdBook(int idBook) {
//		return commentRepository.findAllByIdBook(idBook);
//	}
	
}
