package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Customer;

class CustomerTest {

	Customer customer = new Customer("ID001", "Sahasra", "Muluka", "RDS-TXS", null);
            
	
	@Test
    public void testGetCustomerName() {
        assertEquals("Sahasra Muluka", customer.getCustomerName());
    }

    @Test
    public void testGetId() {
        assertEquals("ID001", customer.getId());
    }

    @Test
    public void testSetId() {
        customer.setId("ID002");
        assertEquals("ID002", customer.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Sahasra", customer.getFirstName());
    }

}
