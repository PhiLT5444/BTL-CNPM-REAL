package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect{
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QLChungCu", "root", "admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
}