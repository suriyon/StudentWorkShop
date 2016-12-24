package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLHelper {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = 
			"jdbc:mysql://localhost:3306/tutorialdb?characterEncoding=UTF-8";
	private static String user = "suriyon";
	private static String password = "1234";
	private static Connection con = null;
	
	public static Connection openDB(){
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void closeDB(){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
