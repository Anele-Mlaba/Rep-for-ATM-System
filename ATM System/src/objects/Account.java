package objects;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Account {
	protected String accountName;
	protected String accountNo;
	protected int balance;
	protected ArrayList<Transaction> transactions;
	
	public Account(String accountName, String accountNo, int balance, 
			ArrayList<Transaction> transactions)
	{
		this.accountName = accountName;
		this.accountNo = accountNo;
		this.balance = balance;
		this.transactions = transactions;
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
	
	public String getAccountName()
	{
		return accountName;
	}
	
	public String getAccountNo()
	{
		return accountNo;
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public boolean makeDeposit(int amount)
	{
		balance+=amount;
		try {
			transactions.add(new Transaction(new SimpleDateFormat("mm/dd/yyyy").parse("01/23/2022"), "Deposit", "+"+amount));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean makeTransfer(int amount)
	{
		
		return true;
	}
	
	public String toString()
	{
		return accountName+" "+accountNo+" "+balance+"\n";
	}
}
