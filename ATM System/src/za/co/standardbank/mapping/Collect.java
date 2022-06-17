package za.co.standardbank.mapping;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import za.co.standardbank.control.Logic;
import za.co.standardbank.model.Account;
import za.co.standardbank.model.PersonalInfo;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;
import za.co.standardbank.model.Transaction;

public class Collect {
	private static LinkedList<String> data = new LinkedList<String>();
	private static SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
	
	public static void pin()
	{
		data.add(Logic.customer.getPin());
	}
	
	public static void personalInfo()
	{
		PersonalInfo personalInfo  = Logic.customer.getPersonalInfo();
		data.add(personalInfo.getId());
		data.add(personalInfo.getName());
		data.add(personalInfo.getSurname());
		data.add(personalInfo.getGender());
		data.add(dateFormat.format(personalInfo.getDOB()));
		data.add(personalInfo.getEmail());
		data.add(personalInfo.getPhoneNumber());
		data.add(personalInfo.getAddress());
	}
	
	public static void transactions()
	{
		ArrayList<? super Account> accounts = Logic.customer.getAccounts();
		for(Transaction x: ((Professional)accounts.get(0)).getTransactions())
		{
			data.add("Professional,"+x.toString());
		}
		for(Transaction x:  ((StudentAchiever)accounts.get(1)).getTransactions())
		{
			data.add("Student Achiever,"+x.toString());
		}
		data.add("*");
	}
	
	public static void accounts()
	{
		ArrayList<? super Account> accounts = Logic.customer.getAccounts();
		data.add(((Professional)accounts.get(0)).getAccountName());
		data.add(((Professional)accounts.get(0)).getBalance()+"");
		data.add(((Professional)accounts.get(0)).getAccountNo());
		data.add(((StudentAchiever)accounts.get(1)).getAccountName());
		data.add(((StudentAchiever)accounts.get(1)).getBalance()+"");
		data.add(((StudentAchiever)accounts.get(1)).getAccountNo());
	}
	
	public static void writeToFile()
	{
		try (FileWriter myWriter = new FileWriter(Logic.fileName)) {
			
			for(String x: data)
			{
				myWriter.write(x+"\n");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.clear();
	}
	public static void test()
	{
		for(String x: data)
		{
			System.out.println(x);
		}
	}
}
