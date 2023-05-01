package ParkingManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegisterCarCommand implements Command{

	private ParkingOffice office;
	
	public RegisterCarCommand(ParkingOffice parkingOffice) {
		this.office = parkingOffice;
	}

	@Override
	public String getCommandName() {
		return "RegisterCarCommand";
	}

	@Override
	public String getDisplayName() {
		return "Register Car";
	}

	@Override
	public String execute(Properties props) {
		List<String> messages = new ArrayList<>();
		if (checkParameters(props)) {
	          Car car = new Car();
	          car.setLicensePlate(checkLicensePlate(props.getProperty("license")));
	          car.setOwner(checkCustomer(props.getProperty("customer")));
	          car.setType(CarType.COMPACT);
	          messages.add(office.register(car));
	          messages.add("Successfully registered" + props.getProperty("license") + "car for customer " + props.getProperty("customer") + " with parking office");
	        } else {
	          messages.add("Cannot create car permit: wrong number of parameters");
	        }
		return String.join("\n", messages);
	}
	
	private boolean checkParameters(Properties props) {
		if(props.size()==2) {
			return true;
		}
		return false;
	}
	
	private Customer checkCustomer(String customerId) {
	    String[] parts = customerId.split("=");
	    if ( parts.length == 2 ) {
	      customerId = parts[1];
	    }
	    Customer customer = office.getCustomer(customerId);
	    if (customer == null) {
	      customer = new Customer();
	      customer.setFirstName("Unknown");
	      office.register(customer);
	    }
	    return customer;
	  }
	
	private static String checkLicensePlate(String plate) {
	    String[] parts = plate.split("=");
	    if ( parts.length == 2 ) {
	      plate = parts[1];
	    }
	    String result = plate;
	    if (result == null) {
	      plate = "Unknown";
	    }
	    return result;
	  }
}
