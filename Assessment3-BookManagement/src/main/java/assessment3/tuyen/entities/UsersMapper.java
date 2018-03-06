package assessment3.tuyen.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UsersMapper implements RowMapper<Users> {
  
  public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
    Users users = new Users();
    users.setIdUser(rs.getInt("idUser"));
    users.setEmail(rs.getString("email"));
    users.setPassword(rs.getString("password"));
    users.setFirstName(rs.getString("firstName"));
    users.setLastName(rs.getString("lastName"));
    users.setIdRole(rs.getInt("idRole"));
    users.setActive(rs.getInt("active"));
    return users;
  }
  
}
