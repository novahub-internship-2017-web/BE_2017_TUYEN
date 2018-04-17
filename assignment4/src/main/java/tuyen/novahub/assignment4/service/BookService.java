package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.dao.BookRepository;
import tuyen.novahub.assignment4.model.Book;

@Service
@Transactional
public class BookService {
	
	@Autowired
	BookRepository bookRepository;

	public List<Book> findAllByEnabledAndRemove(int enabled,int remove) {
		return bookRepository.findAllByEnabledAndRemove(enabled,remove);
	}

	public Book findByIdBook(int idBook) {
		return bookRepository.findByIdBook(idBook);
	}

	public List<Book> findAllByIdUserAndRemove(int idUser,int remove) {
		return bookRepository.findAllByIdUserAndRemove(idUser,remove);
	}

	public List<Book> findAllByIdUser(int idUser) {
		return bookRepository.findAllByIdUser(idUser);
	}

	public void save(Book objBook) {
		bookRepository.save(objBook);
	}

	public Book findByIdBookAndRemoveAndEnabled(int idBook, int remove, int enabled) {
		return bookRepository.findByIdBookAndRemoveAndEnabled(idBook,remove,enabled);
	}

	public Book findByIdBookAndRemove(int idBook, int remove) {
		return bookRepository.findByIdBookAndRemove(idBook,remove);
	}

}
