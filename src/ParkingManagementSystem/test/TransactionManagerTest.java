import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ParkingManagementSystem.ParkingEvent;
import ParkingManagementSystem.ParkingPermit;
import ParkingManagementSystem.TransactionManager;

class TransactionManagerTest {
    private TransactionManager transactionManager;
    private List<ParkingEvent> parkingEvents;
    
    @BeforeEach
    void setUp() {
        parkingEvents = new ArrayList<ParkingEvent>();
        transactionManager = new TransactionManager(parkingEvents);
    }

    @Test
    void testRegisterParkingEvent() {
        ParkingPermit permit = new ParkingPermit("ABC123");
        Instant timeIn = Instant.now();
        ParkingEvent event = new ParkingEvent("Lot A", permit, timeIn);
        
        transactionManager.registerParkingEvent(event);
        
        assertTrue(parkingEvents.contains(event));
    }
    
    @Test
    void testFindParkingEvent() {
        ParkingPermit permit = new ParkingPermit("ABC123");
        Instant timeIn = Instant.now();
        ParkingEvent event = new ParkingEvent("Lot A", permit, timeIn);
        parkingEvents.add(event);
        
        ParkingEvent foundEvent = transactionManager.findParkingEvent("Lot A", permit, null);
        
        assertEquals(event, foundEvent);
    }
    
    @Test
    void testFindParkingEventReturnsNullIfNotFound() {
        ParkingPermit permit = new ParkingPermit("ABC123");
        Instant timeIn = Instant.now();
        ParkingEvent event = new ParkingEvent("Lot A", permit, timeIn);
        parkingEvents.add(event);
        
        ParkingEvent foundEvent = transactionManager.findParkingEvent("Lot B", permit, null);
        
        assertNull(foundEvent);
    }
    
    @Test
    void testUpdateParkingEvent() {
        ParkingPermit permit = new ParkingPermit("ABC123");
        Instant timeIn = Instant.now();
        ParkingEvent event = new ParkingEvent("Lot A", permit, timeIn);
        parkingEvents.add(event);
        
        Instant timeOut = Instant.now();
        transactionManager.updateParkingEvent(event, timeOut);
        
        assertEquals(timeOut, event.getTimeOut());
    } 
}
