package main;


import java.util.Scanner;
import java.sql.SQLException;

public class Ims {
	
	private static Scanner scan = new Scanner(System.in);
	private static String dispCat() {
		System.out.println("choose Customer, Item or Order");
		return scan.nextLine().toLowerCase();
	}
	
	private static String chooseAction() {
		System.out.println("choose Create, Read, Update or Delete ");
		return scan.nextLine().toUpperCase();
	}
	
	public void tester() throws SQLException {
		String xyz  = Ims.dispCat();
		String action = Ims.chooseAction();
		
		switch(xyz) {
		
		case "customer":
			System.out.println("first name: ");
			String firstName = scan.nextLine();
			System.out.println("last: ");
			String surname = scan.nextLine();
			
			if(action.equals("READ")||action.equals("DELETE")||action.equals("UPDATE")) {
				System.out.println("ID: ");
				int id = Integer.parseInt(scan.nextLine());
				Customer gay = new Customer(firstName,surname,id);
				ActionsC(action,gay);
				break;
			}
			Customer gay = new Customer(firstName,surname);
			System.out.println(gay.toString());
			ActionsC(action, gay);
			break;
		case "item":
			if(action.equals("READ")) {
				break;
			}
			System.out.println("called ");		
			break;
		case "Order":
			break;
		}
			
		}
	
	
	
	public void ActionsC(String act,Customer var) throws SQLException {
		DB r = new DB();
		switch(act) {
		case "CREATE":
			r.createCust(var);
			System.out.println("created");	
			break;
		case "UPDATE":
			r.updateCust(var);
			System.out.println("cjk");
			break;
			
		case "READ":
			r.readCust(var.getId());
			break;
		case "DELETE":
			r.deleteCust(var);
			System.out.println("deleted");
		}
	}
}
	


