package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import tuyen.novahub.entities.NhanVien;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class NhanVienDao {

	private java.sql.Connection conn;
	private ConnectMySQLDBLibrary connectMySQLDBLibrary;
	private PreparedStatement pst;
	private ResultSet rs;

	public NhanVienDao() {
		connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
	}

	public NhanVien getItem(int idUser) {
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM NhanVien WHERE IdUser = ?";
		NhanVien objNhanVien = new NhanVien();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();
			if (rs.next()) {
				objNhanVien = new NhanVien(rs.getInt("IdNhanVien"), rs.getInt("IdUser"), rs.getInt("IdPhongBan"),
						rs.getInt("SoNgayCong"));
			}
		} catch (SQLException e) {
			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return objNhanVien;
	}

public int addNhanVien(NhanVien objNhanVien) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "INSERT INTO NhanVien(IdUser,IdPhongBan,SoNgayCong) VALUES(?,?,?)";
		try {
			pst = conn.prepareStatement(sql);

			pst.setInt(1, objNhanVien.getIdUser());

			pst.setInt(2, objNhanVien.getIdPhongBan());
			pst.setInt(3, objNhanVien.getSoNgayLamViec());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return result;

	}

	public int editNhanVien(NhanVien objNhanVien) {
		int result = 0;
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "UPDATE NhanVien SET IdPhongBan = ?,SoNgayCong = ? WHERE IdUser = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, objNhanVien.getIdPhongBan());
			pst.setInt(2, objNhanVien.getSoNgayLamViec());
			pst.setInt(3, objNhanVien.getIdUser());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				Logger.getLogger(NhanVienDao.class.getName()).log(Level.SEVERE, null, e);
			}
		}

		return result;
	}

}
