package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tuyen.novahub.entities.ChucVu;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class ChucVuDao {
	private java.sql.Connection conn;
	private ConnectMySQLDBLibrary connectMySQLDBLibrary;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ChucVuDao() {
		connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
	}

	public ChucVu getItem(int idChucVu) {
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM ChucVu WHERE IdChucVu = ?";
		ChucVu objChucVu = new ChucVu();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idChucVu);
			rs = pst.executeQuery();
			if (rs.next()) {
				objChucVu = new ChucVu(rs.getInt("IdChucVu"), rs.getString("ChucVu"), rs.getDouble("PhuCap"),
						rs.getInt("IdLoaicanBo"));
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

		return objChucVu;
	}

	public ArrayList<ChucVu> getListChucVu(int idLoaiCanBo) {
		ArrayList<ChucVu> listChucVu = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM ChucVu WHERE IdLoaiCanBo = "+idLoaiCanBo;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ChucVu objChucVu = new ChucVu(rs.getInt("IdChucVu"), rs.getString("ChucVu"), rs.getDouble("PhuCap"),
						rs.getInt("IdLoaicanBo"));
				listChucVu.add(objChucVu);
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
		return listChucVu;
	}
}
