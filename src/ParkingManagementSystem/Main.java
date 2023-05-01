package ParkingManagementSystem;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {

	public static void main(String[] args) {
		
		//Parking Office object
		ParkingOffice office = new ParkingOffice();
		office.setParkingOfficeName("DU Parking Office");
		
		//PArking Service Object to perform operation on Parking Office
		ParkingService service = new ParkingService(office);
		
		//Register a customer 
		System.out.println(service.performCommand("Register_CUSTOMER", "name=sahasra"));
		
		//Register a car for the above register customer
		System.out.println(service.performCommand("Register_CAR", "license=rds-txs", "customer=CUST-12"));
		
		System.out.println(office.getParkingPermit("P1001"));
		
		LocalDateTime myObj = LocalDateTime.now(ZoneId.of("America/Denver"));
		
		
		System.out.println(myObj);
		System.out.println(service.performCommand("PARK", "parkingLot=321", "permit=P1001", "entryTime="+myObj, "exitTime="+myObj.plusHours(123)));
		
		System.out.println(service.performCommand("PARK", "parkingLot=301", "permit=P1001", "entryTime="+myObj, "exitTime="+myObj.plusHours(14)));
		 
		System.out.println(service.performCommand("CHARGES", "permit=P1001"));
	}

}
