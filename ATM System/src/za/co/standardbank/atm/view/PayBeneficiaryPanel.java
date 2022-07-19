package za.co.standardbank.atm.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.standardbank.atm.control.BeneficiaryController;


public class PayBeneficiaryPanel extends JPanel{
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
	JButton payButton;
	JPanel leftUp;
	JPanel rightUp;
	JPanel leftDown;
	JPanel rightDown;
	JButton benPanelButton;
	
	public PayBeneficiaryPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating panels that are going to be visible in this panel---------- start------
		 userIDPanel = new JPanel();
		 pinPanel = new JPanel();
		 loginButtonPanel = new JPanel();
		
		userIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		
		JPanel BeneficiaryButtonPanel = new JPanel();
		BeneficiaryButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		
		//--------------------------------------------------------------------- end-----
		
		// creating and populating the contents of this page------------------- start------
		amountLabel = new JLabel("Amount");
		amountTextField = new JTextField(13);
		
		accountLabel = new JLabel("Account");
		
		accountComboBox = new JComboBox<>();
		accountComboBox.addItem("");
		accountComboBox.addItem("Professional");
		accountComboBox.addItem("Student Achiever");
		
		
		payButton = new JButton("Pay");
		
		benPanelButton = new JButton("Beneficiaries");
		benPanelButton.addActionListener(e -> new BeneficiaryPanel());
		
		BeneficiaryButtonPanel.add(benPanelButton);
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
		loginButtonPanel.add(payButton);
		//----------------------------------------------------------------------end---
		
		
		//creating the panel for the label that displays errors----------start------------		
		errorPanel = new JPanel();
		errorLabel = new JLabel();
		errorLabel.setForeground(Color.red);
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		errorPanel.add(errorLabel);
		//---------------------------------------------------------------end---------------
		
		JPanel benDetailsPanel = new JPanel();
		benDetailsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		benDetailsPanel.add(new JLabel(
				BeneficiaryPanel.tempBenName
				+" "+BeneficiaryPanel.tempAccountNumber
				+" "+BeneficiaryPanel.tempBenBankName));
		payButton.addActionListener
		(
				e->
				{					
					errorLabel.setText(BeneficiaryController
							.payBen(
									amountTextField.getText(), 
									(String) accountComboBox.getSelectedItem(), 
									BeneficiaryPanel.tempAccountNumber));
					if(errorLabel.getText().length() == 0)
					{
						String message = BeneficiaryPanel.tempBenName+ " successfully paid";
						JOptionPane.showMessageDialog(MainFrame.frame,message);	
					}
				}
		);
		
		//--------------adding all the panels to this panel------ start--------------------------
		add(BeneficiaryButtonPanel);
		add(new JPanel());
		add(new JPanel());
		add(benDetailsPanel); // added to center the contents of this panel
		add(errorPanel);
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
