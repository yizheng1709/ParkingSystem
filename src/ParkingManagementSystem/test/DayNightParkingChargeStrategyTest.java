package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.DayNightParkingChargeStrategy;
import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.money.Money;


class DayNightParkingChargeStrategyTest {

	@Test
void test() {
		
		DayNightParkingChargeStrategy strategy = new DayNightParkingChargeStrategy();
		ParkingPermit permit1 = new ParkingPermit("P0001", new Car(), LocalDateTime.now().plusYears(1));
		
		Money m = strategy.calculateParkingCharge(permit1, LocalDateTime.now(), LocalDateTime.now().plusHours(3));
		
		assertEquals(m, Money.of(4.80));
		
	}

}
