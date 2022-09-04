package za.co.standardbank.atm.model;

import za.co.standardbank.atm.annotations.Column;
import za.co.standardbank.atm.annotations.PrimaryKey;
import za.co.standardbank.atm.annotations.TableName;

@TableName(name = "account_types")
public class AccountType {
	@PrimaryKey(name = "Acc_Name")
	String accountName;
	
	@Column(name = "Withdrawal_Charge")
	float withdrawalCharge;

//	public String getAccountName() {
//		return accountName;
//	}

	public float getWithdrawalCharge() {
		return withdrawalCharge;
	}
}
