package com.qa.main.methods;

import java.sql.SQLException;
import java.util.Scanner;

import com.qa.main.DB.DBo;
import com.qa.main.tables.Orders;

public class OrderMethods {
	Scanner scanO = new Scanner(System.in);
	
	
	
	
	
	public void ActionsO(String act) throws SQLException {
		DBo orderDB = new DBo();
		try {
			if(act.equals("CREATE")) {
				System.out.println("Customer ID: ");
				int cid = Integer.parseInt(scanO.nextLine());
				System.out.println("product id: ");
				int pid = Integer.parseInt(scanO.nextLine());
				System.out.println("quantity: ");
				int quantity = Integer.parseInt(scanO.nextLine());
				Orders order = new Orders(0,cid,pid,quantity);
				orderDB.createOrder(order);
			}else if (act.equals("UPDATE")) {
				System.out.println("order ID: ");
				int oid = Integer.parseInt(scanO.nextLine());
				System.out.println("product id: ");
				int pid = Integer.parseInt(scanO.nextLine());
				System.out.println("quantity: ");
				int quantity = Integer.parseInt(scanO.nextLine());
				Orders order = new Orders(oid,pid,quantity);
				orderDB.updateOrder(order);
			}else if (act.equals("READ")) {
				System.out.println("ID(Enter 0 to view all: ");
				int oid = Integer.parseInt(scanO.nextLine());
				Orders order = new Orders(oid,0,0,0);
				if(order.getOid()==0) {
					orderDB.readAllOrders();
				}else {
					orderDB.readOrder(order);
				}
			}else {
				System.out.println("Enter the order ID to delete: ");
				int oid = Integer.parseInt(scanO.nextLine());
				Orders order = new Orders(oid,0,0,0);
				orderDB.deleteOrder(order);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			orderDB.Close();
		}
	}
	
	
	
	
	public void scanCloser() {
		scanO.close();
	}
	
	
	
	
	
	
	
	
}

