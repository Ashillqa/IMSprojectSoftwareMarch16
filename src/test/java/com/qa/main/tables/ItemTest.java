package com.qa.main.tables;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item("shoes",200,12,1);
		other = new Item("shoes",200,12,1);
	}
	

	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getQuantity());
		assertNotNull(item.getPrice());
		
		item.setName(null);
		assertNull(item.getName());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1, item.getId(), 0);
		assertEquals("shoes", item.getName());
		assertEquals(200, item.getQuantity());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}
	
	@Test
	public void ItemNameNullButOtherNameNotNull() {
		item.setName(null);
		assertFalse(item.equals(other));
	}
	
	@Test
	public void itemNamesNotEqual() {
		other.setName("hats");
		assertFalse(item.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setName(null);
		other.setName(null);
		assertTrue(item.equals(other));
	}
	
	
	
	
	@Test
	public void otherIdDifferent() {
		other.setId(2);
		assertFalse(item.equals(other));
	}
	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}
}
