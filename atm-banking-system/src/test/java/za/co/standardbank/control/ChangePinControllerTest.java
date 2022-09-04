package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.ChangePinController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class ChangePinControllerTest {
	
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		
	}
	
	@Test
	public void changePinTest()
	{
		assertEquals(ChangePinController.changePin("00001","26353","26353"), "the pin you entered doesn't match the existing pin");
		assertEquals(ChangePinController.changePin("00000","00000","00000"), "new Pin matched the already existing pin");
		assertEquals(ChangePinController.changePin("00000","0000","0000"), "pin must be 5 digits long");
		assertEquals(ChangePinController.changePin("00000","00ll0","00ll0"), "pin should only contain digits");
		assertEquals(ChangePinController.changePin("00000","26353","26355"), "confirmation pin does not match your new pin");
		assertEquals(ChangePinController.changePin("00000","33333","33333"), "pin cannot consist of only identical digits. try mixing different digits");
		assertEquals(ChangePinController.changePin("00000","33333","33333"), "pin cannot consist of only identical digits. try mixing different digits");
		assertEquals(ChangePinController.changePin("00000","12345","12345"), "pin cannot be consecutive digits");
		assertEquals(ChangePinController.changePin("00000","54321","54321"), "pin cannot be consecutive digits");
		assertEquals(ChangePinController.changePin("00000","26353","26353"), "");	
		
		ChangePinController.changePin("26353","00000","00000");
	}
	
	@After
	public void endAll()
	{
		EntityManagerFactory.of(Customer.class).update(new Customer("00000000000","00000","0001013456409"));
	}

}
