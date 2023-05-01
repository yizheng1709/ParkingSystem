package ParkingManagementSystem;

import java.time.LocalDateTime;

import ParkingManagementSystem.money.Money;

public interface ParkingChargeStrategy {
	Money calculateParkingCharge(ParkingPermit pid, LocalDateTime entry, LocalDateTime exit);
}

