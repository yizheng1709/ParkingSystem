package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Car;
import ParkingManagementSystem.CarType;
import ParkingManagementSystem.Customer;

class CarTest {

	@Test
	public void testDefaultConstructor() {
        Car car = new Car();
        assertEquals(CarType.COMPACT, car.getType());
        assertEquals("", car.getLicensePlate());
        assertNotNull(car.getOwner());
    }

    @Test
    public void testParameterizedConstructor() {
        Customer owner = new Customer();
        Car car = new Car(CarType.SUV, "CEN345", owner);
        assertEquals(CarType.SUV, car.getType());
        assertEquals("CEN345", car.getLicensePlate());
    }

    @Test
    public void testSettersAndGetters() {
        Customer owner = new Customer();
        Car car = new Car();
        car.setType(CarType.SUV);
        car.setLicensePlate("CEN345");
        car.setOwner(owner);
        assertEquals(CarType.SUV, car.getType());
        assertEquals("CEN345", car.getLicensePlate());
        assertEquals(owner, car.getOwner());
    }
}
