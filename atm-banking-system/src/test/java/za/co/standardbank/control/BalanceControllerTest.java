package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.BalanceController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class BalanceControllerTest {
	
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}
	
	@Test(expected = NullPointerException.class)
	public void getBalanceTest()
	{
		String expectedProf = 300.0+"";
		
		System.out.println(BalanceController.getBalance("Professional")+"amount");
		assertEquals(expectedProf, BalanceController.getBalance("Professional")+"");
		
		String expectedStd = 200.0+"";
		System.out.println(BalanceController.getBalance("Student Achiever")+"amount");
		assertEquals(expectedStd, BalanceController.getBalance("Student Achiever")+"");
		
		BalanceController.getBalance("");
	}
}
