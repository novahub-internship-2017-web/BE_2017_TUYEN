package novahub.tuyen.assignment3.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookMapper implements RowMapper<Book>{

  public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
    
    Book book = new Book();
    book.setIdBook(rs.getInt("idBook"));
    book.setTitle(rs.getString("title"));
    book.setPictureBook(rs.getString("pictureBook"));
    book.setAuthor(rs.getString("author"));
    book.setDescription(rs.getString("description"));
    book.setCreated_at(rs.getDate("created_at"));
    book.setUpdated_at(rs.getDate("updated_at"));
    book.setIdUser(rs.getInt("idUser"));
    
    return book;
  }
}
