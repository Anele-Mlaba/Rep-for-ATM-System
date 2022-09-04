package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.AccountController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class AccountControllerTest {
	

	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.0f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.0f , "00000000000"));
	}
			
	@Test(expected = NullPointerException.class)
	public void findAccountTest()
	{
		String expectedStdAccName = "Student Achiever";
		String expectedStdAccNo = "101912345678";
		String expectedStdCustID = "00000000000";
		float expectedStdBal = 200.0f;
		
		String expectedProfAccName = "Professional";
		String expectedProfAccNo = "101812345678";
		String expectedProfCustID = "00000000000";
		float expectedProfBal = 300.0f;
		
		Account stdAcc = AccountController.findAccount("Student Achiever");
		Account profAcc = AccountController.findAccount("Professional");
		
		assertEquals(expectedStdAccName, stdAcc.getAccountName());
		assertEquals(expectedStdAccNo, stdAcc.getAccountNo());
		assertEquals(expectedStdCustID, stdAcc.getCustomerId());
		assertEquals(expectedStdBal+"",stdAcc.getBalance()+"");
		
		assertEquals(expectedProfAccName, profAcc.getAccountName());
		assertEquals(expectedProfAccNo, profAcc.getAccountNo());
		assertEquals(expectedProfCustID, profAcc.getCustomerId());
		assertEquals(expectedProfBal+"",profAcc.getBalance()+"");
		
		AccountController.findAccount("");
	}
	
	@Test
	public void getWithdrawalChargeAmountTest()
	{
		String expectedProfCharge = 12.50+"";
		String expectedStdCharge = 7.50+"";
		
		assertEquals(expectedProfCharge, AccountController.getWithdrawalChargeAmount("Professional")+"");
		assertEquals(expectedStdCharge, AccountController.getWithdrawalChargeAmount("Student Achiever")+"");
	}	
}
