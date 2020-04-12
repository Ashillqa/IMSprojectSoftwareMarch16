package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tables.Item;

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
	
	public void createItem(Item item) throws SQLException {
		List<String> itemName = new ArrayList<>();
		String prodName = item.getName().replaceAll("[^0-9_-£!\"$%^&*()+=`¬|\\<>.,:;'@#~/?]", "");
		ResultSet rs1 = stmt.executeQuery("SELECT name from items");
		while(rs1.next()) {
			itemName.add(rs1.getString("name"));
		}
		if(item.getName().isEmpty()) {
			System.out.println("can't have an empty name");
		}else if((itemName.contains(item.getName()))) {
			System.out.println("This item already exists please update or delete");
		}else if (prodName.isEmpty()) {
			stmt.executeUpdate("INSERT INTO items (name,quantity,price)"+ "VALUES('" + item.getName()+"', '" +item.getQuantity()+"', '" +item.getPrice()+"')");
		}else {
			System.out.println("Eneter a valid item name please");
		}
	}
	
	public void readItem(Item item) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM items WHERE product_id = "+item.getId());
		while (rs.next()) {
			if(rs.getString("name").isEmpty()) {
				System.out.println("item ID does not exist perhaps try readAll ");
			}else {
				String name = "product: " + rs.getString("name")+ "\nStock: " + rs.getInt("quantity")+ "\nPrice: " + rs.getFloat("price");
				System.out.println(name);
			}
		}
	}
	
	public void readAllItems() throws SQLException{
		ResultSet rs = stmt.executeQuery("SELECT * from items");
		while(rs.next()) {
			String name = "product: " + rs.getString("name")+ "\nStock: " + rs.getInt("quantity")+ "\nPrice: £" + rs.getFloat("price")+"\n";
			System.out.println(name);
		}
	}
		
		public void updateItem(Item item) throws SQLException {
			List<Integer> itemID = new ArrayList<>();
			ResultSet rs1 = stmt.executeQuery("SELECT product_id from items");
			while(rs1.next()) {
				itemID.add(rs1.getInt("product_id"));
			}
			if(itemID.contains(item.getId())) {
				stmt.executeUpdate("UPDATE items SET quantity = '" + item.getQuantity() + "', price = '" + item.getPrice() + "' WHERE product_id = " +item.getId());
				System.out.println("Updated Item");
			}else {
				System.out.println("This ID doesn't exist perhaps create it");
			}
			
		}
		
		public void deleteItem(Item item) throws SQLException {
			List<Integer> itemID = new ArrayList<>();
			ResultSet rs1 = stmt.executeQuery("SELECT product_id from items");
			while(rs1.next()) {
				itemID.add(rs1.getInt("product_id"));
			}
			if(itemID.contains(item.getId())) {
				stmt.executeUpdate("DELETE FROM items WHERE product_id = " + item.getId());
				System.out.println("Item deleted");
			}else {
				System.out.println("This ID doesn't exist");
			}
		}
	
}
