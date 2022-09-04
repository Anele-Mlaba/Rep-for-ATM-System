package za.co.standardbank.atm.model;

import java.util.ArrayList;
import java.util.List;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;


@TableName(name = "transactions")
public class Transaction implements Comparable<Transaction>{
	@PrimaryKey(name = "Trans_ID")
	private int transId;
	
	@Column(name = "Trans_Type")
	private String type;
	
	@Column(name = "Amount")
	private String amount;
	
	@Column(name = "Trans_Date")
	private String date;
	
	@Column(name = "Acc_No")
	private String accNo;
	
	public Transaction() {}
	
	public Transaction(String type, String amount,String date, String accNo)
	{
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.accNo = accNo;
	}

//	public int getTransId() {
//		return transId;
//	}
//
//	public void setTransId(int transId) {
//		this.transId = transId;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getAmount() {
//		return amount;
//	}
//
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//
//	public String getDate() {
//		return date;
//	}
//
//	public void setDate(String date) {
//		this.date = date;
//	}
//
//	public String getAccNo() {
//		return accNo;
//	}
//
//	public void setAccNo(String accNo) {
//		this.accNo = accNo;
//	}
//	
	public String toString()
	{
		return type+","+date+","+amount;
	}

	@Override
	public int compareTo(Transaction other) {
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
