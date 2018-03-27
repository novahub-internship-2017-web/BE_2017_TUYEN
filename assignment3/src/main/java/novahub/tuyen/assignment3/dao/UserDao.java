package novahub.tuyen.assignment3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import novahub.tuyen.assignment3.entities.Role;
import novahub.tuyen.assignment3.entities.RoleMapper;
import novahub.tuyen.assignment3.entities.User;
import novahub.tuyen.assignment3.entities.UserMapper;
import novahub.tuyen.assignment3.library.MD5Library;

@Repository
@Transactional
public class UserDao {

	@Autowired
	DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	public User checkLogin(String email, String password) {
		String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
		User objUser = new User();

		try {
			conn = datasource.getConnection();
			pst = conn.prepareStatement(sql);

			pst.setString(1, email);
			pst.setString(2, MD5Library.md5(password));
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("idUser"), rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("picture"),
						rs.getInt("idRole"), rs.getInt("active"));
			}
		} catch (SQLException e) {
			Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return objUser;
	}

	public List<User> getListUser() {
		String sql = "SELECT * FROM user";
		return jdbcTemplate.query(sql, new UserMapper());
	}

	public List<Role> getListRole() {
		String sql = "SELECT * FROM role";
		return jdbcTemplate.query(sql, new RoleMapper());
	}

	public Role getNameRoleById(int idRole) {
		String sql = "SELECT * FROM role WHERE idRole = " + idRole;
		Role objRole = new Role();

		try {
			conn = datasource.getConnection();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				objRole = new Role(rs.getInt("idRole"), rs.getString("nameRole"));
			}
		} catch (SQLException e) {
			Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return objRole;
	}

	public int addUser(User objUser) {
		String sql = "INSERT INTO user (email,password,firstName, lastName,picture,idRole,active) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int result = 0;
		result = jdbcTemplate.update(sql, objUser.getEmail(), MD5Library.md5(objUser.getPassword()),
				objUser.getFirstName(), objUser.getLastName(), objUser.getPicture(), objUser.getIdRole(), 1);

		return result;
	}

	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE idUser = ?";
		return jdbcTemplate.queryForObject(sql, new UserMapper(), id);

	}

	public int editUser(User objUser) {
		String sql = "UPDATE user SET firstName = ?, lastName = ?, idRole = ? ,picture = ? WHERE idUser = ? ";
		int result = 0;
		result = jdbcTemplate.update(sql, objUser.getFirstName(), objUser.getLastName(), objUser.getIdRole(),
				objUser.getPicture(), objUser.getIdUser());
		System.out.println("Kết quả update: " + result);
		return result;
	}

	public int delUser(int id) {
		String sql = "DELETE FROM user WHERE idUser = " + id;
		int result = 0;
		result = jdbcTemplate.update(sql);
		System.out.println("Kết quả khi xóa : " + result);
		return result;
	}

	public User checkEmail(String aemail) {
		System.out.println("Email truyền tới: " + aemail);

		String sql = "SELECT * FROM user WHERE email = ?";
		User objUser = new User();

		try {
			conn = datasource.getConnection();
			pst = conn.prepareStatement(sql);

			pst.setString(1, aemail);
			rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new User(rs.getInt("idUser"), rs.getString("email"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("picture"),
						rs.getInt("idRole"), rs.getInt("active"));
			}
		} catch (SQLException e) {
			Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return objUser;

	}

	public int changePassword(User objUser) {
		String sql = "UPDATE user SET password = ? WHERE idUser = ? ";
		int result = jdbcTemplate.update(sql, MD5Library.md5(objUser.getPassword()), objUser.getIdUser());
		System.out.println("Kết quả đổi pass thành công: " + result);
		return result;
	}

	public int changeStatus(int idUser, int status) {
		String sql = "UPDATE user SET active = ? WHERE idUser = ? ";
		int result = jdbcTemplate.update(sql, status, idUser);
		System.out.println("Kết quả đổi active thành công: " + result);
		return result;
	}

}
