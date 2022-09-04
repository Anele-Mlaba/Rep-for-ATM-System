package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.LoginController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class LoginControllerTest {
	
	@Test
	public void loginTest()
	{
		assertFalse(LoginController.login("1", "0000"));
		assertFalse(LoginController.login("00000000000", "00"));
		assertTrue(LoginController.login("00000000000", "00000"));
		
		assertEquals("00000000000", Customer.customer.getCustomerId());
		assertEquals("00000", Customer.customer.getPin());
		assertEquals("0001013456409", Customer.customer.getIdNo());
	}
}
