package ParkingManagementSystem;

import java.time.LocalDateTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ParkingManagementSystem.money.Money;

// The methods of the transaction manager are implemented with the stream() interface (Java 8)
// This allows examples of map() and reduce(), as well as use of lambdas
// Of course, these aren't required for the solution. Do you think they are more elegant?
public class TransactionManager {
  
  private static final Logger logger = Logger.getLogger(TransactionManager.class.getName());

  private List<ParkingTransaction> transactions = new ArrayList<ParkingTransaction>();
  private List<ParkingEvent> events = new ArrayList<ParkingEvent>();
  private ParkingOffice office;

  public TransactionManager(ParkingOffice office) {
    this.office = office;
  }

  public ParkingTransaction park(LocalDateTime in, LocalDateTime out, ParkingPermit p, ParkingLot l) {
    ParkingTransaction transaction = null;
    if ( l != null && p != null && l != null ) {
      Money money = l.getParkingCharges(p, in, out);
      transaction = new ParkingTransaction(in, p, l, money);
      transactions.add(transaction);
    } else {
      
    }
    return transaction;
  }
  
  public void addEvent(ParkingEvent event) {
	events.add(event);
  }
  
  private void registerEvent(ParkingEvent event) {
    System.out.println("Registered parking event for permit " + event.getPermit().getId() +
        " at lot " + event.getLotName() + " with entry time " + event.getTimeIn() + " and exit time " + event.getTimeOut());
  }

  
  public void park(String lotName, ParkingPermit permit, Instant timeIn) {
    ParkingEvent event = new ParkingEvent(lotName, permit, timeIn);
    registerEvent(event);
  }
  
  public void unpark(String lotName, ParkingPermit permit, Instant timeOut) {
    ParkingEvent event = findEvent(lotName, permit, timeOut);
    if (event != null) {
      event.setExitTime(timeOut);
      registerEvent(event);
    }
  }
  
  private ParkingEvent findEvent(String lotName, ParkingPermit permit, Instant timeOut) {
    return events.stream()
            .filter(event -> event.getLotName().equals(lotName))
            .filter(event -> event.getPermit().equals(permit))
            .filter(event -> event.getTimeOut() == null)
            .findFirst()
            .orElse(null);
  }

  public Money getParkingCharges(Customer c) {
    List<ParkingTransaction> customerTransactions;
    // First, let's get a list of the transactions for the customer.
    customerTransactions = transactions.stream()
            .filter(transaction -> transaction.getPermit().getCar().getOwner().equals(c))
            .collect(Collectors.toList());

    // Now lets add up all the charged amounts
    Money result = customerTransactions.stream()
            .map(transaction -> transaction.getChargedAmount())
            .reduce(Money.of(0.0), (a, b) -> Money.add(a, b));

    return result;
  }

  public Money getParkingCharges(ParkingPermit p) {
    return transactions.stream()
            .filter(transaction -> transaction.getPermit().equals(p))
            .map(transaction -> transaction.getChargedAmount())
            .reduce(Money.of(0.0), (a, b) -> Money.add(a, b));
  }

}
