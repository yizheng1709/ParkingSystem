package ParkingManagementSystem;

import java.util.Properties;

public interface Command {
	public String getCommandName();
	
	public String getDisplayName();
	
	public String execute(Properties params);
}
