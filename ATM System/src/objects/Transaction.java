package objects;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private Date date;
	private String type;
	private String amount;
	
	public Transaction(Date date, String type, String amount)
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
		SimpleDateFormat dateFormat= new SimpleDateFormat("MM/dd/yyyy");
		return type+","+dateFormat.format(date)+","+amount;
	}
}
