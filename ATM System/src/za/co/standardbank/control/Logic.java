package za.co.standardbank.control;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

import za.co.standardbank.main.Main;
import za.co.standardbank.mapping.Collect;
import za.co.standardbank.mapping.Populate;
import za.co.standardbank.model.Account;
import za.co.standardbank.model.Customer;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;

public class Logic  {
	public static Customer customer; //all objects are accessed through this object
	public static String fileName = "";
	
	/*
	 * uses the Collect class to collect all the information from the objects back to the file
	 */
	public static boolean collect()
	{
		Collect.pin();
		Collect.personalInfo();
		Collect.transactions();
		Collect.accounts();
		Collect.writeToFile();
		return true;
	}
	
	/*
	 * uses the Populate class to take the data from the file and writes it to relevant objects
	 */
	public static boolean populate(String fileName)
	{	
		File file = new File(fileName.trim());
		Scanner scan;
		try
		{
			scan = new Scanner(file);
			Populate populate = new Populate();
			populate.readFromFile(scan);
			customer = populate.customer();
			return true;		
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("log these errors");
			return false;
		}
	}
	
	/*
	 * the login button calls this method
	 */
	public static boolean login(String id, String pin)
	{
		try {
			File file = new File(id.trim()+".txt");
			Scanner scan = new Scanner(file);
			populate(id.trim()+".txt"); // when the file exists then populate the objects
			fileName = id.trim()+".txt"; // store the name of the file for later use
			if(customer.getPin().equals(pin))
			{
				return true;// when the password matches then return true meaning we are logged in
			}
			else
			{
				return false;
			}			
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public static boolean makeDeposit(int amount, String account)
	{
		ArrayList<? super Account> accounts = customer.getAccounts();
		if(account.equals("Professional"))
		{
			Professional acc =  ((Professional)accounts.get(0));
			acc.makeDeposit(amount);
			accounts.set(0, acc);
			customer.setAccounts(accounts);
			return true;
		}
		else if (account.equals("Student Achiever"))
		{
			StudentAchiever acc =  ((StudentAchiever)accounts.get(1));
			acc.makeDeposit(amount);
			accounts.set(1, acc);
			customer.setAccounts(accounts);
			return true;
		}
		return false;
	}
}
