package mappingClasses;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import objects.Account;
import objects.Customer;
import objects.PersonalInfo;
import objects.Professional;
import objects.StudentAchiever;
import objects.Transaction;

public class Populate {
	 LinkedList<String> data = new LinkedList<>();
	 Map<Transaction, String> transactions = new HashMap<>();
		
	public void readFromFile(Scanner scan)
	{
		while(scan.hasNextLine())
		{
			data.add(scan.nextLine());
			
		}
	}
	
	public PersonalInfo personalInfo()
	{
		
		
		String idNo = data.pollFirst();
		String name = data.pollFirst();
		String surname = data.pollFirst();
		String gender = data.pollFirst();
		Date dob = new Date();
		try {
			 dob = new SimpleDateFormat("mm/dd/yyyy").parse(data.pollFirst());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = data.pollFirst();
		String phoneNumber = data.pollFirst();
		String address = data.pollFirst();
		
		return new PersonalInfo(idNo, name, surname, gender, dob, email, phoneNumber, address);
	}
	
	public void transactions()
	{
		String trans = data.pollFirst();
		while(!trans.equals("*"))
		{
			String[] temp = trans.split(",");
			
			String accountName = temp[0];
			String type = temp[1];
			String amount = temp[3];
			
			Date dateOfTrans = new Date();
			try {
				dateOfTrans = new SimpleDateFormat("mm/dd/yyyy").parse(temp[2]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			transactions.put(new Transaction(dateOfTrans, type, amount), accountName);			
			trans = data.pollFirst();
		}
	}
	
	public ArrayList<? super Account> accounts()
	{
		transactions();
		
		ArrayList<? super Account> accounts = new ArrayList<>();
		ArrayList<Transaction> profTrans = new ArrayList<>();
		ArrayList<Transaction> stdTrans = new ArrayList<>();
		
		for(Map.Entry<Transaction, String> x: transactions.entrySet())
		{
			if(x.getValue().equalsIgnoreCase("Professional"))
			{
				profTrans.add(x.getKey());
			}
			else
			{
				stdTrans.add(x.getKey());
			}
		}
		
		String profName = data.pollFirst();
		int profBalance = Integer.parseInt(data.pollFirst());
		String profAccountNo = data.pollFirst();
		
		String stdName = data.pollFirst();
		int stdBalance = Integer.parseInt(data.pollFirst());
		String stdAccountNo = data.pollFirst();
		
		accounts.add(new Professional(profName, profAccountNo, profBalance, profTrans));
		accounts.add(new StudentAchiever(stdName, stdAccountNo, stdBalance, stdTrans));
		System.out.println(accounts);
		return accounts;
	}
	
	public Customer customer()
	{
		return new Customer(pin(), personalInfo(), accounts());
	}
	
	public String pin()
	{
			
		return data.pollFirst();
	}
		
}
