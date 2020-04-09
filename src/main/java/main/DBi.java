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
	
	public void createItem(Customer customer) throws SQLException {
		stmt.executeUpdate("INSERT INTO customers (first_name,last_name)"+ "VALUES('" + customer.getFirstName()+"', '" +customer.getSurname()+"')");
	}
	
	public void readItem(int id) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE customer_id = "+id);
		while (rs.next()) {
			String name = rs.getString("first_name")+ " " + rs.getString("last_name");
			System.out.println(name);
		}
	}
		
		public void updateItem(Customer customer) throws SQLException {
			stmt.executeUpdate("UPDATE customers SET first_name = '"+ customer.getFirstName() + "', last_name = '" + customer.getSurname() + "' WHERE customer_id = " +customer.getId());
			
		}
		
		public void deleteItem(Customer customer) throws SQLException {
			stmt.executeUpdate("DELETE FROM customers WHERE customer_id = " + customer.getId());
		}
	

}
