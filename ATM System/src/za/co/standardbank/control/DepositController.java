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
import za.co.standardbank.util.UserInputValidations;

public class DepositController {
	
	public static String makeDeposit(String amount, String account)
	{
		String errorMessageForAmount = UserInputValidations.validateAmountInput(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(account);
			if(errorMessageForAccount.equals(""))
			{
				int parsedAmount = Integer.parseInt(amount.trim());
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
			}
			else
			{
				return errorMessageForAccount;
			}
		}
		else
		{
			return errorMessageForAmount;
		}
		
		return "";				
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


