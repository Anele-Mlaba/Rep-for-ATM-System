package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.orm.EntityManagerFactory;
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
				
				if(BalanceController.getBalance(accountFromName) - parsedAmount >= 0)
				{
					Account accountFrom = AccountController.findAccount(accountFromName);		
					Account accountTo = AccountController.findAccount(accountToName);
					
					accountFrom.setBalance(accountFrom.getBalance()-parsedAmount);
					accountTo.setBalance(accountTo.getBalance()+parsedAmount);
					
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
					
					Transaction transTo = new Transaction("Transfer From "+accountToName, "+"+amount,date, accountTo.getAccountNo());
					Transaction transFrom = new Transaction("Transfer to "+accountFromName, "-"+amount,date, accountFrom.getAccountNo());
					
					EntityManagerFactory.of(Account.class).update(accountFrom);
					EntityManagerFactory.of(Account.class).update(accountTo);
					EntityManagerFactory.of(Transaction.class).persist(transFrom);
					EntityManagerFactory.of(Transaction.class).persist(transTo);
					
					return "";
				}
				
				else
					return "insufficient funds";
				
			}
			else
				return errorMessageForAccount;
		}
		else
			return errorMessageForAmount;
	}
}
