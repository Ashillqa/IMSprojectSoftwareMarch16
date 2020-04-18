package com.qa.main.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.main.tables.Customer;

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
	
	public String createCust(Customer customer) throws SQLException {
		String fn = customer.getFirstName().replaceAll("[^A-Z]", "!");
		String ln = customer.getSurname().replaceAll("[^A-Z]", "!");
		String num = customer.getNumber().replaceAll("[^0-9]", "!");
		if(customer.getNumber().isEmpty()|| num.contains("!")||!(customer.getNumber().startsWith("0"))||customer.getNumber().length()!=11) {
			return "not a valid number";
		}else if(customer.getFirstName().isEmpty()||customer.getSurname().isEmpty()) {
			return "Can't have an empty first or last name";
		}else if(fn.contains("!")||ln.contains("!")) {
			return "Enter a valid name try again";
		}else {
			stmt.executeUpdate("INSERT INTO customers (first_name,last_name,number)"+ "VALUES('" + customer.getFirstName()+"', '" +customer.getSurname()+"', '" +customer.getNumber()+"')");
			return "created";
		}
	}
	//READ YOUR TABLE = SELECT THEREFORE EXECUTE QUERY
	
	public String readCust(Customer customer) throws SQLException {
		int custID=0;
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE customer_id = "+customer.getId());
		
		try{
			while (rs.next()) {
			custID = rs.getInt("customer_id");
		}
		if(custID==0) {
			return "customer ID does not exist";
		}else {
			String name = rs.getString("first_name")+ " " + rs.getString("last_name")+" "+rs.getString("number");
			return name;
		}
		}finally {
			rs.close();
		}
	}
	
	public String readAllCust() throws SQLException{
		String result = "";
		ResultSet rs = stmt.executeQuery("SELECT * FROM customers");
		try{
			while (rs.next()) {
				result += rs.getInt("customer_id")+" "+rs.getString("first_name")+ " " + rs.getString("last_name")+" "+rs.getString("number")+"\n";
			}
		}finally {
				rs.close();
			}
		return result;
	}
	
	
	
	
	
		
		//UPDATE ONE OF YOUR TABLES HENCE EXECUTE UPDATE
		
		public String updateCust(Customer customer) throws SQLException {
			int custID =0;
			String fn = customer.getFirstName().replaceAll("[^A-Z]", "!");
			String ln = customer.getSurname().replaceAll("[^A-Z]", "!");
			String num = customer.getNumber().replaceAll("[^0-9]", "!");
			
			ResultSet rs = stmt.executeQuery("SELECT customer_id from customers where customer_id = "+customer.getId());
			try{
				while(rs.next()) {
					custID = rs.getInt("customer_id");
				}
				if(customer.getNumber().isEmpty()|| num.contains("!")||!(customer.getNumber().startsWith("0"))||customer.getNumber().length()!=11) {
					return "invalid number entry remember enter same number to keep the existing record";
				}else if(custID==0) {
					return "Customer does not exist";
				}else if(customer.getFirstName().isEmpty()||customer.getSurname().isEmpty()){
					return "Can't have an empty first or last name";
				}else if(fn.contains("!")||ln.contains("!")){
					return "Enter a valid name";
				}else {
					stmt.executeUpdate("UPDATE customers SET first_name = '"+ customer.getFirstName() + "', last_name = '" + customer.getSurname() + "',number = '"+customer.getNumber()+"' WHERE customer_id = " +customer.getId());
					return "updated";
				}
			}finally {
				rs.close();
			}
		}
		
		//DELETE
		public String deleteCust(Customer customer) throws SQLException {
			int cust_id = 0;
			ResultSet rs1 = stmt.executeQuery("SELECT customer_id from customers WHERE customer_id = "+ customer.getId());
			try{
				while(rs1.next()) {
					cust_id = rs1.getInt("customer_id");
				}
				if(cust_id == 0) {
					return "This ID doesn't exist!";
				}else {
					stmt.executeUpdate("DELETE FROM customers WHERE customer_id = " + customer.getId());
					return "Deleted\n";
				}
			}finally {
				rs1.close();
			}
			
		}
	}


