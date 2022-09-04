package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.orm.EntityManagerFactory;
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
				Account account = AccountController.findAccount(accountName);
				float withdrawalCharge = AccountController.getWithdrawalChargeAmount(accountName);
				
				if(account.getBalance() - parsedAmount - withdrawalCharge >= 0)
				{
					float newBalance = Math.round((account.getBalance() - parsedAmount - withdrawalCharge)*100);
				    newBalance =newBalance / 100;
				    
					account.setBalance(newBalance);	
					
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
					
					Transaction withdrawalTrans = 
							new Transaction("Withdrawal","-"+parsedAmount,date,account.getAccountNo());
					Transaction withdrawalChargeTrans = 
							new Transaction("Withdrawal charge", "-"+withdrawalCharge,date, account.getAccountNo());
					
					EntityManagerFactory.of(Transaction.class).persist(withdrawalTrans);
					EntityManagerFactory.of(Transaction.class).persist(withdrawalChargeTrans);
					EntityManagerFactory.of(Account.class).update(account);
					
					return "";	
				}
				else
					return "Insufficient funds";
			}
			else
				return errorMessageForAccount;
		}
		else
			return errorMessageForAmount;				
	}	
}