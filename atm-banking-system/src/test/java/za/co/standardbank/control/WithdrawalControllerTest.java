package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.WithdrawalController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class WithdrawalControllerTest 
{
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.0f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.0f , "00000000000"));
	}
	@Test
	public void makeWithdrawalTest()
	{
		assertEquals("Insufficient funds", WithdrawalController.makeWithdrawal("400", "Professional"));
		assertEquals("can't be R0", WithdrawalController.makeWithdrawal("0", "Professional"));
		assertEquals("Make sure you choose an account!", WithdrawalController.makeWithdrawal("10", ""));
		assertEquals("", WithdrawalController.makeWithdrawal("10", "Professional"));
	}
}
