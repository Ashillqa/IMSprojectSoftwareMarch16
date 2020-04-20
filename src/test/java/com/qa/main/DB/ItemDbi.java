package com.qa.main.DB;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.qa.main.tables.Item;




public class ItemDbi{
	
	@Mock
	 private DBi dbi = Mockito.mock(DBi.class);
	
	
	@Test
	public void createTest() throws SQLException {
		Item item1 = new Item("",20,4);
		Item item2 = new Item("hat",20,4);
		Item item3 = new Item("h4t5",50,3.5F);
		
		
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4));
		
		Mockito.when(dbi.createItem(item2)).thenReturn("exists");
		Mockito.when(dbi.createItem(item1)).thenReturn("empty");
		Mockito.when(dbi.createItem(item3)).thenReturn("invalid entry");
		
		assertEquals("exists",dbi.createItem(item2));
		assertEquals("empty",dbi.createItem(item1));
		assertEquals("invalid entry",dbi.createItem(item3));
	}
	
	@Test
	public void UpdateTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,1));
		itemList.add(new Item("gloves",15,4,2));
		
		Item item1 = new Item("",5,0,1);
		Item item2 = new Item("",5,0,5);
		Item item3 = new Item("",-2,0,2);
		
		Mockito.when(dbi.updateItem(item1)).thenReturn("updated");
		Mockito.when(dbi.updateItem(item2)).thenReturn("non existent");
		Mockito.when(dbi.updateItem(item3)).thenReturn("invalid quantity");
		
		assertEquals("updated",dbi.updateItem(item1));
		assertEquals("non existent",dbi.updateItem(item2));
		assertEquals("invalid quantity",dbi.updateItem(item3));
	}
	
	@Test
	public void readTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,1));
		
		Item item1 = new Item("",0,0,1);
		Item item2 = new Item("",0,0,4);
		
		Mockito.when(dbi.readItem(item2)).thenReturn("item does not exist");
		Mockito.when(dbi.readItem(item1)).thenReturn(itemList.get(0).getName()+" "+itemList.get(0).getQuantity());
		assertEquals("hat 20",dbi.readItem(item1));
		assertEquals("item does not exist",dbi.readItem(item2));
	}
	@Test
	public void readAllTest() throws SQLException {
		List<Item> itemList = new ArrayList<>();
		itemList.add(new Item("hat",20,4,1));
		itemList.add(new Item("gloves",15,4,3));
		
		String res = itemList.get(0).getName()+" "+itemList.get(0).getQuantity()+"\n"+itemList.get(1).getName()+" "+itemList.get(1).getQuantity();
				
		Mockito.when(dbi.readAllItems()).thenReturn(res);
		assertEquals(res,dbi.readAllItems());
		
	}

}
