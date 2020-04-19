package com.qa.main.DB;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.main.tables.Customer;
import com.qa.main.DB.DB;


@RunWith(MockitoJUnitRunner.class)
public class DBcTest {
	
	@Mock 
	private DB dbc;
	@InjectMocks
	private Customer customer = new Customer(2,"ash","prt","07769975501");
	
	@Test
	public void CustomerCreate() throws SQLException {
		
		dbc.createCust(customer);
		Mockito.verify(dbc, Mockito.times(1)).createCust(customer);
	}
	
	@Test
	public void CustomerUpdate() throws SQLException {
		dbc.updateCust(customer);
		Mockito.verify(dbc, Mockito.times(1)).updateCust(customer);
	}
	
	@Test
	public void CustomerDelete() throws SQLException{
		dbc.deleteCust(customer);
		Mockito.verify(dbc, Mockito.times(1)).deleteCust(customer);
	}
	
	@Test
	public void CustomerRead() throws SQLException{
		dbc.readCust(customer);
		Mockito.verify(dbc, Mockito.times(1)).readCust(customer);
	}
	@Test
	public void CustomerReadAll() throws SQLException {
		dbc.readAllCust();
		Mockito.verify(dbc,Mockito.times(1)).readAllCust();
	}
	

}



