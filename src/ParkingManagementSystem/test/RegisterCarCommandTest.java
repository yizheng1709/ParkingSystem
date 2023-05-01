package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.ParkingOffice;
import ParkingManagementSystem.RegisterCarCommand;

class RegisterCarCommandTest {

	@Test
	void test() {
		ParkingOffice office = new ParkingOffice();
		RegisterCarCommand command = new RegisterCarCommand(office);
		
		assertEquals("RegisterCarCommand", command.getCommandName());
	}

}
