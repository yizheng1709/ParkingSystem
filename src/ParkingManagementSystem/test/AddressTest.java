package ParkingManagementSystem.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ParkingManagementSystem.Address;

public class AddressTest {

  @Test
  public void testGetAddressInfo() {
    Address address = new Address.Builder()
        .withStreetAddress1("80123 S")
        .withCity("Centennial")
        .withState("CO")
        .withZip("80112")
        .build();

    String expected = "80123 S\nCentennial, CO\n80112\n";
    String actual = address.getAddressInfo();

    assertEquals(expected, actual);
  }

  @Test
  public void testSettersAndGetters() {
    Address address = new Address();

    address.setStreetAddress1("80111");
    address.setCity("Parker");
    address.setState("CO");
    address.setZip("80134");

    assertEquals("80111", address.getStreetAddress1());
    assertEquals("", address.getStreetAddress2());
    assertEquals("Parker", address.getCity());
    assertEquals("CO", address.getState());
    assertEquals("80134", address.getZip());
  }

}