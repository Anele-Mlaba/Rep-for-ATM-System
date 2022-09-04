package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.DepositController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class DepositControllerTest {
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}
	
	@Test
	public void makeDeposit()
	{
		assertEquals(DepositController.makeDeposit("40", "Professional"), "");
		assertEquals(DepositController.makeDeposit("30", "Student Achiever"), "");
		assertTrue(DepositController.makeDeposit("43", "Professional").length()>0);
		assertTrue(DepositController.makeDeposit("30", "").length()>0);
	}
	
	@After
	public void endAll()
	{
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}
	
}
