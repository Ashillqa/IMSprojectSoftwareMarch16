package com.qa.main.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.main.tables.Item;

public class DBi {
	
	private Connection conn;
	private Statement stmt;
	
	public DBi() throws SQLException{
		conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);
		stmt = conn.createStatement();
	}
	
	public void Close() throws SQLException {
		conn.close();
	}
	
	public String createItem(Item item) throws SQLException {
		List<String> itemName = new ArrayList<>();
		String prodName = item.getName().replaceAll("[^a-z\\s+]", "!");
		ResultSet rs1 = stmt.executeQuery("SELECT name from items");
		try{
			while(rs1.next()) {
				itemName.add(rs1.getString("name"));
			}
			if(item.getName().isEmpty()) {
				return "can't have an empty name";
			}else if((itemName.contains(item.getName()))) {
				return "This item already exists please update or delete";
			}else if (prodName.contains("!")) {
				return "Enter a valid item name please";
			}else {
				stmt.executeUpdate("INSERT INTO items (name,quantity,price)"+ "VALUES('" + item.getName()+"', '" +item.getQuantity()+"', '" +item.getPrice()+"')");
				return "item created";
			}
		}finally {
			rs1.close();
		}
	}
	
	public String readItem(Item item) throws SQLException {
		String name = "";
		String check = "";
		ResultSet rs = stmt.executeQuery("SELECT * FROM items WHERE product_id = "+item.getId());
		try{
			while (rs.next()) {
				check = rs.getString("name");
			}
			if(rs.getString("name").isEmpty()||check=="") {
				return "item ID does not exist perhaps try readAll ";
			}else {
				name = "product: " + rs.getString("name")+ "\nStock: " + rs.getInt("quantity")+ "\nPrice: " + rs.getFloat("price");
				return name;
			}
		}finally {
			rs.close();
		}
	}
	
	public String readAllItems() throws SQLException{
		String name = "";
		ResultSet rs = stmt.executeQuery("SELECT * from items");
		try{
			while(rs.next()) {
				name += "ID: "+rs.getInt("product_id")+ "\nproduct: " + rs.getString("name")+ "\nStock: " + rs.getInt("quantity")+ "\nPrice: £" + rs.getFloat("price")+"\n";
			}
		}finally {
			rs.close();
		}
		return name;
	}
		
		public String updateItem(Item item) throws SQLException {
			int itemID = 0;
			ResultSet rs1 = stmt.executeQuery("SELECT product_id from items");
			try{
				while(rs1.next()) {
					itemID = rs1.getInt("product_id");
				}
				if(itemID==0||itemID!=item.getId()) {
					return "This ID doesn't exist perhaps create it or read all to see ID's";
				}else {
					stmt.executeUpdate("UPDATE items SET quantity = '" + item.getQuantity() + "', price = '" + item.getPrice() + "' WHERE product_id = " +item.getId());
					return "Updated Item";
				}
			}finally {
				rs1.close();
			}
		}
		
		public String deleteItem(Item item) throws SQLException {
			int itemID = 0;
			ResultSet rs1 = stmt.executeQuery("SELECT product_id from items");
			try{
				while(rs1.next()) {
					itemID = rs1.getInt("product_id");
				}
				if(itemID==0||itemID!=item.getId()) {
					return "This ID doesn't exist read all to see ID's";
				}else {
					stmt.executeUpdate("DELETE FROM items WHERE product_id = " + item.getId());
					return "Item deleted";
				}
			}finally {
				rs1.close();
			}
		}
	
}
