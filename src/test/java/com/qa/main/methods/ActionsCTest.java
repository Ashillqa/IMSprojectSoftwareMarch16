package com.qa.main.methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.main.DB.DB;
import com.qa.main.tables.Customer;

public class ActionsCTest {
	
	
	@Mock
	CustomerMethods cm = Mockito.mock(CustomerMethods.class);
	
	@InjectMocks
	private String act = "CREATE";
	
	@Test
	public void ActionTest() throws SQLException {
		
		cm.ActionsC(act);
		Mockito.verify(cm, Mockito.times(1)).ActionsC(act);
		
		
	}

}
