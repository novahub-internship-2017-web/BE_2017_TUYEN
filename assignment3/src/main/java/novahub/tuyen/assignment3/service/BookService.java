package novahub.tuyen.assignment3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import novahub.tuyen.assignment3.dao.BookDao;
import novahub.tuyen.assignment3.entities.Book;

@Service
@Transactional
public class BookService {
  
  @Autowired
  BookDao bookDao;
  
  public List<Book> getListBook() {
    return bookDao.getListBook();
  }

  public Book getBookById(int id) {
    
    return bookDao.getBookById(id);
  }


  public List<Book> getListBookByUserLogin(int idUser) {
    return bookDao.getListBookByUserLogin(idUser);
  }

  public int addBook(Book objBook) {
    return bookDao.addBook(objBook);
    
  }

  public int editBook(Book objBook) {
    return bookDao.editBook(objBook);
  }

  public int deleteBook(int id) {
    return bookDao.deleteBook(id);
  }

  public List<Book> getListSearchBookByUserLogin(int idUser, String keySearch) {
    return bookDao.getListSearchBookByUserLogin(idUser,keySearch);
  }

public List<Book> getListSearchAllBook(String keySearch) {
	return bookDao.getListSearchAllBook(keySearch);
}

  
}