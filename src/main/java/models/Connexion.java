package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static Connection connection = null;
	
	public static Connection getConnection()  {
		if(connection == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_produits","root","");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return connection;
	}
}
