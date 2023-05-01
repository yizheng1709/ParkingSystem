package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.ParkingLot;
import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.ParkingTransaction;
import ParkingManagementSystem.money.Money;

class ParkingTransactionTest {

	@Test
	void test() {
		LocalDateTime date = LocalDateTime.of(2023, 4, 18, 10, 30);
	    ParkingPermit permit = new ParkingPermit("P001", new Car(), LocalDateTime.of(2023, 4, 20, 12, 0));
	    ParkingLot parkingLot = new ParkingLot("PL001", "West Building", null);
	    Money chargedAmount = Money.of(3);
	
	    ParkingTransaction transaction = new ParkingTransaction(date, permit, parkingLot, chargedAmount);
	    
	    assertEquals(date, transaction.getDate());
	    assertEquals(permit, transaction.getPermit());
	    assertEquals(parkingLot, transaction.getParkingLot());
	    assertEquals(chargedAmount, transaction.getChargedAmount());
	}

}
