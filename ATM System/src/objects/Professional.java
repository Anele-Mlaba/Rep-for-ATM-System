package objects;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Professional extends Account {
	public Professional(String accountName, String accountNo, int balance, 
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
