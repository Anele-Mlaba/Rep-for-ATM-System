package za.co.standardbank.atm.mapping;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import za.co.standardbank.atm.model.Account;
import za.co.standardbank.atm.model.Beneficiary;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.model.PersonalInfo;
import za.co.standardbank.atm.model.Professional;
import za.co.standardbank.atm.model.StudentAchiever;
import za.co.standardbank.atm.model.Transaction;

public class Populate {
	 private List<String> customerDataFromFile = new LinkedList<>();
	 private Map<Transaction, String> transactions = new HashMap<>();
		
	public void readFromFileToList(BufferedReader reader) throws IOException
	{
		customerDataFromFile =(LinkedList<String>) reader.lines()
				.collect(Collectors.toCollection(LinkedList::new));
	}
	
	public String pin()
	{
			
		return ((LinkedList<String>)customerDataFromFile).pollFirst();
	}
	
	
	public PersonalInfo personalInfo()
	{	
		String idNo = ((LinkedList<String>)customerDataFromFile).pollFirst();
		String name = ((LinkedList<String>)customerDataFromFile).pollFirst();
		String surname = ((LinkedList<String>)customerDataFromFile).pollFirst();
		String gender = ((LinkedList<String>)customerDataFromFile).pollFirst();
		Date dob = new Date();
		try {
			 dob = new SimpleDateFormat("mm/dd/yyyy").parse(((LinkedList<String>)customerDataFromFile).pollFirst());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String email = ((LinkedList<String>)customerDataFromFile).pollFirst();
		String phoneNumber = ((LinkedList<String>)customerDataFromFile).pollFirst();
		String address = ((LinkedList<String>)customerDataFromFile).pollFirst();
		
		return new PersonalInfo(idNo, name, surname, gender, dob, email, phoneNumber, address);
	}
	
	public void transactions()
	{
		String trans = ((LinkedList<String>)customerDataFromFile).pollFirst();
		while(!trans.equals("*"))
		{
			String[] temp = trans.split(",");
			
			String accountName = temp[0];
			String type = temp[1];
			String date = temp[2];
			String amount = temp[3];
			
			transactions.put(new Transaction(date, type, amount), accountName);			
			trans = ((LinkedList<String>)customerDataFromFile).pollFirst();
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
		
		String profName = ((LinkedList<String>)customerDataFromFile).pollFirst();
		float profBalance = Float.parseFloat(((LinkedList<String>)customerDataFromFile).pollFirst());
		String profAccountNo = ((LinkedList<String>)customerDataFromFile).pollFirst();
		
		String stdName = ((LinkedList<String>)customerDataFromFile).pollFirst();
		float stdBalance = Float.parseFloat(((LinkedList<String>)customerDataFromFile).pollFirst());
		String stdAccountNo = ((LinkedList<String>)customerDataFromFile).pollFirst();
		
		accounts.add(new Professional(profName, profAccountNo, profBalance, profTrans));
		accounts.add(new StudentAchiever(stdName, stdAccountNo, stdBalance, stdTrans));
		return accounts;
	}
	
	public List<Beneficiary> beneficiaries()
	{
		List<Beneficiary> beneficiaries = new ArrayList<>();
		String ben = ((LinkedList<String>)customerDataFromFile).pollFirst();
		do
		{
			String[] temp = ben.split(",");
			
			String benName = temp[0];
			String benAccountNumber = temp[1];
			String benBankName = temp[2];
			
			beneficiaries.add(new Beneficiary(benName, benAccountNumber, benBankName));			
			ben = ((LinkedList<String>)customerDataFromFile).pollFirst();
		}
		while(ben != null);
		
		System.out.println(beneficiaries);
		return beneficiaries;
	}
	
	public void customer()
	{
		new Customer(pin(), personalInfo(),accounts(), beneficiaries());
	}
	
	public static boolean populate(String fileName)
	{	
		if(Files.exists(Paths.get(fileName)))
		{
			try(BufferedReader reader = Files.newBufferedReader(Paths.get(fileName)))
			{
				Populate populate = new Populate();
				populate.readFromFileToList(reader);
				populate.customer();
				return true;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return false;
			}	
		}
		else
		{
			return false;
		}
	}
		
}
