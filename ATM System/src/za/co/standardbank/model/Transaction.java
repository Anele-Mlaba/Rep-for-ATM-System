package za.co.standardbank.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Transaction implements Comparable<Transaction> {
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
	
	public String getDate()
	{
		return this.date;
	}

	@Override
	public int compareTo(Transaction other)
	{
		int returnedInteger = 0;
		List<String> monthsInAYear = new ArrayList<>();
		monthsInAYear.add(""); monthsInAYear.add("Jan");monthsInAYear.add("Feb"); monthsInAYear.add("Mar"); 
		monthsInAYear.add("Apr"); monthsInAYear.add("May"); monthsInAYear.add("Jun"); monthsInAYear.add("Jul");
		monthsInAYear.add("Aug"); monthsInAYear.add("Sep"); monthsInAYear.add("Oct"); monthsInAYear.add("Nov");
		monthsInAYear.add("Dec");
		
		String[] thisFullDate = this.date.split(" ");
		String[] thisDateWithoutTime = thisFullDate[0].split("/");
		String[] thisTimeOnly = thisFullDate[1].split(":");
		
		String[] otherFullDate = other.date.split(" ");
		String[] otherDateWithoutTime = otherFullDate[0].split("/");
		String[] otherTimeOnly = otherFullDate[1].split(":");
		
		int thisYear = Integer.parseInt(thisDateWithoutTime[0]);
		int otherYear = Integer.parseInt(otherDateWithoutTime[0]);
		
		int thisMonth = monthsInAYear.indexOf(thisDateWithoutTime[1]);
		int otherMonth = monthsInAYear.indexOf(otherDateWithoutTime[1]);
		
		int thisDay = Integer.parseInt(thisDateWithoutTime[2]);
		int otherDay = Integer.parseInt(otherDateWithoutTime[2]);
		
		int thisHour = Integer.parseInt(thisTimeOnly[0]);
		int otherHour = Integer.parseInt(otherTimeOnly[0]);
		
		int thisMin = Integer.parseInt(thisTimeOnly[1]);
		int otherMin = Integer.parseInt(otherTimeOnly[1]);
		
		if(thisYear == otherYear)
		{
			if(thisMonth == otherMonth)
			{
				if(thisDay == otherDay)
				{
					if(thisHour == otherHour)
					{
						if(thisMin == otherMin) 
							returnedInteger = 0;
						else if(thisMin > otherMin)
							returnedInteger = 1;
						else
							returnedInteger = -1;
					}
					else if(thisHour > otherHour)
						return 1;
					else if(thisHour < otherHour)
						return -1;
				}
				else if (thisDay > otherDay)
					returnedInteger = 1;
				else if (thisDay < otherDay)
					returnedInteger =-1;	
			}
			else if(thisMonth > otherMonth)
				returnedInteger = 1;
			else if(thisMonth < otherMonth)
				returnedInteger = -1;
		}
		else if(thisYear > otherYear)
			returnedInteger = 1;
		else
			returnedInteger =-1;
		
		return returnedInteger;
	}
	
	
		
}
