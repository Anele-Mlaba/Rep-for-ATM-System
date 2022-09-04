package za.co.standardbank.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import za.co.standardbank.atm.control.LoginController;
import za.co.standardbank.atm.control.TransactionsController;

public class TransactionsControllerTest {
	@Test
	public void getTransactions()
	{
		LoginController.login("00000000000","00000");
		List<String> trans = TransactionsController.getTransactions(0, "Professional");
		System.out.println(trans.size());
		assertTrue(trans.size() == TransactionsController.TRANSACTIONS_PER_PAGE);
	}
}
