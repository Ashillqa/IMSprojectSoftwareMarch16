package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private Connection conn;
	private Statement stmt;
	
	public DB() throws SQLException{
		conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);
		stmt = conn.createStatement();
	}
	
	public void Close() throws SQLException {
		conn.close();
	}
	//START YOUR CRUD STATEMENTS HERE
	//CREATE YOUR ENTRY I.E. INSERT
	
	public void createCust(Customer customer) throws SQLException {
		stmt.executeUpdate("INSERT INTO customers (first_name,last_name)"+ "VALUES('" + customer.getFirstName()+"', '" +customer.getSurname()+"')");
	}
	
	//READ YOUR TABLE = SELECT THEREFORE EXECUTE QUERY
	
	public void readCust(int id) throws SQLException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE customer_id = "+id);
		while (rs.next()) {
			String name = rs.getString("first_name")+ " " + rs.getString("last_name");
			System.out.println(name);
		}
	}
		
		//UPDATE ONE OF YOUR TABLES HENCE EXECUTE UPDATE
		
		public void updateCust(Customer customer) throws SQLException {
			stmt.executeUpdate("UPDATE customers SET first_name = '"+ customer.getFirstName() + "', last_name = '" + customer.getSurname() + "' WHERE customer_id = " +customer.getId());
			
		}
		
		//DELETE
		public void deleteCust(Customer customer) throws SQLException {
			stmt.executeUpdate("DELETE FROM customers WHERE customer_id = " + customer.getId());
		}
	}


