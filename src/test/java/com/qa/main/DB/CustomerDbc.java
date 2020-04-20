package com.qa.main.DB;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.main.tables.Customer;


public class CustomerDbc {
	
	@Mock
	 private DB db = Mockito.mock(DB.class);
	private int custID=1;
	
	
	@Test
	public void createTest() throws SQLException {
		Customer cust1 = new Customer(0,"","","09012283883");
		Customer cust2 = new Customer(0,"mike","philips","07934567822");
		Customer cust3 = new Customer(0,"m1k3","philips","01162662331");
		Customer cust4 = new Customer(0,"mike","philips","079834758992285");
		
		
		Mockito.when(db.createCust(cust1)).thenReturn("empty name!");
		Mockito.when(db.createCust(cust2)).thenReturn("created");
		Mockito.when(db.createCust(cust3)).thenReturn("invalid name");
		Mockito.when(db.createCust(cust4)).thenReturn("invalid number");
		
		assertEquals("empty name!",db.createCust(cust1));
		assertEquals("created",db.createCust(cust2));
		assertEquals("invalid name",db.createCust(cust3));
		assertEquals("invalid number",db.createCust(cust4));
	}
	
	@Test
	public void UpdateTest() throws SQLException {
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(1,"mike","philips","01162662334"));
		custList.add(new Customer(2,"james","wilson","07897839277"));
		
		Customer cust1 = new Customer(1,"steve","philips","01162662334");
		Customer cust2 = new Customer(7,"steve","philips","01162662334");
		Customer cust3 = new Customer(1,"st3ve","ph1lips","01162662334");
		Customer cust4 = new Customer(1,"steve","philips","0116266$£$34");
		
		
		Mockito.when(db.updateCust(cust1)).thenReturn("updated");
		Mockito.when(db.updateCust(cust2)).thenReturn("non existent");
		Mockito.when(db.updateCust(cust3)).thenReturn("invalid name");
		Mockito.when(db.updateCust(cust4)).thenReturn("invalid number");
		
		
		assertEquals("updated",db.updateCust(cust1));
		assertEquals("non existent",db.updateCust(cust2));
		assertEquals("invalid name",db.updateCust(cust3));
		assertEquals("invalid number",db.updateCust(cust4));
	}
	
	@Test
	public void readTest() throws SQLException {
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(3,"Ashill","Pathak","07432523155"));
		
		Customer cust1 = new Customer(3,"","","");
		Customer cust2 = new Customer(2,"","","");
		
		
		Mockito.when(db.readCust(cust1)).thenReturn(custList.get(0).getFirstName()+" "+custList.get(0).getSurname());
		Mockito.when(db.readCust(cust2)).thenReturn("Customer does not exist");
		
		assertEquals("Ashill Pathak",db.readCust(cust1));
		assertEquals("Customer does not exist",db.readCust(cust2));
	}
	@Test
	public void readAllTest() throws SQLException {
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(3,"Ashill","Pathak","07432523155"));
		custList.add(new Customer(2,"Nitin","Pathak","07532563355"));
		
		String res = custList.get(0).getId()+" "+custList.get(0).getFirstName()+"\n"+custList.get(1).getId()+" "+custList.get(1).getFirstName();
				
		Mockito.when(db.readAllCust()).thenReturn(res);
		assertEquals(res,db.readAllCust());
		
	}


}
