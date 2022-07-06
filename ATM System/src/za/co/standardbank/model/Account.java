package za.co.standardbank.model;
import java.util.ArrayList;
import java.util.Collections;

public class Account {
	protected String accountName;
	protected String accountNo;
	protected float balance;
	protected ArrayList<Transaction> transactions;
	
	public Account(String accountName, String accountNo, float balance, 
			ArrayList<Transaction> transactions)
	{
		this.accountName = accountName;
		this.accountNo = accountNo;
		this.balance = balance;
		this.transactions = transactions;
		this.sortTransactions();
	}
	
	public ArrayList<Transaction> getTransactions()
	{
		ArrayList<Transaction> copy = new ArrayList<>();
		for(Transaction x: transactions)
		{
			copy.add(x.copy());
		}
		
		return copy;
	}
	
	public void setTransactions(ArrayList<Transaction> transactions)
	{
		this.transactions = transactions;
	}
	
	public String getAccountName()
	{
		return accountName;
	}
	
	public String getAccountNo()
	{
		return accountNo;
	}
	
	public float getBalance()
	{
		return balance;
	}
	
	public void setBalance(float balance)
	{
		this.balance = balance;
	}
	
	public boolean makeTransfer(int amount)
	{
		
		return true;
	}
	

	public String toString()
	{
		return accountName+" "+accountNo+" "+balance+"\n";
	}
	
	public void sortTransactions()
	{
		Collections.sort(transactions);
		Collections.reverse(transactions);
	}
}
