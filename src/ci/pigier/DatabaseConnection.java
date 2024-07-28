package ci.pigier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
	private static DatabaseConnection instance = new DatabaseConnection();

	private DatabaseConnection() {}

	public static DatabaseConnection getInstance() {
		return instance;
	}

	public static Connection getConnection() {
		String host = "0.0.0.0";
		String url="jdbc:mysql://" + host + ":3306/notetakingappdb";
		String user="root";
		String password="root";
	
		Connection connection=null;
		try {
			connection= DriverManager.getConnection(url,user,password);
			System.out.println("Connection a la base de donnée etablie avec succès");
		} catch (SQLException e) {
			System.err.println("Erreur: " + e.getMessage());
		}
		
		return connection;
	}
}
