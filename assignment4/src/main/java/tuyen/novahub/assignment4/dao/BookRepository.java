package tuyen.novahub.assignment4.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	public List<Book> findAllByEnabled(int i);

	public Book findByIdBook(int idBook);
}
