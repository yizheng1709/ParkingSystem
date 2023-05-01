package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.PermitManager;

class PermitManagerTest {

	@Test
	void test() {
		PermitManager manager = new PermitManager();
		Car car1 = new Car();
		ParkingPermit permit1 = manager.register(car1);
		
		assertEquals(1, manager.getPermits().size());
		
		assertEquals(car1, permit1.getCar());
		assertFalse(permit1.isExpired());
		
		Car car2 = new Car();
		ParkingPermit permit2 = manager.register(car2);
		assertNotEquals(permit1.getId(), permit2.getId());
	}

}
