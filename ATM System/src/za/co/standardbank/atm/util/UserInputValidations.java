package za.co.standardbank.atm.util;

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
}
