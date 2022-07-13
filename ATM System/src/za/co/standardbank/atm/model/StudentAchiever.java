package za.co.standardbank.atm.model;
import java.util.ArrayList;

public class StudentAchiever extends Account{
	
	private static float WITHDRAWAL_CHARGE = 7.50f;
	
	public StudentAchiever(String accountName, String accountNo, float balance, 
			ArrayList<Transaction> transactions)
	{
		super(accountName, accountNo, balance, 
				 transactions);
	}
	
	public boolean payBeneficiary(int amount)
	{
		return true;
	}
	
	public boolean makeWithdrawal(int amount)
	{
		return true;
	}
	
	public StudentAchiever copy()
	{
		return new StudentAchiever(accountName, accountNo, balance, transactions);
	}
	
	@Override
	public float getWithdrawalChargeAmount() {
		return this.WITHDRAWAL_CHARGE;
	}
	
}
