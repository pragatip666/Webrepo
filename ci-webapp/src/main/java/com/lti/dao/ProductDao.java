package com.lti.dao;

import java.io.IOException;
//import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.dao.util.ConnManager;
import com.lti.entity.Product;

//classes which contain DB code are commonly
//referred to as Data Access Objects
public class ProductDao {

	public Product select(int id)  {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn= ConnManager.connect();
		
		
			String sql = "SELECT * FROM tbl_product WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}
			return null; //very bad should throw an exception indicating that no product found
		}
		//very bad should throw an exception indicating some problem which trying to fetch data

		catch(SQLException | IOException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null;
		
	}
	finally {
		try { conn.close(); } catch(Exception e) { }
	}
	}
		

	
	
	public List<Product> selectAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/trainingdb", "mj", "mj");
			String sql = "SELECT * FROM tbl_product";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			List<Product> list = new ArrayList<>();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				list.add(product);
			}
			return list;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
			return null; //very bad should throw an exception indicating some problem while trying to fetch data
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
		
	}
	
	public void insert(Product product) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/trainingdb", "mj", "mj");
			String sql = "INSERT INTO tbl_product VALUES(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setDouble(3, product.getPrice());
			int count = stmt.executeUpdate();
			//checking count important in case of update/delete statements
			//for ex: if(count == 0) indicates no rows got updated/deleted
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace(); //we should rather throw an user defined exception
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}
}