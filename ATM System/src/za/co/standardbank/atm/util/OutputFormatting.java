package za.co.standardbank.atm.util;

import java.awt.Color;

import javax.swing.JLabel;

public class OutputFormatting {
	public static JLabel formatTransactionLabel(JLabel transactionLabel)
	{
		
		if(transactionLabel.getText().contains("+"))
		{
			transactionLabel.setForeground(Color.GREEN);
		}
		else
		{
			transactionLabel.setForeground(Color.RED);
		}
		
		if(transactionLabel.getText().length()>0)
		{
			String [] transactionSeparated = transactionLabel.getText().split(",");
			StringBuffer transactionBufferString = new StringBuffer();
			transactionBufferString.append(transactionSeparated[0]);
			
			
			for(int i = 0; i < 50 - transactionSeparated[0].length();i++)
				transactionBufferString.append("_");
			
			transactionBufferString.append(transactionSeparated[1]);
			transactionBufferString.append("____");
			transactionBufferString.append(transactionSeparated[2]);
			transactionLabel.setText(transactionBufferString.toString());
			//System.out.println(transactionLabel.getText());
		}
		
		return transactionLabel;	
	}
}
