package za.co.standardbank.model;
import java.util.ArrayList;

public class Customer {
	private String pin;
	private PersonalInfo personalInfo;
	private ArrayList<? super Account> accounts;
	
	public Customer(String pin, PersonalInfo personalInfo, ArrayList<? super Account> accounts)
	{
		this.pin = pin;
		this.personalInfo = personalInfo;
		this.accounts = accounts;
	}
	
	public String getPin()
	{
		return pin;
	}
	
	public void setPin(String newPin)
	{
		pin = newPin;
	}
	
	public ArrayList<? super Account> getAccounts()
	{
		ArrayList<? super Account> copy = new ArrayList<>();
		copy.add(((Professional)this.accounts.get(0)).copy());
		copy.add(((StudentAchiever)this.accounts.get(1)).copy());
		return copy;
	}
	
	public void setAccounts(ArrayList<? super Account> accounts)
	{
		this.accounts = accounts;
	}

	public PersonalInfo getPersonalInfo() {

		return personalInfo.copy();
	}
	
}
