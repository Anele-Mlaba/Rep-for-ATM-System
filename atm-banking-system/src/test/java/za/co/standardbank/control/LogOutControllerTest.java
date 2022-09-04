package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import za.co.standardbank.atm.control.LogOutController;
import za.co.standardbank.atm.control.LoginController;
import za.co.standardbank.atm.model.Customer;

public class LogOutControllerTest {
	@Test
	public void logOut()
	{
		LoginController.login("00000000000","00000");
		LogOutController.logOut();
		assertEquals(Customer.customer, null);
	}
}
