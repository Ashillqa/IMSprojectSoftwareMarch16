package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBo {
	
	private Connection conn;
	private Statement stmt;
	
	public DBo() throws SQLException{
		conn = DriverManager.getConnection(DBConfig.DB_URL,DBConfig.USER,DBConfig.PASS);
		stmt = conn.createStatement();
	}
	
	public void Close() throws SQLException {
		conn.close();
	}
	
	
public void createOrder(Orders order) throws SQLException {
		
		ResultSet rs = stmt.executeQuery("SELECT price FROM items where product_id = "+ order.getPid());
		stmt.executeUpdate("Insert into orders (customer_id,product_id,quantity, total)" + " VALUES ('" + order.getCid() + "', '" + order.getPid() + "', '" + order.getQuantity() + "', '" + (rs.getDouble("price")*order.getQuantity())+ "')");
		
	}

public void deleteOrder(Orders order) throws SQLException {
	
	stmt.executeUpdate("DELETE from orders WHERE order_id = "+ order.getOid());
}

public void updateOrder(Orders order) throws SQLException {
	ResultSet rs = stmt.executeQuery("SELECT price FROM items where product_id = "+ order.getPid());
	stmt.executeUpdate("UPDATE orders set product_id = '"+order.getPid()+"', quantity = '"+order.getQuantity()+"', total = '"+(rs.getDouble("price")*order.getQuantity())+"' WHERE customer_id = "+order.getCid());
}

public void readOrder(Orders order) throws SQLException{
	
}

}
