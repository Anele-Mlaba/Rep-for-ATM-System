package za.co.standardbank.atm.model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {
	private String pin;
	private PersonalInfo personalInfo;
	private ArrayList<? super Account> accounts;
	private List<Beneficiary> beneficiaries;
	public static Customer customer;
	public static String fileName = "";
	
	
	
	public Customer(String pin, PersonalInfo personalInfo,
			 ArrayList<? super Account> accounts,  List<Beneficiary> beneficiaries)
	{
		this.pin = pin;
		this.personalInfo = personalInfo;
		this.accounts = accounts;
		this.beneficiaries = beneficiaries;
		customer = this;
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

	public void setAccount(Account account) {
		if(account.getAccountName().equals("Professional"))
			accounts.set(0, account);
		else
			accounts.set(1, account);
		
	}
	
	public List<Beneficiary> getBeneficiaries()
	{
		return beneficiaries.size() == 0? new ArrayList<>(): beneficiaries.stream()
																	.collect(Collectors.toList());
	
	}
	
	public void setBeneficiary(Beneficiary ben)
	{
		for(Beneficiary benInList: beneficiaries)
		{
			if(benInList.getAccountNumber().equals(ben.getAccountNumber()))
			{
				benInList = ben;
				break;
			}
		}
	}	
}
