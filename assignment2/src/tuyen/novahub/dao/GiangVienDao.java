package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import tuyen.novahub.entities.GiangVien;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class GiangVienDao {
  
  private java.sql.Connection   conn;
  private ConnectMySQLDBLibrary connectMySQLDBLibrary;
  private PreparedStatement     pst;
  private ResultSet             rs;
  public GiangVienDao() {
    connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
  }
  
  public GiangVien getItem(int idUser) {
    conn = connectMySQLDBLibrary.getConnectMySQL();
    String sql = "SELECT * FROM GiangVien WHERE IdUser = ?";
    GiangVien objGiangVien = new GiangVien();
    try {
      pst = conn.prepareStatement(sql);
      pst.setInt(1, idUser);
      rs = pst.executeQuery();
      if (rs.next()) {
        objGiangVien = new GiangVien(rs.getInt("IdGiangVien"),rs.getInt("IdUser"), rs.getInt("IdKhoa"),rs.getInt("SoTietDay"));
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
    
    return objGiangVien;
  }

public int addGiangVien(GiangVien objGiangVien) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO GiangVien(IdUser,IdKhoa,SoTietDay) VALUES(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objGiangVien.getIdUser());
			pst.setInt(2, objGiangVien.getIdKhoa());
			pst.setInt(3, objGiangVien.getSoTietDay());
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

public int editGiangVien(GiangVien objGiangVien) {
	int result = 0;
	conn = connectMySQLDBLibrary.getConnectMySQL();
	String sql = "UPDATE GiangVien SET IdKhoa = ?,SoTietDay = ? WHERE IdUser = ?";
	try {
		pst = conn.prepareStatement(sql);
		pst.setInt(1, objGiangVien.getIdKhoa());
		pst.setInt(2, objGiangVien.getSoTietDay());
		pst.setInt(3, objGiangVien.getIdUser());
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

}
