import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class ParkingObserverTest {

    private ParkingObserver observer;
    private TransactionManager transactionManager;

    @BeforeEach
    public void setUp() {
        transactionManager = new TransactionManager();
        observer = new ParkingObserver(transactionManager);
    }

    @Test
    public void testUpdate() {
        ParkingLot lot = new ParkingLot("Lot 1");
        lot.addObserver(observer);

        ParkingPermit permit = new ParkingPermit("ABC123");
        lot.enter(permit);

        ParkingEvent event = new ParkingEvent("Lot 1", permit, Instant.now());
        assertEquals(event, observer.getLatestEvent());
    }

}
