package com.qa.main.DB;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.main.tables.Customer;
import com.qa.main.tables.Item;
import com.qa.main.tables.Orders;

public class OrderDBo {
	@Mock
	 private DBo dbo = Mockito.mock(DBo.class);
	
	
	@Test
	public void createTest() throws SQLException {
		
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,3));
		
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(2,"mike","philips","01162662334"));
		
		int cid = custList.get(0).getId();
		int pid = itemList.get(0).getId();
		
		Orders order1 = new Orders(0,cid,pid,15);
		Orders order2 = new Orders(0,4,pid,15);
		Orders order3 = new Orders(0,cid,6,15);

		Mockito.when(dbo.createOrder(order1)).thenReturn("created");
		Mockito.when(dbo.createOrder(order2)).thenReturn("customer invalid");
		Mockito.when(dbo.createOrder(order3)).thenReturn("product invalid");
		
		assertEquals("created",dbo.createOrder(order1));
		assertEquals("customer invalid",dbo.createOrder(order2));
		assertEquals("product invalid",dbo.createOrder(order3));
	}
	
	@Test
	public void UpdateTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,3));
		itemList.add(new Item("gloves",20,4,2));
		
		List<Orders> orderList = new ArrayList<>();
		orderList.add(new Orders(1,2,3,4));
		orderList.add(new Orders(2,2,5,4));
		
		int pid = itemList.get(0).getId();
		int oid = orderList.get(0).getOid();
		int pid2 = itemList.get(1).getId();
		
		Orders order1 = new Orders(oid,pid,7); //quant
		Orders order2 = new Orders(0,pid,15); //invalid orderid
		Orders order3 = new Orders(oid,pid2,9);//order updated
		Orders order4 = new Orders(oid,6,9); // invalid product
		Orders order5 = new Orders(oid,5,7); // product exists in different order 
		

		Mockito.when(dbo.updateOrder(order1)).thenReturn("quantity updated");
		Mockito.when(dbo.updateOrder(order2)).thenReturn("orderID invalid");
		Mockito.when(dbo.updateOrder(order3)).thenReturn("product updated");
		Mockito.when(dbo.updateOrder(order4)).thenReturn("productID invalid");
		Mockito.when(dbo.updateOrder(order5)).thenReturn("product Exists in different order");
		
		assertEquals("quantity updated",dbo.updateOrder(order1));
		assertEquals("orderID invalid",dbo.updateOrder(order2));
		assertEquals("product updated",dbo.updateOrder(order3));
		assertEquals("productID invalid",dbo.updateOrder(order4));
		assertEquals("product Exists in different order",dbo.updateOrder(order5));
	}
	
	@Test
	public void readTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,3));
		
		List<Orders> orderList = new ArrayList<>();
		orderList.add(new Orders(1,2,3,4));
		
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(2,"mike","philips","01162662334"));
		
		Orders order1 = new Orders(1,0,0);
		Orders order2 = new Orders(4,0,0);
		
		String res = orderList.get(0).getOid()+" "+custList.get(0).getFirstName()+" "+itemList.get(0).getName()+" "+orderList.get(0).getQuantity()+" "+(orderList.get(0).getQuantity()*itemList.get(0).getPrice());
		
		Mockito.when(dbo.readOrder(order1)).thenReturn(res);
		Mockito.when(dbo.readOrder(order2)).thenReturn("invalid orderID");
		
		assertEquals(res,dbo.readOrder(order1));
		assertEquals("invalid orderID",dbo.readOrder(order2));
	}
	@Test
	public void readAllTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,3));
		itemList.add(new Item("gloves",20,4,4));
		
		List<Orders> orderList = new ArrayList<>();
		orderList.add(new Orders(1,2,3,4));
		orderList.add(new Orders(2,5,4,9));
		
		List<Customer> custList = new ArrayList<>();
		custList.add(new Customer(2,"mike","philips","01162662334"));
		custList.add(new Customer(5,"john","philips","09162764334"));
		
		String res = orderList.get(0).getOid()+" "+custList.get(0).getFirstName()+" "+itemList.get(0).getName()+" "+orderList.get(0).getQuantity()+" "+(orderList.get(0).getQuantity()*itemList.get(0).getPrice()+"\n");
		String res2 = orderList.get(1).getOid()+" "+custList.get(1).getFirstName()+" "+itemList.get(1).getName()+" "+orderList.get(1).getQuantity()+" "+(orderList.get(1).getQuantity()*itemList.get(1).getPrice()+"\n");
		
		Mockito.when(dbo.readAllOrders()).thenReturn(res + res2);
		assertEquals(res+res2,dbo.readAllOrders());
		
	}


}
