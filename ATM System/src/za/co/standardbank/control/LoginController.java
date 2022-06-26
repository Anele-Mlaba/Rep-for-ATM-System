package za.co.standardbank.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import za.co.standardbank.main.Main;

public class LoginController {
	
	public static boolean login(String id, String pin)
	{
		try {
			File file = new File(id.trim()+".txt");
			Scanner scan = new Scanner(file);
			Main.populate(id.trim()+".txt"); // when the file exists then populate the objects
			Main.fileName = id.trim()+".txt"; // store the name of the file for later use
			if(Main.customer.getPin().equals(pin))
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
}
