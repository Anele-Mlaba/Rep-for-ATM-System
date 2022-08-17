package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class BalanceController {
	public static float getBalance(String accountName)
	{
		return (float) EntityManagerFactory.of(Account.class)
				.readForeign(Account.class, Customer.customer)
				.stream()
				.map(object -> (Account)object)
				.filter(account -> account.getAccountName().equals(accountName))
				.findFirst()
				.orElseThrow()
				.getBalance();	
	}	
}
