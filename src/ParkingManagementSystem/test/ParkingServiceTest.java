package ParkingManagementSystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.ParkingOffice;
import ParkingManagementSystem.ParkingService;

class ParkingServiceTest {
	ParkingOffice office = new ParkingOffice();
	ParkingService service  = new ParkingService(office);

	@Test
	void testRegisterCustomer() {
		
		service.performCommand("Register_CUSTOMER", "name=sahasra");
		assertEquals(office.getCustomer("CUST-12").getLastName(), "sahasra");
		
	}
}
