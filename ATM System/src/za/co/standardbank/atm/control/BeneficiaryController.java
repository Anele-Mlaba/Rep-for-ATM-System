package za.co.standardbank.atm.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import za.co.standardbank.atm.mapping.Collect;
import za.co.standardbank.atm.mapping.Populate;
import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Beneficiary;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.Transaction;
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
				Customer.customer.addBeneficiary(new Beneficiary(benName, benAccNo, benBankName));
				Collect.collect();
				Populate.populate(Customer.fileName);
			}
		}
		
		return message;
	}
	
	public static List<String> getBeneficiaries(int StartingFrom)
	{
		return Customer.customer.getBeneficiaries().size() == 0? new ArrayList<String>() :
			Customer.customer.getBeneficiaries().stream()
			.map(e->e.toString())
			.limit(NUM_OF_BENEFICIARIES)
			.collect(Collectors.toList());
	}
	
	public static List<String> getSearchedbeneficiaries(String userInput)
	{
		return Customer.customer.getBeneficiaries().size() == 0? new ArrayList<String>() :
			Customer.customer.getBeneficiaries().stream()
			.map(e->e.toString())
			.filter(e->e.toLowerCase().contains(userInput.toLowerCase()))
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
				
				Account account = (Account) Customer.customer.getAccounts().stream()
						.filter(accountInStream ->((Account)accountInStream).getAccountName().equals(accountName))
						.findFirst()
						.orElseThrow();
				
				Beneficiary ben = Customer.customer.getBeneficiaries().stream()
						.filter(benInStream->benInStream.getAccountNumber().equals(benAccountNumber))
						.findFirst()
						.orElseThrow();
				
				if(account.getBalance() - parsedAmount >= 0 )
				{
					float newBalance = account.getBalance() - parsedAmount;
					
					account.setBalance(newBalance);	
					ArrayList<Transaction> transactions = account.getTransactions();
					
					String date = new SimpleDateFormat("yyyy/MMM/dd HH:mm").format(Calendar.getInstance().getTime());
					
					transactions.add(new Transaction(date, "Paid to "+ben.getBenName(),"-"+parsedAmount));
					account.setTransactions(transactions);			
					account.sortTransactions();
				}	
				else
				{
					return "Insuficient funds";
				}
				
				ben.setUseFrequency(ben.getUseFrequency()+1);
				Customer.customer.setBeneficiary(ben);
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
