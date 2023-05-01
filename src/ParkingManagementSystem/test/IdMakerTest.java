package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.IdMaker;

class IdMakerTest {

	@Test
	public void testMakeId() {
	    
	    assertEquals("CUST-2", IdMaker.makeId("CUST-"));
	    
	  }

}
