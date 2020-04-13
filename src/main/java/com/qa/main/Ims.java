package com.qa.main;


import java.util.Scanner;
import com.qa.main.methods.CustomerMethods;
import com.qa.main.methods.ItemMethods;
import com.qa.main.methods.OrderMethods;

import java.sql.SQLException;

public class Ims {
	
	private static Scanner scan = new Scanner(System.in);
	
	
	/////////////////////////////////////////////////////////
	
	private static String dispCat() {
		
		System.out.println("choose Customers, Items, Orders or Quit");
		String x = scan.nextLine().toLowerCase();
		if(x.equals("customers")||x.equals("orders")||x.equals("items")||x.equals("quit")) {
			return x;
		}else {
			return dispCat();
		}
		}
////////////////////////////////////////////////////////////////////////////////////////////
	
	private static String chooseAction() {
		System.out.println("choose Create, Read, Update or Delete ");
		String y = scan.nextLine().toUpperCase();
		if(y.equals("CREATE")||y.equals("READ")||y.equals("UPDATE")||y.equals("DELETE")) {
			return y;
		}else {
			return chooseAction();
		}
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void tester() throws SQLException {
		boolean using = true;
		while(using ==true) {
			CustomerMethods Cmethod = new CustomerMethods();
			ItemMethods Im = new ItemMethods();
			OrderMethods Om = new OrderMethods();
			String xyz  = Ims.dispCat();
			if(xyz.equals("quit")) {
				scan.close();
				Cmethod.scanCloser();
				Im.scanCloser();
				Om.scanCloser();
				System.out.println("see you soon!");
				using=false;
				break;
			}
			try {
				String action = Ims.chooseAction();
				switch(xyz) {
				case "customers":
					Cmethod.ActionsC(action);
					break;
				case "items":
					Im.ActionsI(action);
					break;
				case "orders":
					Om.ActionsO(action);
					break;
				}
			}finally {
				System.out.println("redirecting");
			}
		}
	}	
}