package com.qa.main.DB;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.main.tables.Orders;
import com.qa.main.DB.DBo;


@RunWith(MockitoJUnitRunner.class)


public class DBoTest {
	@Mock
	private DBo dbo;
	@InjectMocks
	private Orders order = new Orders(2,5,4,4);
	
	@Test
	public void OrderCreate() throws SQLException {
		dbo.createOrder(order);
		Mockito.verify(dbo,Mockito.times(1)).createOrder(order);
	}
	@Test
	public void OrderUpdate() throws SQLException {
		dbo.updateOrder(order);
		Mockito.verify(dbo,Mockito.times(1)).updateOrder(order);
	}
	@Test
	public void OrderDelete() throws SQLException {
		dbo.deleteOrder(order);
		Mockito.verify(dbo,Mockito.times(1)).deleteOrder(order);
	}
	@Test
	public void OrderRead() throws SQLException {
		dbo.readOrder(order);
		Mockito.verify(dbo,Mockito.times(1)).readOrder(order);
	}
	@Test
	public void OrderReadAll() throws SQLException {
		dbo.readAllOrders();
		Mockito.verify(dbo,Mockito.times(1)).readAllOrders();
	}
	

}
