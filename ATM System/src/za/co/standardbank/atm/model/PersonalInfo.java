package za.co.standardbank.atm.model;
import java.util.Date;

public class PersonalInfo {
	private String idNo;
	private String name, surname, gender, address, phoneNumber, email;
	private Date dateOfBirth;
	
	public PersonalInfo(String idNo, String name, String surname, 
			String gender, Date dateOfBirth, String email, String phoneNumber , String address)
	{
		this.idNo = idNo;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.address = address;
		this.dateOfBirth = dateOfBirth;	
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public PersonalInfo copy()
	{
		return new PersonalInfo(idNo, name, surname, 
				gender, dateOfBirth, email, phoneNumber , address);
	}
	
	public String getId()
	{
		return idNo;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public Date getDOB()
	{
		return dateOfBirth;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String toString()
	{
		return   " ID no          : "+idNo
				+"\n Name         : "+name
				+"\n Surname      : "+surname
				+"\n gender       : "+gender
				+"\n address      : "+address
				+"\n DOB          : "+dateOfBirth
				+"\n emai         : "+email
				+"\n phone Number :"+phoneNumber;	
			
	}
	
}
