package ParkingManagementSystem;

import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.ParkingObserver;
import ParkingManagementSystem.ParkingEvent;

public interface ParkingLotSubject {
    void attachObserver(ParkingObserver observer);

    void detachObserver(ParkingObserver observer);

    void notifyObservers(ParkingEvent event);

    void enter(ParkingPermit permit);

    void exit(ParkingPermit permit);
}
