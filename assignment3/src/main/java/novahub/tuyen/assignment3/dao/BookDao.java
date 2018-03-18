package novahub.tuyen.assignment3.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import novahub.tuyen.assignment3.entities.Book;
import novahub.tuyen.assignment3.entities.BookMapper;

@Repository
@Transactional
public class BookDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Book> getListBook() {

    String sql = "SELECT * FROM book";
    return jdbcTemplate.query(sql, new BookMapper());
  }

  public Book getBookById(int id) {
    String sql = "SELECT * FROM book WHERE idBook = ?";
    return jdbcTemplate.queryForObject(sql, new BookMapper(), id);
  }

  public List<Book> getListBookByUserLogin(int idUser) {
    String sql = "SELECT * FROM book WHERE idUser = ?";
    return jdbcTemplate.query(sql, new BookMapper(), idUser);
  }

  public int addBook(Book objBook) {
    String sql = "INSERT INTO book (title,author,description, created_at,updated_at,idUser,pictureBook) VALUES (?, ?, ?, ?, ?, ?, ?)";
    int result = jdbcTemplate.update(sql, objBook.getTitle(), objBook.getAuthor(), objBook.getDescription(),
        objBook.getCreated_at(), objBook.getUpdated_at(), objBook.getIdUser(), objBook.getPictureBook());
    return result;
  }

}
