package com.qa.main.tables;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrdersTest {
	private Orders order;
	private Orders other;
	
	@Before
	public void setUp() {
		order = new Orders(2,3,4,25);
		other = new Orders(2,3,4,25);
	}
	

	@Test
	public void settersTest() {
		assertNotNull(order.getOid());
		assertNotNull(order.getCid());
		assertNotNull(order.getQuantity());
		assertNotNull(order.getPid());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(2, order.getOid(), 0);
		assertEquals(3, order.getCid());
		assertEquals(25, order.getQuantity());
		assertEquals(4,order.getPid());
	}
	
	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}
	@Test
	public void otherIdDifferent() {
		other.setOid(1);
		assertFalse(order.equals(other));
	}
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
}
