package za.co.standardbank.exceptions;

public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(String message)
	{
		super(message);
	}
}
