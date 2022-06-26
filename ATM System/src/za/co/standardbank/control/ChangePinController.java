
package za.co.standardbank.control;

import javax.swing.JLabel;

import za.co.standardbank.main.Main;

public class ChangePinController {

	public static String changePin(String oldPin, String newPin) {
		String numbers = "0123456789";
		if(!oldPin.equals(newPin))
		{
			if(Main.customer.getPin().equals(oldPin))
			{		
				if(newPin.length() == 5)
				{
					int count = 0;
					for(int i = 0; i<newPin.length();i++)
					{
						for(int s = 0; s<numbers.length();s++)
						{
							if((numbers.charAt(s)+"").equals(newPin.charAt(i)+""))
							{
								count = 0;
								break;
							}
							else
							{
								count++;
							}
						}
						if(count != 0)
							break;
					}
					
					if(count == 0) 
					{
						Main.customer.setPin(newPin);
						Main.collect();
						return "";
					}
					else return "pin should only contain numbers";
				}
						
				else
					return "pin must be 5 numbers long";
					
			}
			else
			{
				return "the pin you entered doesn't match the existing pin";
			}
		}
		else
		{
			return "new Pin matched the already existing pin";
		}
	}
}
