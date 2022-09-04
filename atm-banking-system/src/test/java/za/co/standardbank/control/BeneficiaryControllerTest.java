package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import za.co.standardbank.atm.control.BeneficiaryController;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class BeneficiaryControllerTest
{
	@Before
	public void initAll()
	{
		Customer.customer = new Customer("00000000000","00000","0001013456409");
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}

	@Test
	public void addBeneficiaryTest()
	{
		String actualAccNoTest1 = BeneficiaryController.addBeneficiary("Amuh", "ABSA", "102mjs");
		String expectedAccNoTest1 = "Account should only contain numbers";
		
		String actualAccNoTest2 = BeneficiaryController.addBeneficiary("Amuh", "ABSA", "01129834");
		String expectedAccNoTest2 = "account number does not start with 0";
		
		String actualAccNoTest3 = BeneficiaryController.addBeneficiary("Amuh", "ABSA", "1002823932173817247129");
		String expectedAccNoTest3 = "Account number too long";
		
		
		String actualNoBankSelected = BeneficiaryController.addBeneficiary("Amuh", "", "10020987");
		String expectedNoBankSelected = "Make sure you choose Bank!";
		
		String actual =  BeneficiaryController.addBeneficiary("Amuh", "ABSA", "10020987");
		String expected = "";
		
		assertEquals(expectedAccNoTest1, actualAccNoTest1);
		assertEquals(expectedAccNoTest2, actualAccNoTest2);
		assertEquals(expectedAccNoTest3, actualAccNoTest3);
		assertEquals(expectedNoBankSelected, actualNoBankSelected);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBeneficiariesTest()
	{
		List<String> bens = BeneficiaryController.getBeneficiaries(0);
		
		assertTrue(bens.size()<=BeneficiaryController.NUM_OF_BENEFICIARIES && bens.size()>0);
		
		String expectedBenName = "Jordan Peterson";
		String expectedBenBankName = "Capitec Bank";
		String expectedBenAccNo = "101023452";
		
		assertTrue(bens.get(0).contains(expectedBenAccNo));
		assertTrue(bens.get(0).contains(expectedBenName));
		assertTrue(bens.get(0).contains(expectedBenBankName));
		
	}
	
	@Test
	public void getSearchedBeneficiariesTest()
	{
		assertEquals(0, BeneficiaryController.getSearchedbeneficiaries("ahfhfahfab").size());
		
		assertTrue(BeneficiaryController.getSearchedbeneficiaries("Charlie Kirk").get(0).contains("Charlie Kirk"));
	}
	
	@Test
	public void payBenTest()
	{
		BeneficiaryController.payBen("45", "Professional", "10127809");
		
		assertEquals("ATM only uses notes. make sure your amount is a multiple of 10", 
				BeneficiaryController.payBen("45", "Professional", "10127809"));
		
		assertEquals("can't be R0", BeneficiaryController.payBen("0", "Professional", "10127809"));
		
		assertEquals("Make sure your amount does not contain decimals", 
				BeneficiaryController.payBen("10.2", "Professional", "10127809"));
		
		assertEquals("Amount field can't be empty!", BeneficiaryController.payBen("", "Professional", "10127809"));
		
		assertEquals("Make sure your amount is numeric", 
				BeneficiaryController.payBen("iyb", "Professional", "10127809"));
		
		assertEquals("Insuficient funds", BeneficiaryController.payBen("5000", "Professional", "10127809"));
		assertEquals("Insuficient funds", BeneficiaryController.payBen("5000", "Student Achiever", "10127809"));		
		assertEquals("Make sure you choose an account!", BeneficiaryController.payBen("10", "", "10127809"));		
		assertEquals("", BeneficiaryController.payBen("10", "Student Achiever", "10127809"));		
		assertEquals("", BeneficiaryController.payBen("10", "Professional", "10127809"));
		
	}
	
	@After
	public void endAll()
	{
		EntityManagerFactory.of(Account.class).update(new Account("101812345678", "Professional",300.00f , "00000000000"));
		EntityManagerFactory.of(Account.class).update(new Account("101912345678", "Student Achiever",200.00f , "00000000000"));
	}
}
