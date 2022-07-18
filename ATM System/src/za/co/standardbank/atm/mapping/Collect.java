package za.co.standardbank.atm.mapping;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Beneficiary;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.PersonalInfo;
import za.co.standardbank.atm.model.Professional;
import za.co.standardbank.atm.model.StudentAchiever;
import za.co.standardbank.atm.model.Transaction;

public class Collect {
	private static LinkedList<String> CustomerDataFromModels = new LinkedList<String>();
	private static SimpleDateFormat DateOfBirth= new SimpleDateFormat("MM/dd/yyyy");
	
	
	public static void pin()
	{
		CustomerDataFromModels.add(Customer.customer.getPin());
	}
	
	public static void personalInfo()
	{
		PersonalInfo personalInfo  = Customer.customer.getPersonalInfo();
		CustomerDataFromModels.add(personalInfo.getId());
		CustomerDataFromModels.add(personalInfo.getName());
		CustomerDataFromModels.add(personalInfo.getSurname());
		CustomerDataFromModels.add(personalInfo.getGender());
		CustomerDataFromModels.add(DateOfBirth.format(personalInfo.getDOB()));
		CustomerDataFromModels.add(personalInfo.getEmail());
		CustomerDataFromModels.add(personalInfo.getPhoneNumber());
		CustomerDataFromModels.add(personalInfo.getAddress());
	}
	
	public static void transactions()
	{
		ArrayList<? super Account> accounts = Customer.customer.getAccounts();
		for(Transaction x: ((Professional)accounts.get(0)).getTransactions())
		{
			CustomerDataFromModels.add("Professional,"+x.toString());
		}
		for(Transaction x:  ((StudentAchiever)accounts.get(1)).getTransactions())
		{
			CustomerDataFromModels.add("Student Achiever,"+x.toString());
		}
		CustomerDataFromModels.add("*");
	}
	
	private static void beneficiaries() 
	{
		List<Beneficiary> ben = Customer.customer.getBeneficiaries();
		
		for(Beneficiary ben1 : ben )
		{
			CustomerDataFromModels.add(ben1.toString());
		}		
	}
	
	
	public static void accounts()
	{
		ArrayList<? super Account> accounts = Customer.customer.getAccounts();
		CustomerDataFromModels.add(((Professional)accounts.get(0)).getAccountName());
		CustomerDataFromModels.add(((Professional)accounts.get(0)).getBalance()+"");
		CustomerDataFromModels.add(((Professional)accounts.get(0)).getAccountNo());
		CustomerDataFromModels.add(((StudentAchiever)accounts.get(1)).getAccountName());
		CustomerDataFromModels.add(((StudentAchiever)accounts.get(1)).getBalance()+"");
		CustomerDataFromModels.add(((StudentAchiever)accounts.get(1)).getAccountNo());
	}
	
	public static void writeToFile()
	{
		try (FileWriter writer = new FileWriter(Customer.fileName))
		{
			for(String x: CustomerDataFromModels)
				writer.write(x+"\n");
		
		} catch (IOException e) 
		
		{
			e.printStackTrace();
		}
		
		CustomerDataFromModels.clear();
	}
	
	public static boolean collect()
	{
		Collect.pin();
		Collect.personalInfo();
		Collect.transactions();
		Collect.accounts();
		Collect.beneficiaries();
		Collect.writeToFile();
		return true;
	}
	
}
