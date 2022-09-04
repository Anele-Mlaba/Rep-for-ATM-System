//package za.co.standardbank.atm.model;
//
//import za.co.standardbank.atm.annotations.Column;
//import za.co.standardbank.atm.annotations.PrimaryKey;
//import za.co.standardbank.atm.annotations.TableName;
//
//@TableName(name = "customer_personal_info")
//public class PersonalInfo {
//	@PrimaryKey(name = "ID_No")
//	private String IdNo;
//	
//	@Column(name = "Cust_Name")
//	private String name;
//	
//	@Column(name = "Cust_Surname")
//	private String surname;
//	
//	@Column(name = "DOB")
//	private String dob;
//	
//	@Column(name = "Gender")
//	private String gender;
//	
//	@Column(name = "Address")
//	private String address;
//	
//	@Column(name = "email")
//	private String email;
//	
//	@Column(name = "cell_No")
//	private String cellNo;
//	
//	public static PersonalInfo personalInfo;
//	
//	public PersonalInfo() {}
//	
//	public PersonalInfo(String IdNo, String name, String Surname,
//			String dob, String gender, String address, String email, String cellNo)
//	{
//		this.IdNo = IdNo; this.name = name; this.surname = Surname;
//		this.dob = dob; this.gender = gender; this.address = address;
//		this.email = email; this.cellNo = cellNo;
//	}
//
//	public String getIdNo() {
//		return IdNo;
//	}
//
//	public void setIdNo(String idNo) {
//		IdNo = idNo;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSurname() {
//		return surname;
//	}
//
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
//
//	public String getDob() {
//		return dob;
//	}
//
//	public void setDob(String dob) {
//		this.dob = dob;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getCellNo() {
//		return cellNo;
//	}
//
//	public void setCellNo(String cellNo) {
//		this.cellNo = cellNo;
//	}
//}
