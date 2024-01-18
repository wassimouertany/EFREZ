package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import models.Cart;
import models.Product;

public class ProductDAO {
	private Connection connexion;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDAO(Connection connexion) {
		this.connexion = connexion;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		try {
			query="select * from products";
			pst = this.connexion.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query = "select * from products where id= ?";
					pst = this.connexion.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
						
						
					}
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	
	public double getTotalPrice(ArrayList<Cart> cartList) {
			double s=0;
			
			try {
				if(cartList.size()>0) {
					for(Cart item:cartList) {
						query = "select price from products where id = ?";
						pst = this.connexion.prepareStatement(query);
						pst.setInt(1, item.getId());
						rs = pst.executeQuery();
						
						while(rs.next()) {
							s+=rs.getDouble("price")*item.getQuantity();
						}
						
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return s;
	}

	public Product getSingleProduct(int id) {
		
		Product row =null;
		
		try {
			query = "select * from products where id=?";
			pst = this.connexion.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return row;
	}
	
	
	
	public void deleteProduct(int id) {
		try {
			query= "delete from products where id = ?";
			pst=connexion.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Product save(Product p) {
		
		try {
			query = "insert into products(name,category,price,image) values(?,?,?,?)";
			pst=connexion.prepareStatement(query);
			pst.setString(1, p.getName());
			pst.setString(2, p.getCategory());
			pst.setDouble(3, p.getPrice());
			pst.setString(4, p.getImage());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public Product update(Product p) {
		try {
			query = "UPDATE products SET name=?, category=?, price=?, image=? WHERE id=?";
			pst=connexion.prepareStatement(query);
			pst.setString(1, p.getName());
			pst.setString(2, p.getCategory());
			pst.setDouble(3, p.getPrice());
			pst.setString(4, p.getImage());
			pst.setInt(5, p.getId());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	

	
	public List<Product> chercherParMC(String mot) {
		
		Product row =null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			query = "select * from products where name like ?";
			pst = this.connexion.prepareStatement(query);
			pst.setString(1, "%"+mot.toLowerCase() + "%");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	
	
	
}
