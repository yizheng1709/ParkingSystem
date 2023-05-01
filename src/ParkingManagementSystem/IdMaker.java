package ParkingManagementSystem;

import java.util.concurrent.atomic.AtomicInteger;

public class IdMaker {
  private static final AtomicInteger counter = new AtomicInteger(1);
  
  public static String makeId(String prefix) {
    return prefix + counter.addAndGet(1);
  }
  
}