package com.qa.main;


import java.util.Scanner;

import com.qa.main.DB.DB;
import com.qa.main.DB.DBi;
import com.qa.main.DB.DBo;
import com.qa.main.tables.Customer;
import com.qa.main.tables.Item;
import com.qa.main.tables.Orders;

import java.sql.SQLException;

public class Ims {
	
	private static Scanner scan = new Scanner(System.in);
	
	private static String dispCat() {
		
		System.out.println("choose Customers, Items, Orders or Quit");
		String x = scan.nextLine().toLowerCase();
		if(x.equals("customers")||x.equals("orders")||x.equals("items")||x.equals("quit")) {
			return x;
		}else {
			return dispCat();
		}
		}
	
	private static String chooseAction() {
		System.out.println("choose Create, Read, Update or Delete ");
		String y = scan.nextLine().toUpperCase();
		if(y.equals("CREATE")||y.equals("READ")||y.equals("UPDATE")||y.equals("DELETE")) {
			return y;
		}else {
			return chooseAction();
		}
	}
	
	public void tester() throws SQLException {
		boolean using = true;
		while(using ==true) {
			String xyz  = Ims.dispCat();
			if(xyz.equals("quit")) {
				scan.close();
				System.out.println("see you soon!");
				break;
			}
			try {
				String action = Ims.chooseAction();
				switch(xyz) {
				case "customers":
					if(action.equals("READ")) {
						System.out.println("ID(Enter Zero to view all): ");
						int cid = Integer.parseInt(scan.nextLine());
						Customer customer = new Customer("","",cid);
						ActionsC(action,customer);
						break;
					}
					if(action.equals("DELETE")) {
						System.out.println("Enter the customer ID: ");
						int cid = Integer.parseInt(scan.nextLine());
						Customer customer = new Customer("","",cid);
						ActionsC(action,customer);
						break;
					}
					System.out.println("Enter the first name: ");
					String fname = scan.nextLine().toUpperCase();
					System.out.println("Now the surname: ");
					String lname = scan.nextLine().toUpperCase();
					if(action.equals("CREATE")) {
						Customer customer = new Customer(fname,lname);
						ActionsC(action,customer);
						break;
					}
					if(action.equals("UPDATE")) {
						System.out.println("specify the Customer ID to apply this change to: ");
						int cid = Integer.parseInt(scan.nextLine());
						Customer customer = new Customer(fname,lname,cid);
						ActionsC(action,customer);
						break;
					}
				case "items":
					if(action.equals("READ")) {
						System.out.println("ID(Enter 0 to view all): ");
						int pid = Integer.parseInt(scan.nextLine());
						Item product = new Item("",0,0,pid);
						ActionsI(action,product);
						break;
					}
					if(action.equals("DELETE")) {
						System.out.println("Enter the Product ID of the product to delete: ");
						int pid = Integer.parseInt(scan.nextLine());
						Item product = new Item("",0,0,pid);
						ActionsI(action,product);
						break;
					}
					if(action.equals("CREATE")) {
						System.out.println("name");
						String name = scan.nextLine().toLowerCase();
						System.out.println("quantity");
						int quant = Integer.parseInt(scan.nextLine());
						System.out.println("price");
						float price = Float.parseFloat(scan.nextLine());
						Item product = new Item(name,quant,price);
						ActionsI(action,product);
						break;
					}
					if(action.equals("UPDATE")) {
						System.out.println("Specify the product ID to apply this change to: ");
						int pid = Integer.parseInt(scan.nextLine());
						System.out.println("quantity");
						int quant = Integer.parseInt(scan.nextLine());
						System.out.println("price");
						float price = Float.parseFloat(scan.nextLine());
						Item product = new Item("",quant,price,pid);
						ActionsI(action,product);
						break;
					}
				case "orders":
					if(action.equals("DELETE")) {
						System.out.println("Enter the order ID to delete: ");
						int oid = Integer.parseInt(scan.nextLine());
						Orders order = new Orders(oid,0,0,0);
						ActionsO(action,order);
						break;
					}
					if(action.equals("READ")) {
						System.out.println("ID(Enter 0 to view all: ");
						int oid = Integer.parseInt(scan.nextLine());
						Orders order = new Orders(oid,0,0,0);
						ActionsO(action,order);
						break;
					}
					if(action.equals("CREATE")) {
						System.out.println("Customer ID: ");
						int cid = Integer.parseInt(scan.nextLine());
						System.out.println("product id: ");
						int pid = Integer.parseInt(scan.nextLine());
						System.out.println("quantity: ");
						int quantity = Integer.parseInt(scan.nextLine());
						Orders order = new Orders(0,cid,pid,quantity);
						ActionsO(action,order);
						break;
					}
					if(action.equals("UPDATE")) {
						System.out.println("order ID: ");
						int oid = Integer.parseInt(scan.nextLine());
						System.out.println("product id: ");
						int pid = Integer.parseInt(scan.nextLine());
						System.out.println("quantity: ");
						int quantity = Integer.parseInt(scan.nextLine());
						Orders order = new Orders(oid,pid,quantity);
						ActionsO(action,order);
					}
				}
			}finally {
				System.out.println("redirecting");
			}
		}
	}
		
	public void ActionsC(String act,Customer cust) throws SQLException {
		DB custDB = new DB();
		try {
			switch(act) {
			case "CREATE":
				custDB.createCust(cust);	
				break;
			case "UPDATE":
				custDB.updateCust(cust);
				break;	
			case "READ":
				if(cust.getId()==0) {
					custDB.readAllCust();
					break;
				}
				custDB.readCust(cust);
				break;
			case "DELETE":
				custDB.deleteCust(cust.getId());
			}
		}catch(Exception e) {
			System.out.println("Something went wrong");
		}finally {
			custDB.Close();
		}
	}
	
	public void ActionsI(String act, Item prod) throws SQLException {
		DBi itemDB = new DBi();
		try {
			switch(act) {
			case "CREATE":
				itemDB.createItem(prod);
				break;
			case "UPDATE":
				itemDB.updateItem(prod);
				break;
			case "READ":
				if(prod.getId()==0) {
					itemDB.readAllItems();
					break;
				}
				itemDB.readItem(prod);
				break;
			case "DELETE":
				itemDB.deleteItem(prod);
			}
		}catch(Exception e) {
			System.out.println("something went wrong");
		}finally {
			itemDB.Close();
		}
	}
	public void ActionsO(String act, Orders order) throws SQLException {
		DBo orderDB = new DBo();
		try {
			switch(act) {
			case "CREATE":
				orderDB.createOrder(order);
				break;
			case "UPDATE":
				orderDB.updateOrder(order);
				break;
			case "READ":
				orderDB.readOrder(order);
				break;
			case "DELETE":
				orderDB.deleteOrder(order);
			}
		}catch (Exception e) {
			System.out.println("something went wrong");
		}finally {
			orderDB.Close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	


