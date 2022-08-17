package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManager;
import za.co.standardbank.atm.orm.EntityManagerFactory;

public class LoginController {
	
	public static boolean login(String id, String pin)
	{
		Customer customer;
		EntityManager entityManager = EntityManagerFactory.of(Customer.class);
		customer = (Customer)entityManager.read(Customer.class, id);
		
		if(customer == null)
			return false;
		else
		{	
			if(!customer.getPin().equals(pin))
				return false;
		}
				
		Customer.customer = customer;
		return true;
	}	
}
