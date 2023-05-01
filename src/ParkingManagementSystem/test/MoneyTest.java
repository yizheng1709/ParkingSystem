package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.money.Money;

class MoneyTest {

	@Test
	void test() {
		Money m1 = Money.of(5);
		Money m2 = Money.of(10);
		
		assertEquals(Money.of(15), Money.times(m1, 3));
		assertEquals(Money.of(5), Money.subtract(m2, m1));
	}

}
