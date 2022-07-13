package za.co.standardbank.atm.util;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import za.co.standardbank.atm.control.BalanceController;
import za.co.standardbank.atm.control.TransactionsController;
import za.co.standardbank.atm.view.MainFrame;

public class AccountViewTemplate {
	
	private static List<JLabel> transactionsLabels;
	private static int startingFromTransaction;
	private static JButton  olderButton;
	private static JButton  newerButton;
	private static boolean isOlderButtonDisabled;
	private static boolean isNewerButtonDisabled;
	private static JPanel completePanelTemplate;
	private static String accountNameGlobal;
		
	public static JPanel createAccountViewTemplate(String accountName)
	{
		completePanelTemplate = new JPanel();
		isNewerButtonDisabled = false;
		isOlderButtonDisabled = false;
		startingFromTransaction = 0;
		accountNameGlobal = accountName;

		
		JPanel olderandNewerButtonPanel = new JPanel();
		olderandNewerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		//this panel is for the transactions to appear in the center of the frame
		JPanel forLabelPanelInner = new JPanel();
		forLabelPanelInner.setLayout(new BoxLayout(forLabelPanelInner, BoxLayout.Y_AXIS));
		
		//this panel is for the labels to be written from top to bottom instead of left and right
		JPanel forLabelPanelOutter = new JPanel();
		forLabelPanelOutter.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		transactionsLabels = new ArrayList<>();
		for(int i = 0; i<16; i++)
			transactionsLabels.add(new JLabel(""));
		
		newerButton = new JButton("Newer Transactions");
		newerButton.addActionListener(new NewerTransactionButtonAction());
		
		olderButton = new JButton("Older Transactions");
		olderButton.addActionListener(new OlderTransactionButtonAction());
		
		olderandNewerButtonPanel.add(newerButton);
		olderandNewerButtonPanel.add(olderButton);
				
		completePanelTemplate.setLayout(new BoxLayout(completePanelTemplate, BoxLayout.Y_AXIS));
		
		mapTransactions(TransactionsController.
				getTransactions(startingFromTransaction, accountName));
		
		completePanelTemplate.add(MainFrame.frame.getAccountsButtonPanel());
		completePanelTemplate.add(new JLabel(accountName+" Account"));
		completePanelTemplate.add(new JLabel("Balance: R"+BalanceController.getBalance(accountName)));
		for(JLabel transaction : transactionsLabels)
			forLabelPanelInner.add(transaction);
		
		forLabelPanelOutter.add(forLabelPanelInner);
		completePanelTemplate.add(forLabelPanelOutter);
		completePanelTemplate.add(olderandNewerButtonPanel);
		
		
		return completePanelTemplate;
	}
	
	private static void mapTransactions(List<String> transactionsList)
	{
		int i = 0;
		for(JLabel transactionLabel : transactionsLabels)
		{
			transactionLabel.setText(transactionsList.get(i));
			transactionLabel = OutputFormatting.formatTransactionLabel(transactionLabel);
			i++;
		}
	}
	
	
	private static class NewerTransactionButtonAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(startingFromTransaction -16 >= 0)
			{
				startingFromTransaction -= 16;
				
				mapTransactions(TransactionsController.
						getTransactions(startingFromTransaction, accountNameGlobal));
				
				if(isOlderButtonDisabled)
				{
					olderButton.enable();
					isOlderButtonDisabled = false;					
				}
				
				completePanelTemplate.repaint();	
			}
			else
			{
				newerButton.disable();
				isNewerButtonDisabled = true;
			}					
		}
	}
	
	private static class OlderTransactionButtonAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			startingFromTransaction += 16;
			
			List<String> transactionsList = TransactionsController.
					getTransactions(startingFromTransaction, accountNameGlobal);
			if(transactionsList.size() == 0)
			{
				olderButton.disable();
				isOlderButtonDisabled = true;
				startingFromTransaction -= 16;
			}
			else
			{
				if(isNewerButtonDisabled)
				{
					newerButton.enable();
					isNewerButtonDisabled = false;					
				}
					
				mapTransactions(transactionsList);
				completePanelTemplate.repaint();				
			}			
		}
	}

}
