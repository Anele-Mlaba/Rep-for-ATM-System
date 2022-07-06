package za.co.standardbank.control;

import za.co.standardbank.model.Customer;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;

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
