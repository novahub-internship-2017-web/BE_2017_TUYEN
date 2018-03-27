package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import tuyen.novahub.entities.Luong;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class LuongDao {
	private java.sql.Connection conn;
	private ConnectMySQLDBLibrary connectMySQLDBLibrary;
	private PreparedStatement pst;
	private ResultSet rs;

	public LuongDao() {
		connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
	}

	public Luong getItem(int idUser) {
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM Luong WHERE IdUser = ?";
		Luong objLuong = new Luong();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if (rs.next()) {
				objLuong = new Luong(rs.getInt("IdLuong"), rs.getInt("IdUser"), rs.getInt("IdChucVu"),
						rs.getFloat("HeSoLuong"));
			}
		} catch (SQLException e) {
			Logger.getLogger(LuongDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(LuongDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return objLuong;
	}

	public int addLuong(Luong objLuong) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO Luong(IdUser,IdChucVu,HeSoLuong) VALUES(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objLuong.getIdUser());
			pst.setInt(2, objLuong.getIdChucVu());
			pst.setFloat(3, objLuong.getHeSoLuong());
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

	public int editLuong(Luong objLuong) {

		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "UPDATE Luong SET idChucVu = ?,HeSoLuong = ? WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objLuong.getIdChucVu());
			pst.setFloat(2, objLuong.getHeSoLuong());
			pst.setInt(3, objLuong.getIdUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(LuongDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(LuongDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		return result;
	}

}
