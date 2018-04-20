package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	public List<Book> findAll();
	
	public List<Book> findByEnabled(int enabled);
	
	public Book findByIdBook(int idBook);
	
	public List<Book> findAllByIdUser(int idUser);
	
	@SuppressWarnings("unchecked")
	public Book save(Book objBook);

	public int deleteByIdBook(int idBook);
}
