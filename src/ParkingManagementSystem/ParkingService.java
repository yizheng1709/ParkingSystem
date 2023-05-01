package ParkingManagementSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import ParkingManagementSystem.money.Money;

import java.util.HashMap;

public class ParkingService {

  private final ParkingOffice parkingOffice;
  private RegisterCustomerCommand registerCustomer;
  private RegisterCarCommand registerCar;
  private Map<String, Command> commands = new HashMap<String, Command>();
  
  private static final Logger logger = Logger.getLogger(ParkingService.class.getName());

  public ParkingService(ParkingOffice parkingOffice) {
    this.parkingOffice = parkingOffice;
    registerCustomer = new RegisterCustomerCommand(parkingOffice);
    registerCar = new RegisterCarCommand(parkingOffice);
  }
  
  private void register(Command command) {
	  commands.put("cmd1", command);
  }

  public String performCommand(String command, String... args) {
    List<String> messages = new ArrayList<>();
    logger.log(Level.INFO, "Performing {0} command", command);
    messages.add(command + ": "+ String.join(", ", args));
    
    Properties prop = new Properties();
    for(String arg : args){
	      String[] values = arg.split("=");
	      prop.setProperty(values[0], values[1]);
	    }
    
    switch (command) {
      case "Register_CUSTOMER": // Creates a customer
        messages.add(registerCustomer.execute(prop));
        break;
      
      case "Register_CAR": // Creates a car permit for an existing customer's car
    	messages.add(registerCar.execute(prop));
    	break;
    	  
      case "PARK": // Creates the Parking Transaction for a particular car
        if (checkNumberOfParameters(3, prop.size())) {
          LocalDateTime in = checkDateTime(prop.getProperty("entryTime"));
          LocalDateTime out = checkDateTime(prop.getProperty("exitTime"));
          ParkingLot pl = checkParkingLot(prop.getProperty("parkingLot"));
          ParkingPermit parkedCar = checkParkingPermit(prop.getProperty("permit"));
          ParkingTransaction transaction = parkingOffice.park(in, out, parkedCar, pl);
          if ( transaction != null ) {
            // Updated output to simply post the charge amount.
            messages.add(transaction.getChargedAmount().toString());
          } else {
            messages.add("Parking transaction failed. Parameters invalid");
          }
        } else {
          messages.add("Cannot park: wrong number of parameters");
        }
        break;
      case "CHARGES":
    	  Money charges;
        // Getting total charges for a particular permit
    	  if (checkNumberOfParameters(1, prop.size())) {
    		 charges = parkingOffice.getParkingCharges(parkingOffice.getParkingPermit(prop.getProperty("permit")));
    		 messages.add("Total parking charges for " + prop.getProperty("permit") + " is " + charges);
    	  }else {
    		  messages.add("Cannot park: wrong number of parameters");
    	  }
        break;
      default: // Did not recognize this command
        messages.add("Command unknown");
    }
    return String.join("\n", messages);
  }

  private static boolean checkNumberOfParameters(int expected, int provided) {
    boolean result = true;
    if (provided < expected) {
      logger.log(Level.SEVERE, "Not enough parameters! Expected {0} received {1}",
          new Object[]{expected, provided});
      result = false;
    }
    return result;
  }

  private static LocalDateTime checkDateTime(String dateTime) {
    LocalDateTime result = LocalDateTime.now();
    String[] parts = dateTime.split("=");
    if ( parts.length == 2 ) {
      dateTime = parts[1];
    }
    try {
      result = LocalDateTime.parse(dateTime);
    } catch (DateTimeParseException ex) {
      logger.log(Level.INFO, "Cannot parse datetime {0}: {1}", new Object[]{dateTime, ex});
    }
    return result;
  }

  private ParkingLot checkParkingLot(String lotId) {
    String[] parts = lotId.split("=");
    if ( parts.length == 2) {
      lotId = parts[1];
    }
    ParkingLot result = parkingOffice.getParkingLot(lotId);
    return result;
  }

  private ParkingPermit checkParkingPermit(String permitId) {
    String[] parts = permitId.split("=");
    if ( parts.length == 2 ) {
      permitId = parts[1];
    }
    ParkingPermit result;
    result = parkingOffice.getParkingPermit(permitId);
    return result;
  }

}

