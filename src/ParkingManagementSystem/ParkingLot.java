package ParkingManagementSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ParkingManagementSystem.factory.ParkingChargeFactory;
import ParkingManagementSystem.money.Money;
import ParkingManagementSystem.ParkingObserver;

public class ParkingLot implements ParkingLotSubject {
    private String id;
    private String name;
    private Address address;
    private Money baseRate = Money.of(5.00);
    private ParkingChargeStrategy strategy;
    private ParkingChargeFactory chargeFactory = new ParkingChargeFactory();
    private List<ParkingObserver> observers = new ArrayList<>();
    
    public ParkingLot(String id, String name, Address address) {
    	this.id = id;
    	this.name = name;
    	this.address = address;
    }
    
    public ParkingLot(String id, String name, Address address, Money baseRate, ParkingChargeStrategy strategy) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.baseRate = baseRate;
      this.strategy = strategy;
    }
    
    @Override
    public void attachObserver(ParkingObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void detachObserver(ParkingObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers(ParkingEvent event) {
        for (ParkingObserver observer : observers) {
            observer.update(event);
        }
    }
    
    @Override
    public void enter(ParkingPermit permit) {
        ParkingEvent event = new ParkingEvent(name, permit, Instant.now());
        notifyObservers(event);
    }
    
    @Override
    public void exit(ParkingPermit permit) {
        ParkingEvent event = new ParkingEvent(name, permit, Instant.now());
        notifyObservers(event);
    }
    
    public Money getParkingCharges(ParkingPermit p, LocalDateTime in, LocalDateTime out) {
    	return strategy.calculateParkingCharge(p, in, out);
    }
    
    public Money getBaseRate() {
    	return baseRate;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();

    	sb.append(id);
    	sb.append("\n");
    	sb.append(name);
    	sb.append("\n");
    	sb.append(address);

    	return sb.toString();
    }

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setStrategy(ParkingChargeStrategy newStrategy) {
		strategy = newStrategy;
	}
	
	public ParkingChargeStrategy getStrategy() {
		return strategy;
	}
	
  // Method for permit-required-on-enter lot
  public void enterLot(LocalDateTime in, String permitId) {
  }

	// Method for permit-required-on-exit lot
  public void exitLot(LocalDateTime in, LocalDateTime out, String permitId) {
  }
  
}
