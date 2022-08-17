package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.AccountType;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class AccountController {
	
	public static Account findAccount(String accountName)
	{
		return EntityManagerFactory.of(Account.class)
				.readForeign(Account.class, Customer.customer)
				.stream()
				.map(obj -> (Account)obj)
				.filter(acc -> acc.getAccountName().equals(accountName))
				.findFirst()
				.orElseThrow();
	}
	
	public static float getWithdrawalChargeAmount(String accountName)
	{
		return ((AccountType) EntityManagerFactory.of(AccountType.class)
				.read(AccountType.class, accountName)).getWithdrawalCharge();
			
	}
	
}
