package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tuyen.novahub.entities.PhongBan;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class PhongBanDao {
  private java.sql.Connection   conn;
  private ConnectMySQLDBLibrary connectMySQLDBLibrary;
  private Statement             st;
  private PreparedStatement     pst;
  private ResultSet             rs;
  
  public PhongBanDao() {
    connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
  }
  
  public PhongBan getItem(int idPhongBan) {
    conn = connectMySQLDBLibrary.getConnectMySQL();
    String sql = "SELECT * FROM PhongBan WHERE IdPhongBan = ?";
    PhongBan objPhongBan = new PhongBan();
    try {
      pst = conn.prepareStatement(sql);
      pst.setInt(1, idPhongBan);
      rs = pst.executeQuery();
      if (rs.next()) {
        objPhongBan = new PhongBan(rs.getInt("IdPhongBan"), rs.getString("TenPhongBan"));
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
    
    return objPhongBan;
  }
  
  public ArrayList<PhongBan> getListPhongBan() {
		ArrayList<PhongBan> listPhongBan = new ArrayList<>();
		conn = connectMySQLDBLibrary.getConnectMySQL();
		String sql = "SELECT * FROM PhongBan ORDER BY IdPhongBan DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				PhongBan objPhongBan = new PhongBan(rs.getInt("IdPhongBan"),rs.getString("TenPhongBan"));
				listPhongBan.add(objPhongBan);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listPhongBan;
	}
  
 /* public PhongBan getItemByIdUser(int idUser) {
	    conn = connectMySQLDBLibrary.getConnectMySQL();
	    String sql = "SELECT * FROM PhongBan WHERE IdUser = ?";
	    PhongBan objPhongBan = new PhongBan();
	    try {
	      pst = conn.prepareStatement(sql);
	      pst.setInt(1, idUser);
	      rs = pst.executeQuery();
	      if (rs.next()) {
	        objPhongBan = new PhongBan(rs.getInt("IdPhongBan"), rs.getString("TenPhongBan"));
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
	    
	    return objPhongBan;
	  }*/
	  
  
  
}
