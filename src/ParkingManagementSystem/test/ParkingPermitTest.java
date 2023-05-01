package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.ParkingPermit;

class ParkingPermitTest {

	@Test
	public void testToString() {
		Car car = new Car();
		LocalDateTime expiration = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
		ParkingPermit permit = new ParkingPermit("P001", car, expiration);
		String expected = "ID: P001; expiration: 2023-04-30; Car: COMPACT ";
		assertEquals(expected, permit.toString());
	}
	
	@Test
	public void testIsExpired() {
		Car car = new Car();
		LocalDateTime expiration = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
		ParkingPermit permit = new ParkingPermit("P001", car, expiration);
		assertFalse(permit.isExpired());
		LocalDateTime now = LocalDateTime.now();
		expiration = now.minusDays(1);
		permit = new ParkingPermit("P001", car, expiration);
		assertTrue(permit.isExpired());
	}
	
}
