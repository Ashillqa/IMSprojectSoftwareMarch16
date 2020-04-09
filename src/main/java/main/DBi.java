package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		stmt.executeUpdate("INSERT INTO items (name,quantity,price)"+ "VALUES('" + item.getName()+"', '" +item.getQuantity()+"', '" +item.getPrice()+"')");
	}
	
	public void readItem(int id) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM items WHERE product_id = "+id);
		while (rs.next()) {
			String name = rs.getString("name")+ " " + rs.getInt("quantity")+ " " + rs.getFloat("price");
			System.out.println(name);
		}
	}
		
		public void updateItem(Item item) throws SQLException {
			stmt.executeUpdate("UPDATE items SET name = '"+ item.getName() + "', quantity = '" + item.getQuantity() + "', price = '" + item.getPrice() + "' WHERE product_id = " +item.getId());
			
		}
		
		public void deleteItem(int id) throws SQLException {
			stmt.executeUpdate("DELETE FROM items WHERE product_id = " + id);
		}
	

}
