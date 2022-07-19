package za.co.standardbank.atm.model;

public class Beneficiary {
	private String bankName, accountNumber, benName;
	private int useFrequency;
	public Beneficiary(String benName, String bankName, String accountNumber)
	{
		this.setBankName(bankName);
		this.setAccountNumber(accountNumber);
		this.setBenName(benName);
		setUseFrequency(0);
		
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBenName() {
		return benName;
	}
	public void setBenName(String benName) {
		this.benName = benName;
	}
	
	public String toString()
	{
		return benName+","+bankName+","+accountNumber;
	}
	public int getUseFrequency() {
		return useFrequency;
	}
	public void setUseFrequency(int useFrequency) {
		this.useFrequency = useFrequency;
	}
}
