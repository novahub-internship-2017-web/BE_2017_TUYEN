package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

	public List<Comment> findByIdBook(int idBook);

	public List<Comment> findByIdUser(int idUser);

	public Comment findByIdComment(int idComment);

	@SuppressWarnings("unchecked")
	public Comment save(Comment objComment);

}
