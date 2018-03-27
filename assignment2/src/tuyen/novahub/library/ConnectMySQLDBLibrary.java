package tuyen.novahub.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQLDBLibrary {
	
	private Connection connection;
	private String url;
	private String user;
	private String pass;
	public ConnectMySQLDBLibrary(){
		this.url = "jdbc:mysql://localhost:3306/quanlycanbo?useUnicode=true&characterEncoding=UTF-8";
		this.user = "root";
		this.pass = "1996Tuyen";
	}
	public Connection getConnectMySQL(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		ConnectMySQLDBLibrary conn = new ConnectMySQLDBLibrary();
		System.out.println(conn.getConnectMySQL());
	}
}
