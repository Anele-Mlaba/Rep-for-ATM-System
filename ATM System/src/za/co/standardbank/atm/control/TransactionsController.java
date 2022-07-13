package za.co.standardbank.atm.control;

import java.util.List;
import java.util.stream.Collectors;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;


public class TransactionsController {
	public final static int TRANSACTIONS_PER_PAGE = 16;
	
	/*
	 * this method returns a list of strings with the size equal to the TRANSACTIONS_PER_PAGE constant 
	 *  it can also return a list of strings with the size of 0, which means there are 0 transactions available
	 *   starting from the integer the coder specified in the parameters
	 * 
	 * if the transactions starting from the int the coder specified are less than TRANSACTIONS_PER_PAGE
	 * 	then the method fills the list with empty strings until the list is equal to TRANSACTIONS_PER_PAGE
	 */
	public static List<String> getTransactions(int startingFrom, String accountName)
	{
		List<String>  transactionsFromAccount =
				Customer.customer.getAccounts().stream()
					.filter(account ->((Account)account).getAccountName().equals(accountName))
					.flatMap(account -> ((Account)account).getTransactions().stream())
					.skip(startingFrom)
					.limit(TRANSACTIONS_PER_PAGE)
					.map(trans -> trans.toString())
					.collect(Collectors.toList());
		
		if(transactionsFromAccount.size() == 0)
			return transactionsFromAccount;
		
		while(transactionsFromAccount.size() < TRANSACTIONS_PER_PAGE)
			transactionsFromAccount.add("");
			
		return transactionsFromAccount;	
	}	
	
}
