package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.ParkingChargeStrategy;
import ParkingManagementSystem.factory.ParkingChargeFactory;

class ParkingChargeFactoryTest {

	@Test
	void test() {
		ParkingChargeFactory chargeFactory = new ParkingChargeFactory();
		ParkingChargeStrategy strategy;
		
		strategy = chargeFactory.makeStrategy("longStay");
		
		assertEquals(strategy, chargeFactory.getStrategy());
	}

}
