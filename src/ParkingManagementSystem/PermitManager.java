package ParkingManagementSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PermitManager {
	private List<ParkingPermit> permits = new ArrayList<ParkingPermit>();
	
	private static int idCounter = 1000;
	private static String getPermitId() {
		idCounter += 1;
		return String.format("P%04d", idCounter);
	}
	public ParkingPermit register(Car car) {
		String id = getPermitId();
		LocalDateTime d = LocalDateTime.now();
		ParkingPermit permit = new ParkingPermit(id,car,d.plusYears(1L));
		getPermits().add(permit);
		return permit;
	}
	
	public ParkingPermit findPermit(String id) {
		ParkingPermit result = null;
		for ( ParkingPermit p: getPermits()) {
			if ( p.getId().equals(id)) {
				result = p;
				break;
			}
		}
		
		return result;
	}
	public List<ParkingPermit> getPermits() {
		return permits;
	}
	public void setPermits(List<ParkingPermit> permits) {
		this.permits = permits;
	}
}
