package za.co.standardbank.atm.control;

import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManager;
import za.co.standardbank.atm.orm.EntityManagerFactory;
import za.co.standardbank.atm.util.UserInputValidations;

public class ChangePinController {

	public static String changePin(String oldPin, String newPin, String confirmPin) 
	{
		
		String validationMessage = UserInputValidations.validatePin(oldPin, newPin, confirmPin);
		if(validationMessage.length() == 0)
		{
			EntityManager entityManager = EntityManagerFactory.of(Customer.class);
			Customer.customer.setPin(newPin);
			entityManager.update(Customer.customer);
			return "";
		}
		else
		{
			return validationMessage;
		}		
	}
}