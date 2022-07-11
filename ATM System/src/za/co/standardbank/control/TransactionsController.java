package za.co.standardbank.control;

import java.util.List;

import za.co.standardbank.model.Customer;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;
import za.co.standardbank.model.Transaction;
import za.co.standardbank.util.OutputFormatting;

import java.util.ArrayList;

public class TransactionsController {
	public final static int TRANSACTIONS_PER_PAGE = 16;
	
	public static List<String> getTransactions(int startingFrom, String accountName)
	{
		List<String> transactions = new ArrayList<>();
		if(accountName.equals("Professional"))
		{
			ArrayList<Transaction> TransactionsFromProfessionalAccountModel=
					((Professional) Customer.customer.getAccounts().get(0)).getTransactions();
			
			int count = 0;
			for(int i = 0; i<TRANSACTIONS_PER_PAGE;i++)
			{
				if(startingFrom < TransactionsFromProfessionalAccountModel.size())
				{
					transactions.add(TransactionsFromProfessionalAccountModel.get(startingFrom).toString());
					startingFrom++;
					if (count == 0)
						count++;
				}
				else
				{
					if (count == 0)
							return transactions; //no reason to add empty strings if the whole list has only them
					transactions.add("");
				}
			}			
		}
		else
		{
			ArrayList<Transaction> TransactionsFromStudentAchieverAccountModel=
					((StudentAchiever) Customer.customer.getAccounts().get(1)).getTransactions();
			for(int i = 0; i<TRANSACTIONS_PER_PAGE;i++)
			{
				if(startingFrom < TransactionsFromStudentAchieverAccountModel.size())
				{
					transactions.add(TransactionsFromStudentAchieverAccountModel.get(startingFrom).toString());
					startingFrom++;
				}
				else
				{
					transactions.add("");
				}
			}	
		}
		return transactions;
	}	
}
