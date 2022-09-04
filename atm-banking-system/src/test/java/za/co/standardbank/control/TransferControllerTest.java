package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import za.co.standardbank.atm.control.TransferController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class TransferControllerTest {
	
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}
	
	@Test
	public void makeTransferTest()
	{
		assertEquals("insufficient funds",
				TransferController.makeTransfer("Professional", "Student Achiever", "400"));
		
		assertEquals("Make sure you choose an account!",
				TransferController.makeTransfer("", "Student Achiever", "400"));
		
		assertEquals("can't be R0",
				TransferController.makeTransfer("Professional", "Student Achiever", "0"));
		
		assertEquals("",
				TransferController.makeTransfer("Professional", "Student Achiever", "10"));
		
	}
	

}
