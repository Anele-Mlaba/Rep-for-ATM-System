package za.co.standardbank.atm.control;

import za.co.standardbank.atm.mapping.Populate;
import za.co.standardbank.atm.model.Customer;

public class LoginController {
	
	
	public static boolean login(String id, String pin)
	{
		
		if(Populate.populate(id.trim()+".txt")) 
		{
			Customer.fileName = id.trim()+".txt"; // store the name of the file for later use
			if(Customer.customer.getPin().equals(pin))
			{
				return true;// when the password matches then return true meaning we are logged in
			}
			else
			{
				return false;
			}	
		}
		
		else
		{
			return false;
		}	
	}
}
