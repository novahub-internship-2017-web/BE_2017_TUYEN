package tuyen.novahub.assignment4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.repository.BookRepository;

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
		return bookRepository.findByIdUser(idUser);
	}

	public Book save(Book newBook) {
		return bookRepository.save(newBook);
		
	}

	public int deleteByIdBook(int idBook) {
		return bookRepository.deleteByIdBook(idBook);
		
	}


	public Page<Book> findAllPageable(PageRequest pageRequest) {
		return bookRepository.findAll(pageRequest);
	}

	public Page<Book> findByEnabled(int enabled, Pageable pageable) {
		return bookRepository.findByEnabled(enabled,pageable);
	}

	public Page<Book> findByIdUser(int idUser, Pageable pageable) {
		return bookRepository.findByIdUser(idUser,pageable);
	}

//	public Page<Book> findByIdUserPageable(int idUser, PageRequest of) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
