package za.co.standardbank.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import za.co.standardbank.main.Main;
import za.co.standardbank.control.Logic;


//this class is responsible for the main menu of the system
public class MainMenuPanel extends JPanel {
	JPanel fourButtons;
	JPanel lowerPanel;
	
	public MainMenuPanel()
	{
		// set layout of this panel (Main Menu Panel)
		setLayout(new BorderLayout(10,10));
		
		
		//------ creating panels that are going to be inside this panel----start---
		fourButtons = new JPanel();
		fourButtons.setLayout(new GridLayout(2,2));
		
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		//------------------------------------------------------------------end-----
		
		
		//------------------------Buttons on the Main Menu tab-----start------------------
		JButton makeWithdrawalButton = new JButton("Make a Withdrawal");
		makeWithdrawalButton.addActionListener((e)->new MakeWithdrawalPanel());
		
		JButton makeDepositButton = new JButton("Make a Deposit");
		makeDepositButton.addActionListener((e)->new MakeDepositPanel());
		
		JButton payBeneficiaryButton = new JButton("Pay a Beneficiary");
		payBeneficiaryButton.addActionListener((e)->new PayBeneficiaryPanel());
		
		JButton accountsButton = new JButton("Accounts");
		accountsButton.addActionListener((e)->new AccountsPanel());
		
		JButton logOutButton = new JButton("Log out");
		logOutButton.addActionListener((e)->new LoginPanel(true));
		
		JButton myInfoButton = new JButton("My info");
		myInfoButton.addActionListener((e)-> new MyInfo());
		//-------------------------------------------------------end------------------
		
		//---- adding every button to its relevant panel------ start------
		fourButtons.add(makeWithdrawalButton);
		fourButtons.add(makeDepositButton);
		fourButtons.add(payBeneficiaryButton);
		fourButtons.add(accountsButton);
		
		lowerPanel.add(myInfoButton);
		lowerPanel.add(logOutButton);
		//--------------------------------------------------- end-------
		
		//----- adding all the panels to this (MainMenuPanel)- start ------
		add(fourButtons, BorderLayout.CENTER);
		add(lowerPanel,BorderLayout.PAGE_END);
		//------END ------------------------------------------------------
		
		//draw this Panel to the frame
		Main.frame.drawFrame(this);	
	}
	
	
	
}
