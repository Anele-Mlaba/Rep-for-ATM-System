package za.co.standardbank.atm.util;

import za.co.standardbank.atm.model.Customer;

public class UserInputValidations {
	public static String validateAmountInput(String amount)
	{
		if(amount.equals("0"))
			return "You can't deposit R0";
		
		else if(amount.contains("."))
			return "Make sure your amount does not contain decimals";
		else
		{
			try
			{
				Integer.parseInt(amount.trim());
			}
			catch(NumberFormatException f)
			{
				if(amount.trim().equals(""))
					return "Amount field can't be empty!";
				else
					return "Make sure your amount is numeric"+ amount;			
			}
		}		
		return "";
	}
	
	
	//in addition to the validateAmountInput method, this method also checks that the input the user entered is a multiple of 10
	public static String validateAmountInputAdvanced(String amount)
	{
		String firstCheckResult = validateAmountInput(amount);
		if(firstCheckResult.length() == 0) //meaning all validations passed, now it's time to check if it's a multiple of 10
		{
			if(Integer.parseInt(amount)%10 != 0)
				return "ATM only uses notes. make sure your amount is a multiple of 10";
			else
				return "";
		}
		return firstCheckResult;
	}
	
	//makes sure the user chose an account
	public static String validateAccountInput(String account)
	{
		if(account.length() == 0)
			return "Make sure you choose an account!";
		return "";
	}
	
	public static String validateBankNameInput(String bankName)
	{
		if(bankName.length() == 0)
			return "Make sure you choose Bank!";
		return "";
	}
	
	public static String validateAccNo(String accNo)
	{
		String numbers = "1234567890";
		for(int i = 0; i<accNo.length(); i++)
		{
			if(!numbers.contains(accNo.charAt(i)+""))
			{
				return "Account should only contain numbers";
			}
		}
			
		if(accNo.length()>15)
			return "Account number too long";
		else if ((accNo.charAt(0)+"").equals("0"))
			return "account number does not start with 0";
		return "";		
	}
	
	
	public static String validatePin(String oldPin, String newPin, String confirmPin)
	{
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
