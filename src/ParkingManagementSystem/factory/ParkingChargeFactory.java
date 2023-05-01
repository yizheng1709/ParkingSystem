package ParkingManagementSystem.factory;

import ParkingManagementSystem.DayNightParkingChargeStrategy;
import ParkingManagementSystem.LongStayParkingChargeStrategy;
import ParkingManagementSystem.ParkingChargeStrategy;

public class ParkingChargeFactory {
	private ParkingChargeStrategy strategy = null;
	
	public ParkingChargeStrategy makeStrategy(String lotStrategy) {
		
		if(lotStrategy.equalsIgnoreCase("dayNight")) {
			strategy = new DayNightParkingChargeStrategy();
		}
		else if(lotStrategy.equalsIgnoreCase("longStay")) {
			strategy = new LongStayParkingChargeStrategy();
		}
		
		return strategy;
	}
	
	public ParkingChargeStrategy getStrategy() {
		return strategy;
	}
	
}
