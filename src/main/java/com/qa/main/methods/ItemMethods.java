package com.qa.main.methods;

import java.sql.SQLException;
import java.util.Scanner;

import com.qa.main.DB.DBi;
import com.qa.main.tables.Item;

public class ItemMethods {
	Scanner scanI = new Scanner(System.in);
	
	
	
	public void ActionsI(String act) throws SQLException {
		DBi itemDB = new DBi();
		try {
			if(act.equals("CREATE")) {
				System.out.println("name");
				String name = scanI.nextLine().toLowerCase();
				System.out.println("quantity");
				int quant = Integer.parseInt(scanI.nextLine());
				System.out.println("price");
				float price = Float.parseFloat(scanI.nextLine());
				Item product = new Item(name,quant,price);
				itemDB.createItem(product);
			}else if(act.equals("UPDATE")) {
				System.out.println("Specify the product ID to apply this change to: ");
				int pid = Integer.parseInt(scanI.nextLine());
				System.out.println("quantity");
				int quant = Integer.parseInt(scanI.nextLine());
				System.out.println("price");
				float price = Float.parseFloat(scanI.nextLine());
				Item product = new Item("",quant,price,pid);
				itemDB.updateItem(product);
			}else if(act.equals("READ")) {
				System.out.println("ID(Enter 0 to view all): ");
				int pid = Integer.parseInt(scanI.nextLine());
				Item product = new Item("",0,0,pid);
				if(product.getId()==0) {
					itemDB.readAllItems();
				}else {
					itemDB.readItem(product);
				}
			}else {
				System.out.println("Enter the Product ID of the product to delete: ");
				int pid = Integer.parseInt(scanI.nextLine());
				Item product = new Item("",0,0,pid);
				itemDB.deleteItem(product);
			}
		}catch(Exception e) {
			System.out.println("something went wrong");
		}finally {
			itemDB.Close();
		}
	}

	
	
	
	public void scanCloser() {
		scanI.close();
	}
	
	
	
	
}
