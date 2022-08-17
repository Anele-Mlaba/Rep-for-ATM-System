package za.co.standardbank.atm.model;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;

@TableName(name = "beneficiaries")
public class Beneficiary {
	
	@PrimaryKey(name = "Acc_No")
	private String benAccountNo;
	
	@Column(name = "ben_Name")
	private String benName;
	
	@Column(name = "Bank_Name")
	private String bankName;
	
	@Column(name = "Cust_ID")
	private String customerId;
	
	public Beneficiary() {}

	public Beneficiary(String benAccountNo, String BenName, String BankName, String customerId)
	{
		this.benAccountNo = benAccountNo;
		this.benName = BenName;
		this.bankName = BankName;
		this.customerId = customerId;
	}
	
	
	
	public String getBenAccountNo()
	{
		return benAccountNo;
	}

	public void setBenAccountNo(String benAccountNo) 
	{
		this.benAccountNo = benAccountNo;
	}

	public String getBenName() 
	{
		return benName;
	}

	public void setBenName(String benName) 
	{
		this.benName = benName;
	}

	public String getBankName() 
	{
		return bankName;
	}

	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getCustomerId() 
	{
		return customerId;
	}

	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	
	public String toString()
	{
		return benName+","+bankName+","+benAccountNo;
	}
}
