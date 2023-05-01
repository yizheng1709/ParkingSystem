package ParkingManagementSystem;

import java.time.Instant;

public class ParkingEvent {
    private String lotName;
    private ParkingPermit permit;
    private Instant timeIn;
    private Instant timeOut;

    public ParkingEvent(String lotName, ParkingPermit permit, Instant timeIn) {
        this.lotName = lotName;
        this.permit = permit;
        this.timeIn = timeIn;
    }

    public void setExitTime(Instant timeOut) {
        this.timeOut = timeOut;
    }

    // Getters for event information
    public String getLotName() {
        return lotName;
    }

    public ParkingPermit getPermit() {
        return permit;
    }

    public Instant getTimeIn() {
        return timeIn;
    }

    public Instant getTimeOut() {
        return timeOut;
    }
}
