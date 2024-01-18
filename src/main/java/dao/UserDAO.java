package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.User;

public class UserDAO {
	private Connection connexion;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDAO(Connection connexion) {
		this.connexion = connexion;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
		try {
			query = "select * from users where `email` = ? and `password` = ?";
			pst = this.connexion.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
	
	
	
}
