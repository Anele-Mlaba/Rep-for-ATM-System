package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Customer;

public class LogOutController {
	public static void logOut()
	{
		Customer.customer = null;
	}
}