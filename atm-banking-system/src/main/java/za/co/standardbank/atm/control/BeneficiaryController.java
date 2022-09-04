package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Beneficiary;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Transaction;
import za.co.standardbank.atm.orm.EntityManagerFactory;
import za.co.standardbank.atm.util.UserInputValidations;

public class BeneficiaryController {
	public static final int NUM_OF_BENEFICIARIES = 8;
	
	public static String addBeneficiary(String benName, String benBankName, String benAccNo)
	{
		String message = UserInputValidations.validateBankNameInput(benBankName);
		if(message.length() == 0)
		{
			message = UserInputValidations.validateAccNo(benAccNo);
			if(message.length() == 0)
			{
				EntityManagerFactory.of(Beneficiary.class)
				.persist(new Beneficiary(benAccNo, benName, benBankName, Customer.customer.getCustomerId()));
			}
		}
		
		return message;
	}
	
	public static List<String> getBeneficiaries(int StartingFrom)
	{
		List<Object> beneficiaries = getBeneficiaries();
		return beneficiaries == null? new ArrayList<String>() :beneficiaries
				.stream()
				.map(ben -> ((Beneficiary)ben).toString())
				.limit(NUM_OF_BENEFICIARIES)
				.collect(Collectors.toList());
	}
	
	public static List<String> getSearchedbeneficiaries(String userInput)
	{
		List<Object> beneficiaries = getBeneficiaries();
		
		return beneficiaries == null? new ArrayList<String>() :beneficiaries
				.stream()
				.map(ben -> ((Beneficiary)ben).toString())
				.filter(benString -> benString.toLowerCase().contains(userInput.toLowerCase()))
				.limit(NUM_OF_BENEFICIARIES)
				.collect(Collectors.toList());
	}
	
	
	public static String payBen(String amount, String accountName,String benAccountNumber)
	{
				
		String errorMessageForAmount = UserInputValidations.validateAmountInputAdvanced(amount);
		if(errorMessageForAmount.equals(""))
		{
			String errorMessageForAccount = UserInputValidations.validateAccountInput(accountName);
			if(errorMessageForAccount.equals(""))
			{	
				int parsedAmount = Integer.parseInt(amount.trim());
				Account account = AccountController.findAccount(accountName);
				
							
				if(account.getBalance() - parsedAmount >= 0 )
				{
					String benName = ((Beneficiary)EntityManagerFactory.of(Beneficiary.class)
							.read(benAccountNumber)).getBenName();
					
					float newBalance = account.getBalance() - parsedAmount;
					account.setBalance(newBalance);	
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
						
					EntityManagerFactory.of(Transaction.class)
					.persist(new Transaction("paid to "+ benName,"-"+amount, date, account.getAccountNo()));
					
					EntityManagerFactory.of(Account.class).update(account);
					
					return "";
				}	
				else
					return "Insuficient funds";	
			}
			else
				return errorMessageForAccount;	
		}
		else
			return errorMessageForAmount;	
	}
	
	
	private static List<Object> getBeneficiaries()
	{
		return EntityManagerFactory.of(Beneficiary.class)
				.readForeign(Customer.customer);
	}
}

