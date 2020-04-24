package com.qa.main.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.main.tables.Orders;

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
	
	
public String createOrder(Orders order) throws SQLException {
		float price = 0;
		int cid =0;
		int pid =0;
		int stock =0;
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
		ResultSet rs5 = stmt.executeQuery("SELECT(quantity-"+order.getQuantity()+") as stock from items where product_id = "+order.getPid());
		while(rs5.next()) {
			stock = rs5.getInt("stock");
		}
	////////////////////////////////////////////////////////////////////////////////////////	
		try{
			if(pid==0||pid!=order.getPid()) {
				return "this item does not exist perhaps read all items to view ID's";
			}else if(cid==0||cid!=order.getCid()) {
				return "Cant find this customer id sorry perhaps read all customers to view ID's";
			}else if(orderProd.contains(order.getPid())) {
				return "This item is in an existing order please update rather than create";
			}else if (stock<0){
				return "Insufficient stock";
			}else {
				stmt.executeUpdate("Insert into orders (customer_id,product_id,quantity, total)" + " VALUES ('" + order.getCid() + "', '" + order.getPid() + "', '" + order.getQuantity() + "', '" + (price*order.getQuantity())+ "')");
				stmt.executeUpdate("UPDATE items set quantity = '"+stock+"' where product_id = "+order.getPid());
				return "created";
			}
		}finally {
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			rs5.close();
		}
	}

public String deleteOrder(Orders order) throws SQLException {
	int orderID =0;
	int stock = 0;
	int orderProd = 0;
	ResultSet rs = stmt.executeQuery("Select order_id,product_id from orders where order_id = "+order.getOid());
	while(rs.next()) {
		orderID = rs.getInt("order_id");
		orderProd = rs.getInt("product_id");
	}
	////////////////////////////////////////
	ResultSet rs2 = stmt.executeQuery("select (items.quantity + orders.quantity) as stock from items join orders on orders.product_id = items.product_id where order_id = "+order.getOid());
	while(rs2.next()) {
		stock = rs2.getInt("stock");
	}
	/////////////////////////////////////////
	try{
		if(orderID==0) {
			return "order ID does not match";
		}else {
			stmt.executeUpdate("DELETE from orders WHERE order_id = "+ order.getOid());
			stmt.executeUpdate("UPDATE items set quantity = '"+stock+"' where product_id = "+orderProd);
			return "order deleted and stock updated";
		}
	}finally {
		rs.close();
		rs2.close();
	}
	
	
}

public String updateOrder(Orders order) throws SQLException {
	float price=0;
	int orderID=0;
	int orderProd=0;
	int orderCust = 0;
	int prodID=0;
	int stock = 0;
	int item_quantity=0;
	List<Integer> prodbyCust = new ArrayList<>();
	
	//////////////////////setting calc for total price////////////////////////////////////////////////////
	ResultSet rs = stmt.executeQuery("SELECT price FROM items where product_id = "+ order.getPid());
	while(rs.next()) {
		price = rs.getFloat("price");
	}
	
	///////////////////////////getting the order ID matches or not /////////////////////////////////////////////
	ResultSet rs2 = stmt.executeQuery("Select order_id,customer_id,product_id,quantity from orders where order_id = "+ order.getOid());
	while(rs2.next()) {
		orderID=rs2.getInt("order_id");
		orderProd=rs2.getInt("product_id");
		orderCust=rs2.getInt("customer_id");
		stock=rs2.getInt("quantity");
	}
	
	
	/////////////////////next is setting the list for all items///////////////////////////////////////////
	ResultSet rs3 = stmt.executeQuery("SELECT product_id,quantity from items where product_id = "+order.getPid());
	while(rs3.next()) {
		prodID=rs3.getInt("product_id");
		item_quantity=rs3.getInt("quantity");
	}
	
	
	ResultSet rs4 = stmt.executeQuery("SELECT product_id from orders where customer_id = "+orderCust);
	while(rs4.next()) {
		prodbyCust.add(rs4.getInt("product_id"));
	}
	
	try{
		if(orderID!=order.getOid()||orderID ==0) {
			return "order id does not exist";
		}else if(prodID==0) {
			return "item does not exist";
		}else if((order.getQuantity()-stock)>item_quantity){
			return "insufficient Stock";
		}else if(order.getPid() == orderProd) {
			stmt.executeUpdate("UPDATE orders SET quantity = '" + order.getQuantity() + "', total = '" + (price*order.getQuantity()) + "' WHERE order_id = " +orderID);
			stmt.executeUpdate("UPDATE items set quantity = (quantity - '"+(order.getQuantity()-stock)+ "') where product_id = " +order.getPid());
			return "quantity updated";
		}else if(prodbyCust.contains(order.getPid())) {
			return "find order no. you have this product in seperate order to update or delete.";
		}else {
			stmt.executeUpdate("UPDATE orders SET product_id = '" + order.getPid() + "', quantity = '" + order.getQuantity() + "', total = '" + (price*order.getQuantity()) + "' WHERE order_id = " +orderID);
			stmt.executeUpdate("UPDATE items set quantity = (quantity - '"+order.getQuantity()+ "') where product_id = "+ order.getPid());
			stmt.executeUpdate("UPDATE items set quantity =(quantity +'"+stock+"') WHERE product_id = "+orderProd);
			//stmt.executeUpdate("UPDATE items set quantity = (quantity + '"+stock+ "' where product_id = "+ orderProd);
			return "basket changed,item stock updated";
		}
	}finally {
		rs.close();
		rs2.close();
		rs3.close();
		rs4.close();
	}
}

public String readOrder(Orders order) throws SQLException{
	String x="";
	Double tot = 0D;
	int quant = 0;
	ResultSet rs1 =  stmt.executeQuery("Select first_name,last_name from customers join orders on orders.customer_id = customers.customer_id where order_id = "+order.getOid());
	while(rs1.next()) {
		String name = rs1.getString("first_name")+" "+rs1.getString("last_name");
		x+= name + " ";
	}
	ResultSet rs2 =  stmt.executeQuery("Select name from items join orders on orders.product_id = items.product_id where order_id = "+order.getOid());
	while(rs2.next()) {
		String prod = rs2.getString("name");
		x+=prod + " ";
	}
	ResultSet rs3 = stmt.executeQuery("select quantity, total from orders where order_id = "+order.getOid());
	while(rs3.next()) {
		 tot = rs3.getDouble("total");
		 quant = rs3.getInt("quantity");
	}
	try{
		if(x.equals("")) {
			return "order does not exist read all orders to view ID's";
		}else {
			return x+quant +" Total £"+tot;
		}
	}finally {
		rs1.close();
		rs2.close();
		rs3.close();
	}
}

public String readAllOrders() throws SQLException {
	String name = "";
	ResultSet rs2 =  stmt.executeQuery("Select order_id, first_name,last_name,name,placed,total from customers join orders on orders.customer_id = customers.customer_id join items on orders.product_id = items.product_id");
	try{
		while(rs2.next()) {
			name += rs2.getInt("order_id")+" "+rs2.getString("first_name")+" "+rs2.getString("last_name")+" " +rs2.getString("name")+" "+rs2.getString("placed")+"  £"+rs2.getString("total") +"\n";
		}
	}finally {
		rs2.close();
	}
	return name;
}






}
