package novahub.tuyen.assignment3.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int rowNum) throws SQLException {
    User user = new User();
    user.setIdUser(rs.getInt("idUser"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setFirstName(rs.getString("firstName"));
    user.setLastName(rs.getString("lastName"));
    user.setPicture(rs.getString("picture"));
    user.setIdRole(rs.getInt("idRole"));
    user.setActive(rs.getInt("active"));
    return user;
  }

}
