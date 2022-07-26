package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import za.co.standardbank.atm.mapping.Collect;
import za.co.standardbank.atm.mapping.Populate;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.util.UserInputValidations;

public class WithdrawalController {
	
	/*
	 * returns an empty string if the withdrawal is successful
	 * returns an error message in a form of a string if the withdrawal wasn't successful
	 */
	public static String makeWithdrawal(String amount, String accountName)
	{	
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(accountName);
			if(errorMessageForAccount.equals(""))
			{			
				int parsedAmount = Integer.parseInt(amount.trim());
			
				Account account = (Account) Customer.customer.getAccounts().stream()
						.filter(accountInStream ->((Account)accountInStream).getAccountName().equals(accountName))
						.findFirst()
						.orElseThrow();
				
				if(account.getBalance() - parsedAmount-account.getWithdrawalChargeAmount() >= 0)
				{
					float newBalance = Math.round((account.getBalance()-parsedAmount-account.
							getWithdrawalChargeAmount())*100);
				    newBalance =newBalance / 100;
					account.setBalance(newBalance);	
					ArrayList<Transaction> transactions = account.getTransactions();
					
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
					
					transactions.add(new Transaction(date, "Withdrawal","-"+parsedAmount));
					transactions.add(new Transaction(date, "Charge","-"+account.getWithdrawalChargeAmount()));
					account.setTransactions(transactions);			
					account.sortTransactions();
				}
				else
				{
					return "Insufficient funds";
				}
				
				Customer.customer.setAccount(account);
				Collect.collect();  // updates the file after the withdrawal has been made
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
}
