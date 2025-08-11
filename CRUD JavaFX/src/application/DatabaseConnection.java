package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		if(connection== null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(
					    "jdbc:mysql://localhost:3306/shoestore", "root", ""
					);
				System.out.println("[+] Successfully made connection to MySQL");
	            
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("[!] Failed to make connection to MySQL");
			}
		}
		
		return connection;
	}
	
}
	


