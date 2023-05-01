package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.LongStayParkingChargeStrategy;
import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.money.Money;

class LongStayParkingChargeStrategyTest {

	@Test
	void test() {
		LongStayParkingChargeStrategy strategy = new LongStayParkingChargeStrategy();
		ParkingPermit permit1 = new ParkingPermit("P0001", new Car(), LocalDateTime.now().plusYears(1));
		
		Money m = strategy.calculateParkingCharge(permit1, LocalDateTime.now(), LocalDateTime.now().plusDays(4));
		
		assertEquals(m, Money.of(51));
	}

}
