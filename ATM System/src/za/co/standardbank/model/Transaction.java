package za.co.standardbank.model;


public class Transaction {
	private String date;
	private String type;
	private String amount;
	
	public Transaction(String date, String type, String amount)
	{
		this.date = date;
		this.type = type;
		this.amount = amount;
	}
	
	public Transaction copy()
	{
		return new Transaction(date, type, amount);
	}
		
	public String toString()
	{
		return type+","+date+","+amount;
	}
}
