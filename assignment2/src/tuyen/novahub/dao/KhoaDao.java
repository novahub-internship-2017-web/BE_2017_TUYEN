package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tuyen.novahub.entities.Khoa;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class KhoaDao {
	private java.sql.Connection conn;
	private ConnectMySQLDBLibrary connectMySQLDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public KhoaDao() {
		connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
	}

	public Khoa getItem(int idKhoa) {
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM Khoa WHERE IdKhoa = ?";
		Khoa objKhoa = new Khoa();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idKhoa);
			rs = pst.executeQuery();
			if (rs.next()) {
				objKhoa = new Khoa(rs.getInt("IdKhoa"), rs.getString("TenKhoa"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return objKhoa;
	}

	public ArrayList<Khoa> getListKhoa() {
		ArrayList<Khoa> listKhoa = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM Khoa ORDER BY IdKhoa DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Khoa objKhoa = new Khoa(rs.getInt("IdKhoa"), rs.getString("TenKhoa"));
				listKhoa.add(objKhoa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listKhoa;
	}

	/*public Khoa getItemByIdUser(int idUser) {
	    conn = connectMySQLDBLibrary.getConnectMySQL();
	    String sql = "SELECT * FROM Khoa WHERE IdUser = ?";
	    Khoa objKhoa = new Khoa();
	    try {
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, idUser);
	      rs = pst.executeQuery();
	      if (rs.next()) {
	        objKhoa = new Khoa(rs.getInt("IdKhoa"), rs.getString("TenKhoa"));
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        pst.close();
	        conn.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    
	    return objKhoa;
	  }*/

}
