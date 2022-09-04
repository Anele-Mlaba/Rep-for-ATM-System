package za.co.standardbank.atm.view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.standardbank.atm.control.BeneficiaryController;
import za.co.standardbank.atm.control.TransferController;

public class TransferPanel extends JPanel {
	
	
	JLabel errorLabel;
	JComboBox<String> accountComboBox;
	JTextField amountTextField;
	int amount;
	String account;
	JLabel amountLabel;
	JPanel userIDPanel;
	JPanel pinPanel;
	JPanel loginButtonPanel;
	JPanel errorPanel;
	JLabel accountLabel;
	JButton depositButton;
	JPanel leftUp;
	JPanel rightUp;
	JPanel leftDown;
	JPanel rightDown;
	
	String accName;
	public TransferPanel(String accName)
	{
		this.accName = accName;	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JPanel accountsPanel = new JPanel();
		accountsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		JButton accountsButton = new JButton("Accounts");
		
		accountsButton.addActionListener(e -> new AccountsPanel());
		accountsPanel.add(accountsButton);
		
		//creating panels that are going to be visible in this panel---------- start------
		 userIDPanel = new JPanel();
		 pinPanel = new JPanel();
		 loginButtonPanel = new JPanel();
		
		userIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		//--------------------------------------------------------------------- end-----
		
		// creating and populating the contents of this page------------------- start------
		amountLabel = new JLabel("Amount");
		amountTextField = new JTextField(13);
		
		accountLabel = new JLabel("Account");
		
		accountComboBox = new JComboBox<>();
		accountComboBox.addItem("");
		if(!this.accName.equals("Professional")) accountComboBox.addItem("Professional");
		if(!this.accName.equals("Student Achiever"))accountComboBox.addItem("Student Achiever");
		
		
		depositButton = new JButton("Transfer");
		depositButton.addActionListener
		(
				e->
				{
					errorLabel.setText(TransferController.
							makeTransfer(
									accName,
									(String) accountComboBox.getSelectedItem(), 
									amountTextField.getText()
									));
					if(errorLabel.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(MainFrame.frame,"Transfer successful");	
					}
				}
				
		);
		//------------------------------------------------------------------------end--------
		
		//----- these panels make it easy to align the contents------- start--------
		JPanel leftUp = new JPanel();
		JPanel rightUp = new JPanel();
		JPanel leftDown = new JPanel();
		JPanel rightDown = new JPanel();
		
		leftUp.add(amountLabel);
		rightUp.add(amountTextField);
		leftDown.add(accountLabel);
		rightDown.add(accountComboBox);
		
		userIDPanel.add(leftUp);
		userIDPanel.add(rightUp);
		pinPanel.add(leftDown);
		pinPanel.add(rightDown);
		loginButtonPanel.add(depositButton);
		//----------------------------------------------------------------------end---
		
		
		//creating the panel for the label that displays errors----------start------------		
		errorPanel = new JPanel();
		errorLabel = new JLabel();
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		errorPanel.add(errorLabel);
		//---------------------------------------------------------------end---------------
		
		//--------------adding all the panels to this panel------ start--------------------------
		
		
		add(accountsPanel);
		add(errorPanel);
		add(new JPanel());
		add(new JLabel("transfer from "+this.accName));
		add(new JPanel());
		
		
		add(userIDPanel);
		add(pinPanel);
		add(loginButtonPanel);
		
		
		//these panels are only added to center the contents of this panel
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		//--------------adding all the panels to this panel------ end----------------------------
		
		
		
		MainFrame.frame.drawFrame(this);	//draw this Panel to the frame
	}

}
