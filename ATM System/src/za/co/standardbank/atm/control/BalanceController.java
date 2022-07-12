package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Professional;
import za.co.standardbank.atm.model.StudentAchiever;

public class BalanceController {
	public static float getBalance(String account)
	{
		float balance = ((StudentAchiever)(Customer.customer.getAccounts().get(1))).getBalance();
		if(account.equals("Professional"))
		{
			balance = ((Professional)(Customer.customer.getAccounts().get(0))).getBalance();
		}
		return balance;
	}	
}
