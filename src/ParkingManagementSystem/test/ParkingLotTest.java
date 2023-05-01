package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.LongStayParkingChargeStrategy;
import ParkingManagementSystem.ParkingChargeStrategy;
import ParkingManagementSystem.ParkingLot;
import ParkingManagementSystem.factory.ParkingChargeFactory;
import ParkingManagementSystem.money.Money;

class ParkingLotTest {
	
	ParkingLot lot = new ParkingLot("Lot01", "University Library", null);

	@Test
	void test() {
		assertEquals("Lot01", lot.getId());
		assertEquals("University Library", lot.getName());
		assertEquals(Money.of(5.00), lot.getBaseRate());
	}
	
	@Test
	public void testSetAndGetStrategy() {
		ParkingChargeStrategy longStay;
		ParkingChargeFactory chargeFactory = new ParkingChargeFactory();
		
		longStay = chargeFactory.makeStrategy("longStay");
		
		lot.setStrategy(longStay);
		assertEquals(longStay, lot.getStrategy());
	}

}
