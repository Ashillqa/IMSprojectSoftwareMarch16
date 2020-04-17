package com.qa.main.methods;

import java.sql.SQLException;
import java.util.Scanner;

import com.qa.main.DB.DB;
import com.qa.main.tables.Customer;

public class CustomerMethods {
	Scanner scanC = new Scanner(System.in);
	
	
	public String ActionsC(String act) throws SQLException {
		DB custDB = new DB();
		try {
			if(act.equals("CREATE")){
				System.out.println("Enter the first name: ");
				String fname = scanC.nextLine().toUpperCase();
				System.out.println("Now the surname: ");
				String lname = scanC.nextLine().toUpperCase();
				System.out.println("Enter your mobile or landline");
				String number = scanC.nextLine();
				Customer customer = new Customer(0,fname,lname,number);
				return custDB.createCust(customer);	
			} else if (act.equals("READ")){
				System.out.println("ID(Enter Zero to view all): ");
				int cid = Integer.parseInt(scanC.nextLine());
				Customer customer = new Customer(cid);
				if(customer.getId()==0) {
					return custDB.readAllCust();
				}else {
					return custDB.readCust(customer);
				}
			}else if(act.equals("UPDATE")) {
				System.out.println("specify the Customer ID to apply this change to: ");
				int cid = Integer.parseInt(scanC.nextLine());
				System.out.println("Enter the new first name: ");
				String fname = scanC.nextLine().toUpperCase();
				System.out.println("Now the surname: ");
				String lname = scanC.nextLine().toUpperCase();
				System.out.println("new number or just repeat if same: ");
				String number = scanC.nextLine();
				Customer customer = new Customer(cid,fname,lname,number);
				return custDB.updateCust(customer);
			}else if(act.equals("DELETE")) {
				System.out.println("Enter the customer ID: ");
				int cid = Integer.parseInt(scanC.nextLine());
				Customer customer = new Customer(cid);
				return custDB.deleteCust(customer);
			}else {
				return "try again";
			}
		}catch(Exception e) {
			return e.getMessage();
		}finally {
			custDB.Close();
		}
	}
	
	
	
	
	
	
	public void scanCloser() {
		scanC.close();
	}
	
	
	
	
	
	
	
	
	
	
}


