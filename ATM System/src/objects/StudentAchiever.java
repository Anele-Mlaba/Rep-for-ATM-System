package objects;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class StudentAchiever extends Account{
	public StudentAchiever(String accountName, String accountNo, int balance, 
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
	public StudentAchiever copy()
	{
		return new StudentAchiever(accountName, accountNo, balance, transactions);
	}
	
}
