package za.co.standardbank.atm.model;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;

@TableName(name = "accounts")
public class Account 
{
	@PrimaryKey(name = "Acc_No")
	private String accountNo;
	
	@Column(name = "Acc_Name")
	private String accountName;
	
	@Column(name = "Balance")
	private float balance;
	
	@Column(name = "Cust_ID")
	private String customerId;
	
	
	public Account() {}
	
	public Account(String accountNo, String accountName, float balance, String customerId)
	{
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.balance = balance;
		this.customerId = customerId;
	}
	
	public String getAccountNo() 
	{
		return accountNo;
	}
	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}
	public String getAccountName() 
	{
		return accountName;
	}
	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}
	public float getBalance() 
	{
		return balance;
	}
	public void setBalance(float balance) 
	{
		this.balance = balance;
	}
	public String getCustomerId() 
	{
		return customerId;
	}
	public void setCustomerId(String customer_Id) 
	{
		this.customerId = customer_Id;
	}
}
