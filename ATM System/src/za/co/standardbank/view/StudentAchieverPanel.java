package za.co.standardbank.view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import za.co.standardbank.control.BalanceController;
import za.co.standardbank.control.TransactionsController;

public class StudentAchieverPanel extends JPanel {
	
	private static List<JLabel> transactionsLabels;
	private static int startingFromTransaction = 0;
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
		
		JButton newerButton = new JButton("Newer Transactions");
		JButton olderButton = new JButton("Older Transactions");
		
		olderandNewerButtonPanel.add(newerButton);
		olderandNewerButtonPanel.add(olderButton);
				
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		StudentAchieverPanel.mapTransactions(TransactionsController.
				getTransactions(StudentAchieverPanel.startingFromTransaction, "StudentAchiever"));
		
		add(MainFrame.frame.getAccountsButtonPanel());
		
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
			i++;
		}
	}
}
