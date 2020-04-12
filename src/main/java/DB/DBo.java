package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tables.Orders;

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
		float price = 0;
		int cid =0;
		int pid =0;
		List<Integer> orderProd = new ArrayList<>();
		ResultSet rs1 = stmt.executeQuery("SELECT price FROM items where product_id = "+ order.getPid());
		while(rs1.next()) {
			price = rs1.getFloat("price");			
		}
		ResultSet rs2 = stmt.executeQuery("SELECT product_id from orders where customer_id = "+order.getCid());
		while(rs2.next()) {
			orderProd.add(rs2.getInt("product_id"));
		}
		
		ResultSet rs3 = stmt.executeQuery("SELECT product_id FROM items WHERE product_id = "+order.getPid());
		while(rs3.next()) {
			pid=rs3.getInt("product_id");			
		}
		ResultSet rs4 = stmt.executeQuery("SELECT customer_id FROM customers where customer_id = "+ order.getCid());
		while(rs4.next()) {
			cid = rs4.getInt("customer_id");			
		}
		if(pid==0||pid!=order.getPid()) {
			System.out.println("this item does not exist");
		}else if(cid==0||cid!=order.getCid()) {
			System.out.println("Cant find this customer id sorry");
		}else if(orderProd.contains(order.getPid())) {
			System.out.println("This item is in an existing order please update rather than create");
		}else {
			stmt.executeUpdate("Insert into orders (customer_id,product_id,quantity, total)" + " VALUES ('" + order.getCid() + "', '" + order.getPid() + "', '" + order.getQuantity() + "', '" + (price*order.getQuantity())+ "')");
			System.out.println("created");
		}	
	}

public void deleteOrder(Orders order) throws SQLException {
	
	stmt.executeUpdate("DELETE from orders WHERE order_id = "+ order.getOid());
}

public void updateOrder(Orders order) throws SQLException {
	float price=0;
	int orderID=0;
	int orderProd=0;
	int orderCust = 0;
	int prodID=0;
	List<Integer> prodbyCust = new ArrayList<>();
	
	//////////////////////setting calc for total price////////////////////////////////////////////////////
	ResultSet rs = stmt.executeQuery("SELECT price FROM items where product_id = "+ order.getPid());
	while(rs.next()) {
		price = rs.getFloat("price");
	}
	
	///////////////////////////getting the order ID matches or not /////////////////////////////////////////////
	ResultSet rs2 = stmt.executeQuery("Select order_id,customer_id,product_id from orders where order_id = "+ order.getOid());
	while(rs2.next()) {
		orderID=rs2.getInt("order_id");
		orderProd=rs2.getInt("product_id");
		orderCust=rs2.getInt("customer_id");	
	}
	
	
	/////////////////////next is setting the list for all items///////////////////////////////////////////
	ResultSet rs3 = stmt.executeQuery("SELECT product_id from items where product_id = "+order.getPid());
	while(rs3.next()) {
		prodID=rs3.getInt("product_id");
	}
	
	
	ResultSet rs4 = stmt.executeQuery("SELECT product_id from orders where customer_id = "+orderCust);
	while(rs4.next()) {
		prodbyCust.add(rs4.getInt("product_id"));
	}
	
	if(orderID!=order.getOid()||orderID ==0) {
		System.out.println("order id does not exist");
	}else if(prodID==0) {
		System.out.println("item does not exist");
	}else if(order.getPid() == orderProd) {
		stmt.executeUpdate("UPDATE orders SET quantity = '" + order.getQuantity() + "', total = '" + (price*order.getQuantity()) + "' WHERE order_id = " +orderID);
		System.out.println("quantity updated");
	}else if(prodbyCust.contains(order.getPid())) {
		System.out.println("find order no. you have this product in seperate order to update or delete.");
	}else {
		stmt.executeUpdate("UPDATE orders SET product_id = '" + order.getPid() + "', quantity = '" + order.getQuantity() + "', total = '" + (price*order.getQuantity()) + "' WHERE order_id = " +orderID);
		System.out.println("basket changed");
	}
}

public void readOrder(Orders order) throws SQLException{
	String x="";
	ResultSet rs1 =  stmt.executeQuery("Select first_name from customers join orders on orders.customer_id = customers.customer_id where order_id = "+order.getOid());
	while(rs1.next()) {
		String name = rs1.getString("first_name");
		x+= name + " ";
	}
	ResultSet rs2 =  stmt.executeQuery("Select name from items join orders on orders.product_id = items.product_id where order_id = "+order.getOid());
	while(rs2.next()) {
		String prod = rs2.getString("name");
		x+=prod + " ";
	}
	ResultSet rs3 = stmt.executeQuery("select total from orders where order_id = "+order.getOid());
	while(rs3.next()) {
		Double tot = rs3.getDouble("total");
		System.out.println(x+" Total £"+tot);
	}
}


}
