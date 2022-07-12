package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import za.co.standardbank.atm.mapping.Collect;
import za.co.standardbank.atm.mapping.Populate;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Professional;
import za.co.standardbank.atm.model.StudentAchiever;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.util.UserInputValidations;

public class DepositController {
	
	/*
	 * returns an empty string if the deposit is successful
	 * returns an error message in a form of a string if the deposit wasn't successful
	 */
	public static String makeDeposit(String amount, String account)
	{
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
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
	
	/*
	 * exists to avoid duplicate code, after all validations the actual deposit is done here
	 * this method is used by the makeDeposit method above
	 */
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


