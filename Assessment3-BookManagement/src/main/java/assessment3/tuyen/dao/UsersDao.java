package assessment3.tuyen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import assessment3.tuyen.entities.Users;
import assessment3.tuyen.entities.UsersMapper;

@Repository
@Transactional
public class UsersDao {
  @Autowired
  DataSource           datasource;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  Connection           conn;
  PreparedStatement    pst;
  ResultSet            rs;
  
  public List<Users> findAll() {
    String sql = "SELECT * FROM users";
    return jdbcTemplate.query(sql, new UsersMapper());
  }
  
  public Users checkLogin(String email, String password) {
    // String sql = "SELECT * FROM users WHERE email = '" + users.getEmail() + "' "
    // + " AND password = '" + MD5Library.md5(users.getPassword()) + "'"
    // + " AND active = 1";
    String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND active = 1";
    Users objUser = new Users();
    
    try {
      conn = datasource.getConnection();
      pst = conn.prepareStatement(sql);
      
      pst.setString(1, email);
      pst.setString(2, password);
      rs = pst.executeQuery();
      if (rs.next()) {
        objUser = new Users(rs.getInt("idUser"), rs.getString("email"), rs.getString("password"),
            rs.getString("firstName"), rs.getString("lastName"), rs.getInt("idRole"), rs.getInt("active"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return objUser;
  }

  
  
  public Users findById(int id) {
    String sql = "SELECT * FROM users WHERE idUser = ?";
    return jdbcTemplate.queryForObject(sql, new UsersMapper(), id);
  }
  
}
