package za.co.standardbank.util;

public class UserInputValidations {
	public static String validateAmountInput(String amount)
	{
		if(amount.equals("0"))
		{	
			return "You can't deposit R0";
		}
		else if(amount.contains("."))
		{
			return "Make sure your amount does not contain decimals";
		}
		else
		{
			try
			{
				Integer.parseInt(amount.trim());
			}
			catch(NumberFormatException f)
			{
				if(amount.trim().equals(""))
				{
					return "Amount field can't be empty!";
				}
				else
				{
					return "Make sure your amount is numeric";
				}					
			}
		}		
		return "";
	}
	
	public static String validateAccountInput(String account)
	{
		if(account.length() == 0)
			return "Make sure you choose an account!";
		return "";
	}
}
