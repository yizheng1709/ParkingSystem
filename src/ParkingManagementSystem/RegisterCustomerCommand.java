package ParkingManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RegisterCustomerCommand implements Command{
	
	private ParkingOffice office;

	public RegisterCustomerCommand(ParkingOffice parkingoffice) {
		this.office = parkingoffice;
	}

	@Override
	public String getCommandName() {
		return "RegisterCustomerCommand";
	}

	@Override
	public String getDisplayName() {
		return "Register Customer";
	}

	@Override
	public String execute(Properties props) {
		List<String> messages = new ArrayList<>();
		if (checkParameters(props)) {
	          Customer customer = new Customer();
	          customer.setLastName(props.getProperty("name"));
	          messages.add(office.register(customer));
	          messages.add("Successfully registered customer " + props.getProperty("name") + " with parking office");
	        } else {
	          messages.add("Cannot create customer: wrong number of parameters");
	        }
		return String.join("\n", messages);
	}
	
	private boolean checkParameters(Properties props) {
		if(props.size()==1) {
			if(props.getProperty("name")!=null) {
				return true;
			}
		}
		return false;
	}
	
}

