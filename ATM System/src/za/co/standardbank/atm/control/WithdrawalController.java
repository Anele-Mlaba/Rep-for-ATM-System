package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import za.co.standardbank.atm.exceptions.InsufficientFundsException;
import za.co.standardbank.atm.mapping.Collect;
import za.co.standardbank.atm.mapping.Populate;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Professional;
import za.co.standardbank.atm.model.StudentAchiever;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.util.UserInputValidations;

public class WithdrawalController {
	
	private static float PROFESSIONAL_ACCOUNT_WITHDRAWAL_CHARGE = 12.50f;
	private static float STUDENTACHIEVER_ACCOUNT_WITHDRAWAL_CHARGE = 7.50f;
	
	
	/*
	 * returns an empty string if the withdrawal is successful
	 * returns an error message in a form of a string if the withdrawal wasn't successful
	 */
	public static String makeWithdrawal(String amount, String account)
	{
		
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(account);
			if(errorMessageForAccount.equals(""))
			{			
				int parsedAmount = Integer.parseInt(amount.trim());
				ArrayList<? super Account> accounts = Customer.customer.getAccounts();
				
				try
				{
					if(account.equals("Professional"))
					{
						Professional acc =  ((Professional)accounts.get(0));
						accounts.set(0, completeWithdrawal(acc, parsedAmount, PROFESSIONAL_ACCOUNT_WITHDRAWAL_CHARGE));
					}
					else if (account.equals("Student Achiever"))
					{
						StudentAchiever acc =  ((StudentAchiever)accounts.get(1));	
						accounts.set(1, completeWithdrawal(acc, parsedAmount, STUDENTACHIEVER_ACCOUNT_WITHDRAWAL_CHARGE));
					}
					
					Customer.customer.setAccounts(accounts);
					Collect.collect();  // updates the file after the deposit has been made
					Populate.populate(Customer.fileName);
				}
				catch (InsufficientFundsException insufFundsEx)
				{
					return insufFundsEx.getMessage();
				}
				
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
	 * exists to avoid duplicate code, after all validations the actual withdrawal is done here
	 * this method is used by the makeWithdrawal method above
	 */
	private  static <T extends Account> T  completeWithdrawal(T acc, int amount, float charge) throws InsufficientFundsException
	{
		if(acc.getBalance()-amount-charge >= 0)
		{
			float newBalance = Math.round((acc.getBalance()-amount-charge)*100);
		    newBalance =newBalance / 100;
			acc.setBalance(newBalance);	
			ArrayList<Transaction> transactions = acc.getTransactions();
			
			String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
			
			transactions.add(new Transaction(date, "Withdrawal","-"+amount));
			transactions.add(new Transaction(date, "Charge","-"+charge));
			acc.setTransactions(transactions);			
			acc.sortTransactions();
			return acc;
		}
		else
		{
			throw new InsufficientFundsException("chosen account does not have enough funds to complete the transaction");
		}
	}
	
}
