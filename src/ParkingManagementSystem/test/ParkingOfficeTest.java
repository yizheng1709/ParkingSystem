package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.Customer;
import ParkingManagementSystem.ParkingOffice;

class ParkingOfficeTest {

	private ParkingOffice parkingOffice;
    private Customer customer;
    private Car car;


    @BeforeEach
    public void setup() {
        parkingOffice = new ParkingOffice();
        customer = new Customer("ID001", "Sahara", "Muluka", "CEN-345", null);
        car = new Car();
    }

    @Test
    public void testRegisterCustomer() {
        String customerId = parkingOffice.register(customer);
        assertEquals(customer, parkingOffice.getCustomer(customerId));
    }

    @Test
    public void testRegisterCar() {
        String carId = parkingOffice.register(car);
        assertNotNull(parkingOffice.getParkingPermit(carId));
    }

}
