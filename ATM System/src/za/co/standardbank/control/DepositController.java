package za.co.standardbank.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import za.co.standardbank.main.Main;
import za.co.standardbank.model.Account;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;
import za.co.standardbank.model.Transaction;

public class DepositController {
	
	public static boolean makeDeposit(int amount, String account)
	{
		ArrayList<? super Account> accounts = Main.customer.getAccounts();
		
		if(account.equals("Professional"))
		{
			Professional acc =  ((Professional)accounts.get(0));
			accounts.set(0, completeDeposit(acc,amount));
		}
		else if (account.equals("Student Achiever"))
		{
			StudentAchiever acc =  ((StudentAchiever)accounts.get(1));	
			accounts.set(1, completeDeposit(acc,amount));
		}
		
		Main.customer.setAccounts(accounts);
		Main.collect();  // updates the file after the deposit has been made
		Main.populate(Main.fileName);
		return true;
	}
	
	private  static <T extends Account> T  completeDeposit(T acc, int amount)
	{
		 acc.setBalance(acc.getBalance()+amount);
		try
		{
			ArrayList<Transaction> transactions = acc.getTransactions();
			transactions.add(new Transaction(new SimpleDateFormat("mm/dd/yyyy").parse("06/17/2022"), "Deposit", "+"+amount));
			acc.setTransactions(transactions);
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return acc;
	}
	
	
}


