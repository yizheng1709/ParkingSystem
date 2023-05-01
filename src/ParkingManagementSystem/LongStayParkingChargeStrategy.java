package ParkingManagementSystem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import ParkingManagementSystem.money.Money;

public class LongStayParkingChargeStrategy implements ParkingChargeStrategy{
	//This strategy gives discount to users based on the duration of parking
	Money dailyCharge = Money.of(15);
	Money total = Money.of(0);
	Money discount = Money.of(3);
	Money daysDiscount = Money.of(3);
	Money discounts[] = {Money.of(9),Money.of(15),Money.of(40)};
	
	public Money calculateParkingCharge(ParkingPermit pid, LocalDateTime entry, LocalDateTime exit) {	
				
		LocalTime midnight = LocalTime.MIDNIGHT;
	    LocalDate today = LocalDate.now(ZoneId.of("America/Denver"));
	    LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
	    
	    Duration duration = Duration.between(todayMidnight, exit);
	    double days = duration.toHours() / 24.0;
	    int totalDays = (int) Math.ceil(days);
	    System.out.println("Total Days: "+totalDays);
	    total = Money.add(total, Money.times(dailyCharge, totalDays));
	   
	    if(totalDays > 3) {
	    	total = Money.subtract(total, discounts[0]);
	    }else if(totalDays > 5) {
	    	total = Money.subtract(total, discounts[1]);
	    }else if(totalDays > 10) {
	    	total = Money.subtract(total, discounts[2]);
	    }
	    
	    if(pid.getCar().getType() == CarType.COMPACT) {
	    	discount = Money.times(discount, totalDays);
	    	total = Money.subtract(total, discount);
	    }
	    
	    System.out.println(total.doubleValue());
	    
	    return total;
		
	}
	
}
