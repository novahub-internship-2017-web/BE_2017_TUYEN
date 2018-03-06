package assessment3.tuyen.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import assessment3.tuyen.entities.Books;
import assessment3.tuyen.entities.BooksMapper;

@Repository
@Transactional
public class BooksDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  public List<Books> findAll() {
    String sql = "SELECT * FROM book";
    return jdbcTemplate.query(sql, new BooksMapper());
  }
  
}
