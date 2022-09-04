package za.co.standardbank.atm.model;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;

@TableName(name = "customers")
public class Customer {
	@PrimaryKey(name = "Cust_ID") 
	private String customerId;
	
	@Column(name = "Pin")
	private String pin;
	
	@Column(name = "ID_No")
	private String idNo;
	
	public static Customer customer;
	
	public Customer() {}
	
	public Customer(String customerId, String pin, String idNo)
	{
		this.customerId = customerId;
		this.pin = pin;
		this.idNo = idNo;
	}
	
	public String getCustomerId() {
		return customerId;
	}

//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getIdNo() {
		return idNo;
	}

//	public void setIdNo(String idNo) 
//	{
//		this.idNo = idNo;
//	}
}
