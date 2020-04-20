package com.qa.main.methods;


	import java.sql.SQLException;

	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.Mockito;
	import org.mockito.Spy;
	import org.mockito.junit.MockitoJUnitRunner;

	import com.qa.main.tables.Item;
	import com.qa.main.DB.DBi;


	@RunWith(MockitoJUnitRunner.class)
	public class DBiTest {
		
		@Mock 
		private DBi dbi;
		@InjectMocks
		private Item item = new Item("crisps",25,3.50F);;
		
		@Test
		public void ItemCreate() throws SQLException {
			
			dbi.createItem(item);
			Mockito.verify(dbi, Mockito.times(1)).createItem(item);
		}
		
		@Test
		public void CustomerUpdate() throws SQLException {
			dbi.updateItem(item);
			Mockito.verify(dbi, Mockito.times(1)).updateItem(item);
		}
		
		@Test
		public void CustomerDelete() throws SQLException{
			dbi.deleteItem(item);
			Mockito.verify(dbi, Mockito.times(1)).deleteItem(item);
		}
		
		@Test
		public void CustomerRead() throws SQLException{
			dbi.readItem(item);
			Mockito.verify(dbi, Mockito.times(1)).readItem(item);
		}
		@Test
		public void CustomerReadAll() throws SQLException {
			dbi.readAllItems();
			Mockito.verify(dbi,Mockito.times(1)).readAllItems();
		}
		

	}


