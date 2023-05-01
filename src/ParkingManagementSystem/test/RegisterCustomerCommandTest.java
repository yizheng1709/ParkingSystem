package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.ParkingOffice;
import ParkingManagementSystem.RegisterCustomerCommand;

class RegisterCustomerCommandTest {

	@Test
	void test() {
		ParkingOffice office = new ParkingOffice();
		RegisterCustomerCommand command = new RegisterCustomerCommand(office);
		
		assertEquals("RegisterCustomerCommand", command.getCommandName());
	}

}
