package za.co.standardbank.atm.exceptions;

public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(String message)
	{
		super(message);
	}
}