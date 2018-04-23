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

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findByEnabled(int enabled) {
		return bookRepository.findByEnabled(enabled);
	}

	public Book findByIdBook(int idBook) {
		return bookRepository.findByIdBook(idBook);
	}

	public List<Book> findByIdUser(int idUser) {
		return bookRepository.findAllByIdUser(idUser);
	}

	public Book save(Book newBook) {
		return bookRepository.save(newBook);
		
	}

	public int deleteByIdBook(int idBook) {
		return bookRepository.deleteByIdBook(idBook);
		
	}

	public List<Book> findByPagination(int offset) {
		return bookRepository.findByPagination(offset);
	}


}
