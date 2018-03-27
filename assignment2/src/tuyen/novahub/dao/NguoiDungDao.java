package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import tuyen.novahub.entities.NguoiDung;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class NguoiDungDao {
	private java.sql.Connection conn;
	private ConnectMySQLDBLibrary connectMySQLDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public NguoiDungDao() {
		connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
	}

	public NguoiDung checkLogin(String username, String password) {
		conn = connectMySQLDBLibrary.getConnectMySQL();
		NguoiDung objNguoiDung = new NguoiDung();
		String sql = "SELECT * FROM NguoiDung WHERE Username= ? && Password = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"), rs.getString("Password"),
						rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"), rs.getString("DiaChi"),
						rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
			}
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return objNguoiDung;
	}

	public ArrayList<NguoiDung> getListNguoiDung() {
		ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM NguoiDung ORDER BY NguoiDung.IdUser DESC";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				NguoiDung objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
				listNguoiDung.add(objNguoiDung);
			}

		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return listNguoiDung;
	}

	public NguoiDung getNguoiDung(int idUser) {

		conn = connectMySQLDBLibrary.getConnectMySQL();
		NguoiDung objNguoiDung = new NguoiDung();
		String sql = "SELECT * FROM NguoiDung WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if (rs.next()) {
				objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"), rs.getString("Password"),
						rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"), rs.getString("DiaChi"),
						rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
			}
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return objNguoiDung;
	}

	public int delItem(int idUser) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "DELETE FROM NguoiDung WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return result;
	}

	public int addUser(NguoiDung objUser) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO NguoiDung(Username,Password,Ho,Ten,NamSinh,DiaChi,IdLoaiDangNhap,IdLoaiCanBo) VALUES(?,?,?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getUsername());
			pst.setString(2, objUser.getPassword());
			pst.setString(3, objUser.getHo());
			pst.setString(4, objUser.getTen());
			pst.setInt(5, objUser.getYearOfBirth());
			pst.setString(6, objUser.getAddress());
			pst.setInt(7, objUser.getIdLoaiDangNhap());
			pst.setInt(8, objUser.getIdLoaiCanBo());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return result;
	}

	public int editUser(NguoiDung objNguoiDung) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "UPDATE NguoiDung SET Ho = ?, Ten = ? ,NamSinh = ?,DiaChi = ? ,IdLoaiDangNhap = ?, IdLoaiCanBo = ? WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNguoiDung.getHo());
			pst.setString(2, objNguoiDung.getTen());
			pst.setInt(3, objNguoiDung.getYearOfBirth());
			pst.setString(4, objNguoiDung.getAddress());
			pst.setInt(5, objNguoiDung.getIdLoaiDangNhap());
			pst.setInt(6, objNguoiDung.getIdLoaiCanBo());
			pst.setInt(7, objNguoiDung.getIdUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return result;
	}

	public ArrayList<NguoiDung> getListNguoiDungSortByName(int n) {
		ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sqlASC = "SELECT * FROM NguoiDung ORDER BY NguoiDung.Ten ASC";
		String sqlDESC = "SELECT * FROM NguoiDung ORDER BY NguoiDung.Ten DESC";
		try {
			st = conn.createStatement();
			if (n == 1) {
				rs = st.executeQuery(sqlASC);
			}
			if (n == 2) {
				rs = st.executeQuery(sqlDESC);
			}

			while (rs.next()) {
				NguoiDung objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
				listNguoiDung.add(objNguoiDung);
			}

		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return listNguoiDung;
	}

	public ArrayList<NguoiDung> getListNguoiDungSortByYear(int n) {
		ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		// giam dam
		String sql1 = "SELECT * FROM NguoiDung ORDER BY NguoiDung.NamSinh DESC";
		// tang dan
		String sql2 = "SELECT * FROM NguoiDung ORDER BY NguoiDung.NamSinh ASC";

		try {
			st = conn.createStatement();
			if (n == 3) {
				rs = st.executeQuery(sql2);
			}
			if (n == 4) {
				rs = st.executeQuery(sql1);
			}

			while (rs.next()) {
				NguoiDung objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
				listNguoiDung.add(objNguoiDung);
			}

		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return listNguoiDung;
	}

	public int changePassword(NguoiDung objNguoiDung) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "UPDATE NguoiDung SET Password = ? WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objNguoiDung.getPassword());
			pst.setInt(2, objNguoiDung.getIdUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return result;
	}

	public ArrayList<NguoiDung> getListSearchByName(String search,String name) {
		ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = " SELECT * FROM NguoiDung WHERE Ten LIKE '%"+name+"%' OR Ho LIKE '"+search
				+ "%' ORDER BY NguoiDung.Ten ASC";
		System.out.println(sql);
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				NguoiDung objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
				listNguoiDung.add(objNguoiDung);
			}

		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return listNguoiDung;
	}

	public ArrayList<NguoiDung> getListSearchByYear(int search) {
		ArrayList<NguoiDung> listNguoiDung = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = " SELECT * FROM NguoiDung WHERE NamSinh=" + search + " ORDER BY NguoiDung.Ten ASC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				NguoiDung objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
				listNguoiDung.add(objNguoiDung);
			}

		} catch (SQLException e) {
			Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NguoiDungDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return listNguoiDung;
	}

	public NguoiDung checkUserName(String username){
		NguoiDung objNguoiDung = null;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM NguoiDung WHERE Username = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()){
				objNguoiDung = new NguoiDung(rs.getInt("IdUser"), rs.getString("Username"),
						rs.getString("Password"), rs.getString("Ho"), rs.getString("Ten"), rs.getInt("NamSinh"),
						rs.getString("DiaChi"), rs.getInt("IdLoaiDangNhap"), rs.getInt("IdLoaiCanBo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objNguoiDung;
	}

}
