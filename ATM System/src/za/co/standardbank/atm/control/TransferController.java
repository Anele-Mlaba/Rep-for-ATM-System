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

public class TransferController {
	public static String makeTransfer(String accountFromName, String accountToName, String amount)
	{
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(accountToName);
			if(errorMessageForAccount.equals(""))
			{
				float parsedAmount = Float.parseFloat(amount.trim());
				
				Account accountFrom = (Account) Customer.customer.getAccounts().stream()
						.filter(accountInStream ->((Account)accountInStream).getAccountName().equals(accountFromName))
						.findFirst()
						.orElseThrow();
				
				Account accountTo;
				if(accountFrom.getBalance()-parsedAmount>=0)
				{
					accountTo = (Account) Customer.customer.getAccounts().stream()
							.filter(accountInStream ->((Account)accountInStream).getAccountName().equals(accountToName))
							.findFirst()
							.orElseThrow();
					
					accountFrom.setBalance(accountFrom.getBalance()-parsedAmount);
					accountTo.setBalance(accountTo.getBalance()+parsedAmount);
					
					ArrayList<Transaction> transactionsFrom = accountFrom.getTransactions();
					ArrayList<Transaction> transactionsTo = accountTo.getTransactions();
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
					
					transactionsFrom.add(new Transaction(date, "Transfer to"+accountToName,"-"+parsedAmount));
					accountFrom.setTransactions(transactionsFrom);			
					accountFrom.sortTransactions();
					
					transactionsTo.add(new Transaction(date, "Transfer From"+accountFromName,"+"+parsedAmount));
					accountTo.setTransactions(transactionsTo);			
					accountTo.sortTransactions();
					
					
					Customer.customer.setAccount(accountFrom);
					Customer.customer.setAccount(accountTo);
					Collect.collect();  // updates the file after the withdrawal has been made
					Populate.populate(Customer.fileName);
				}
				else
				{
					return "insufficient funds";
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
}
