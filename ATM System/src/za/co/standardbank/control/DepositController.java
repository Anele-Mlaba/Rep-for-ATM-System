package za.co.standardbank.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import za.co.standardbank.mapping.Collect;
import za.co.standardbank.mapping.Populate;
import za.co.standardbank.model.Account;
import za.co.standardbank.model.Customer;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;
import za.co.standardbank.model.Transaction;

public class DepositController {
	
	public static String makeDeposit(String amount, String account)
	{
		if(amount.equals("0"))
		{	
			return "You can't deposit R0";
		}
		else if(amount.contains("."))
		{
			return "Make sure your amount does not contain decimals";
		}
		
		else 
		{

			try
			{
				int parsedAmount = Integer.parseInt(amount.trim());

				if(account.length() == 0)
					return "Make sure you choose an account!";
				
				ArrayList<? super Account> accounts = Customer.customer.getAccounts();
				
				if(account.equals("Professional"))
				{
					Professional acc =  ((Professional)accounts.get(0));
					accounts.set(0, completeDeposit(acc,parsedAmount));
				}
				else if (account.equals("Student Achiever"))
				{
					StudentAchiever acc =  ((StudentAchiever)accounts.get(1));	
					accounts.set(1, completeDeposit(acc,parsedAmount));
				}
				
				Customer.customer.setAccounts(accounts);
				Collect.collect();  // updates the file after the deposit has been made
				Populate.populate(Customer.fileName);
				
				return "";
						
			}
			
			catch(NumberFormatException f)
			{
				if(amount.trim().equals(""))
				{
					return "Amount field can't be empty!";
				}
				else
				{
					return "Make sure your amount is numeric";
				}					
			}
		}		
		
		
	}
	
	private  static <T extends Account> T  completeDeposit(T acc, int amount)
	{
		acc.setBalance(acc.getBalance()+amount);	
		ArrayList<Transaction> transactions = acc.getTransactions();
		String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
		
		System.out.println(new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime()));
		
		transactions.add(new Transaction(date, "Deposit","+"+amount));
		acc.setTransactions(transactions);			
		acc.sortTransactions();
		return acc;
	}
	
}


