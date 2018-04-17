package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	public List<Book> findAllByEnabledAndRemove(int enabled, int remove);

	public Book findByIdBook(int idBook);

	public List<Book> findAllByIdUserAndRemove(int idUser,int remove);

	public List<Book> findAllByIdUser(int idUser);

	public Book findByIdBookAndRemoveAndEnabled(int idBook, int remove, int enabled);

	public Book findByIdBookAndRemove(int idBook, int remove);
	
//	@Query("SELECT b.enabled FROM book  b WHERE b.id_book = 2")
//	public Book findBook();
}
