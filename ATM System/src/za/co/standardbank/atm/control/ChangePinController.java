
package za.co.standardbank.atm.control;
import za.co.standardbank.atm.mapping.Collect;
import za.co.standardbank.atm.model.Customer;

public class ChangePinController {

	public static String changePin(String oldPin, String newPin, String confirmPin) {
		String numbers = "0123456789";
		if(Customer.customer.getPin().equals(oldPin))
		{
			if(!oldPin.equals(newPin))
			{		
					if(newPin.length() == 5)
					{
						int count = 0;
						for(int c = 0; c<newPin.length()-1;c++)
						{
							if(Integer.parseInt(newPin.charAt(c+1)+"")-Integer.parseInt(newPin.charAt(c)+"") == 1)
								count++;
						}
						if(count == newPin.length()-1)
							return "pin cannot be consecutive digits";
						else count = 0;
						
						for(int c = 0; c<newPin.length()-1;c++)
						{
							if(Integer.parseInt(newPin.charAt(c+1)+"")-Integer.parseInt(newPin.charAt(c)+"") == -1)
								count++;
						}
						if(count == newPin.length()-1)
							return "pin cannot be consecutive digits";
						else count = 0;
						
						for(int c = 0; c<newPin.length()-1;c++)
						{
							if(Integer.parseInt(newPin.charAt(c+1)+"")-Integer.parseInt(newPin.charAt(c)+"") == 0)
								count++;
						}
						if(count == newPin.length()-1)
							return "pin cannot consist of only identical digits. try mixing different digits";
						else count = 0;
						
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
							if(newPin.equals(confirmPin))
							{
								Customer.customer.setPin(newPin);
								Collect.collect();
								return "";
							}
							else return "confirmation pin does not match your new pin";
							
						}
						else return "pin should only contain digits";
					}
							
					else
						return "pin must be 5 digits long";
						
				}
				else
				{
					return "new Pin matched the already existing pin";
					
				}
			}
			else
			{
				return "the pin you entered doesn't match the existing pin";
			}
	}
}
