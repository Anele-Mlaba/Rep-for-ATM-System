package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.orm.EntityManagerFactory;
import za.co.standardbank.atm.util.UserInputValidations;

public class DepositController {
	
	/*
	 * returns an empty string if the deposit is successful
	 * returns an error message in a form of a string if the deposit wasn't successful
	 */
	public static String makeDeposit(String amount, String accountName)
	{
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(accountName);
			if(errorMessageForAccount.equals(""))
			{
				Account account = EntityManagerFactory.of(Account.class).readForeign(Customer.customer)
						.stream()
						.map(acc -> (Account)acc)
						.filter(acc -> acc.getAccountName().equals(accountName))
						.findFirst()
						.orElseThrow(NullPointerException::new);	
				
				account.setBalance(account.getBalance()+Integer.parseInt(amount));
				
				String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
				
				Transaction transaction = new Transaction("Deposit", "+"+amount,date, account.getAccountNo());
				
				EntityManagerFactory.of(Transaction.class).persist(transaction);
				
				EntityManagerFactory.of(Account.class).update(account);
				
				return "";
												
			}
			else
				return errorMessageForAccount;
		}
		else
			return errorMessageForAmount;		
	}	
}