package assessment3.tuyen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assessment3.tuyen.dao.BooksDao;
import assessment3.tuyen.entities.Books;

@Service
@Transactional
public class BooksService {
  
  @Autowired
  BooksDao booksDao;
  
  public List<Books> findAll() {
    return booksDao.findAll();
  }
  
}
