package ParkingManagementSystem;

import java.time.Duration;
import java.time.LocalDateTime;

import ParkingManagementSystem.money.Money;

public class DayNightParkingChargeStrategy implements ParkingChargeStrategy{
	Money dayCharge = Money.of(2); //Per hour
	Money overNightCharge = Money.of(1); //Per hour
	Money totalCharge = Money.of(0);
	int dayHours = 0;
	int nightHours = 0;
	Money charge = Money.of(0);
	Money discount = Money.of(0);
	
	public Money calculateParkingCharge(ParkingPermit pid, LocalDateTime entryTime,LocalDateTime exitTime) {
		//This strategy is used for charging dynamically based on they parked during day time or night time
		long hoursParked = Duration.between(entryTime, exitTime).toHours();
		System.out.println("Total Hours: " + hoursParked);
		while(hoursParked > 0) {
			if(entryTime.getHour() > 5 && entryTime.getHour() < 18) {
				dayHours++;
			}else {
				nightHours++;
			}
			hoursParked--;
			entryTime = entryTime.plusHours(1);
		}
		
		
		System.out.println("6 am to 6pm: "+ dayHours + " hours");
		System.out.println("6 pm to 6am: "+ nightHours + " hours");
		
		charge = Money.add(charge, Money.times(dayCharge, dayHours));
		charge = Money.add(charge, Money.times(overNightCharge, nightHours));
		
        discount = Money.add(discount, Money.times( Money.of(0.4), dayHours));
        discount = Money.add(discount, Money.times( Money.of(0.2), nightHours));
        
        if(pid.getCar().getType() == CarType.COMPACT) {
	    	charge = Money.subtract(charge, discount);
	    }
	    return charge;
	}	
}
