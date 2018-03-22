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
    int result = 0;
    result = jdbcTemplate.update(sql, objBook.getTitle(), objBook.getAuthor(), objBook.getDescription(),
        objBook.getCreated_at(), objBook.getUpdated_at(), objBook.getIdUser(), objBook.getPictureBook());
    return result;
  }

  public int editBook(Book objBook) {
    String sql = "UPDATE book SET title = ?, author = ?,description = ?, updated_at = ? WHERE idBook = ? ";
    int result = 0;
    result = jdbcTemplate.update(sql, objBook.getTitle(), objBook.getAuthor(), objBook.getDescription(),
        objBook.getUpdated_at(), objBook.getIdBook());
    return result;
  }

  public int deleteBook(int id) {
    String sql = "DELETE FROM book WHERE idBook = " + id;
    return jdbcTemplate.update(sql);
  }

  public List<Book> getListSearchBookByUserLogin(int idUser, String keySearch) {
    String sql = "SELECT * FROM book WHERE title LIKE '%" + keySearch + "%'\n" + "AND idUser = " + idUser
        +" UNION "
        + "SELECT * FROM book WHERE author LIKE '%" + keySearch + "%'\n" + "AND idUser = " + idUser;
    return jdbcTemplate.query(sql, new BookMapper());
  }

public List<Book> getListSearchAllBook(String keySearch) {
	String sql = "SELECT * FROM book WHERE title LIKE '%" + keySearch + "%'\n"
	        +" UNION "
	        + "SELECT * FROM book WHERE author LIKE '%" + keySearch + "%'\n";
	    return jdbcTemplate.query(sql, new BookMapper());
}

}
