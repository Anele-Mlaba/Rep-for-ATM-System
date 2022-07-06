package za.co.standardbank.mapping;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.LinkedList;

import za.co.standardbank.model.Account;
import za.co.standardbank.model.Customer;
import za.co.standardbank.model.PersonalInfo;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;
import za.co.standardbank.model.Transaction;

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
		Collect.writeToFile();
		return true;
	}
	
	public static void test()
	{
		for(String x: CustomerDataFromModels)
		{
			System.out.println(x);
		}
	}
}
