package za.co.standardbank.atm.model;
import java.util.ArrayList;

public class Professional extends Account {
	public Professional(String accountName, String accountNo, float balance, 
			ArrayList<Transaction> transactions)
	{
		super(accountName, accountNo, balance, 
				 transactions);
	}
	
	public boolean payBeneficiary(int amount)
	{
		return true;
	}
	
	public boolean makeWithdrawal(int amount)
	{
		return true;
	}
	

	public Professional copy()
	{
		return new Professional(accountName, accountNo, balance, transactions);
	}	
}
