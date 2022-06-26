package za.co.standardbank.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import za.co.standardbank.mapping.Collect;
import za.co.standardbank.mapping.Populate;
import za.co.standardbank.model.Customer;
import za.co.standardbank.view.MyFrame;


public class Main {
	
	public static MyFrame frame;
	public static Customer customer; //all objects are accessed through this object
	public static String fileName = "";
	
	public static void main(String [] args) 
	{
		frame = new MyFrame();
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
}
