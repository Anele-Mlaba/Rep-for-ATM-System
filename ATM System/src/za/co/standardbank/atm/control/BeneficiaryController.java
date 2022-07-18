package za.co.standardbank.atm.control;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import za.co.standardbank.atm.model.Customer;

public class BeneficiaryController {
	public static final int NUM_OF_BENEFICIARIES = 6;
	
	public static List<String> getbeneficiaries(int StartingFrom)
	{
		return Customer.customer.getBeneficiaries().size() == 0? new ArrayList<String>() :
			Customer.customer.getBeneficiaries().stream()
			.map(e->e.toString())
			.limit(NUM_OF_BENEFICIARIES)
			.collect(Collectors.toList());
	}
	
	public static List<String> getSearchedbeneficiaries(String userInput)
	{
		return Customer.customer.getBeneficiaries().size() == 0? new ArrayList<String>() :
			Customer.customer.getBeneficiaries().stream()
			.map(e->e.toString())
			.filter(e->e.toLowerCase().contains(userInput.toLowerCase()))
			.limit(NUM_OF_BENEFICIARIES)
			.collect(Collectors.toList());
	}

}
