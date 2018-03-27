package tuyen.novahub.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tuyen.novahub.entities.LoaiCanBo;
import tuyen.novahub.library.ConnectMySQLDBLibrary;

public class LoaiCanBoDao {
  private java.sql.Connection   conn;
  private ConnectMySQLDBLibrary connectMySQLDBLibrary;
  private PreparedStatement     pst;
  private ResultSet             rs;
  public LoaiCanBoDao() {
    connectMySQLDBLibrary = new ConnectMySQLDBLibrary();
  }
  
  public LoaiCanBo getItem(int idLoaiCanBo) {
    conn = connectMySQLDBLibrary.getConnectMySQL();
    String sql ="SELECT * FROM LoaiCanBo WHERE IdLoaiCanBo = ?";
    LoaiCanBo objLoaiCanBo = new LoaiCanBo();
    try {
      pst = conn.prepareStatement(sql);
      pst.setInt(1, idLoaiCanBo);
      rs = pst.executeQuery();
      if(rs.next()){
        objLoaiCanBo = new LoaiCanBo(rs.getInt("IdLoaiCanBo"),rs.getString("TenLoaiCanBo"));
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
    
    return objLoaiCanBo;
  }
  
//  public ArrayList<LoaiCanBo> getListLoaiCanBo() {
//		ArrayList<LoaiCanBo> listLoaiCanBo = new ArrayList<>();
//		conn = connectMySQLDBLibrary.getConnectMySQL();
//		String sql = "SELECT * FROM LoaiCanBo ORDER BY IdKhoa DESC";
//		try {
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			while(rs.next()){
//				Khoa objKhoa = new Khoa(rs.getInt("IdKhoa"),rs.getString("TenKhoa"));
//				listKhoa.add(objKhoa);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally{
//			try {
//				st.close();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return listKhoa;
//	}
}
