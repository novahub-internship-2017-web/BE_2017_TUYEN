package novahub.tuyen.assignment3.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RoleMapper implements RowMapper<Role> {

  public Role mapRow(ResultSet rs, int rowNum) throws SQLException {

    Role role = new Role();
    role.setIdRole(rs.getInt("idRole"));
    role.setNameRole(rs.getString("nameRole"));

    return role;
  }
}
