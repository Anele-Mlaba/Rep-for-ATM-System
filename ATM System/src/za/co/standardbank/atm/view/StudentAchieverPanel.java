package za.co.standardbank.atm.view;

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
import za.co.standardbank.atm.util.OutputFormatting;


public class StudentAchieverPanel extends JPanel {
	
	private static List<JLabel> transactionsLabels;
	private static int startingFromTransaction = 0;
	private JButton newerButton;
	private JButton olderButton;
	private boolean isOlderButtonDisabled = false;
	private boolean isNewerButtonDisabled = false;
	public StudentAchieverPanel()
	{
		JPanel olderandNewerButtonPanel = new JPanel();
		olderandNewerButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		JPanel forLabelPanelInner = new JPanel();
		forLabelPanelInner.setLayout(new BoxLayout(forLabelPanelInner, BoxLayout.Y_AXIS));
		
		JPanel forLabelPanelOutter = new JPanel();
		forLabelPanelOutter.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		transactionsLabels = new ArrayList<>();
		for(int i = 0; i<16; i++)
			transactionsLabels.add(new JLabel("no transactions to Show"));
		
		newerButton = new JButton("Newer Transactions");
		newerButton.addActionListener(new NewerTransactionButtonAction());
		
		olderButton = new JButton("Older Transactions");
		olderButton.addActionListener(new OlderTransactionButtonAction());
		
		olderandNewerButtonPanel.add(newerButton);
		olderandNewerButtonPanel.add(olderButton);
				
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		StudentAchieverPanel.mapTransactions(TransactionsController.
				getTransactions(StudentAchieverPanel.startingFromTransaction, "StudentAchiever"));
		
		add(MainFrame.frame.getAccountsButtonPanel());
		
		add(new JLabel("Student Achiever Account"));
		
		add(new JLabel("Balance: R"+BalanceController.getBalance("StudentAchiever")));
		for(JLabel transaction : transactionsLabels)
			forLabelPanelInner.add(transaction);
		
		forLabelPanelOutter.add(forLabelPanelInner);
		add(forLabelPanelOutter);
		add(olderandNewerButtonPanel);
		
		MainFrame.frame.drawFrame(this);
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
	
	private class NewerTransactionButtonAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(startingFromTransaction -16 >= 0)
			{
				startingFromTransaction -= 16;
				
				StudentAchieverPanel.mapTransactions(TransactionsController.
						getTransactions(StudentAchieverPanel.startingFromTransaction, "StudentAchiever"));
				
				if(isOlderButtonDisabled)
				{
					olderButton.enable();
					isOlderButtonDisabled = false;					
				}
				
				repaint();	
			}
			else
			{
				newerButton.disable();
				isNewerButtonDisabled = true;
			}					
		}
	}
	
	private class OlderTransactionButtonAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			startingFromTransaction += 16;
			
			List<String> transactionsList = TransactionsController.
					getTransactions(StudentAchieverPanel.startingFromTransaction, "StudentAchiever");
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
					
				StudentAchieverPanel.mapTransactions(transactionsList);
				repaint();				
			}			
		}
	}
}
