package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

	public List<Comment> findAllByRemove(int i);

	public List<Comment> findByIdBook(int idBook);

	public List<Comment> findByIdBookAndRemove(int idBook, int remove);

	public Comment findByIdComment(int idComment);

	public List<Comment> findAllByRemoveAndIdBook(int remove, int idBook);
	
	
}
