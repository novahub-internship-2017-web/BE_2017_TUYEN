package assessment3.tuyen.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BooksMapper implements RowMapper<Books>{

  public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    Books books = new Books();
    books.setIdBook(rs.getInt("idBook"));
    books.setTitleBook(rs.getString("titleBook"));
    books.setAuthorBook(rs.getString("authorBook"));
    books.setDescriptionBook(rs.getString("descriptionBook"));
    books.setCreatedBook(rs.getDate("createdBook"));
    books.setUpdatedBook(rs.getDate("updatedBook"));
    books.setIdUser(rs.getInt("idUser"));
    
    return books;
  }
  
}
