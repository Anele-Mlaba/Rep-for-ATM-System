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

public class AddBeneficiaryPanel extends JPanel{
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
	JButton addButton;
	JPanel leftUp;
	JPanel rightUp;
	JPanel leftDown;
	JPanel rightDown;
	JButton benPanelButton;
	JPanel benNamePanel;
	JLabel benNameLabel;
	JTextField benNameTextField;
	
	public AddBeneficiaryPanel ()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//creating panels that are going to be visible in this panel---------- start------
		 benNamePanel = new JPanel();
		 userIDPanel = new JPanel();
		 pinPanel = new JPanel();
		 loginButtonPanel = new JPanel();
		
		benNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		userIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		
		JPanel BeneficiaryButtonPanel = new JPanel();
		BeneficiaryButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		
		//--------------------------------------------------------------------- end-----
		
		// creating and populating the contents of this page------------------- start------
		amountLabel = new JLabel("Account Number");
		amountTextField = new JTextField(13);
		
		benNameLabel = new JLabel("Ben Name");
		benNameTextField = new JTextField(13);
		
		accountLabel = new JLabel("Bank Name");
		
		accountComboBox = new JComboBox<>();
		accountComboBox.addItem("");
		accountComboBox.addItem("Standard Bank");
		accountComboBox.addItem("Capitec Bank");
		accountComboBox.addItem("ABSA");
		accountComboBox.addItem("NedBank");
		accountComboBox.addItem("Tyme Bank");
		accountComboBox.addItem("Discovery Bank");
		
		addButton = new JButton("Add");
		
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
		
		benNamePanel.add(benNameLabel);
		benNamePanel.add(benNameTextField);
		userIDPanel.add(leftUp);
		userIDPanel.add(rightUp);
		pinPanel.add(leftDown);
		pinPanel.add(rightDown);
		loginButtonPanel.add(addButton);
		//----------------------------------------------------------------------end---
		
		
		//creating the panel for the label that displays errors----------start------------		
		errorPanel = new JPanel();
		errorLabel = new JLabel();
		errorLabel.setForeground(Color.red);
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		errorPanel.add(errorLabel);
		//---------------------------------------------------------------end---------------
		
		addButton.addActionListener
		(
				e->
				{					
					this.errorLabel.setText
					(
							BeneficiaryController
							.addBeneficiary(
									this.benNameTextField.getText(),
									(String) this.accountComboBox.getSelectedItem(),
									this.amountTextField.getText())
					);
					
					if(errorLabel.getText().length()==0)
					{
						JOptionPane.showMessageDialog(MainFrame.frame,"Beneficiary added");
						benNameTextField.setText("");
						amountTextField.setText("");	
						accountComboBox.setSelectedIndex(0);
					}
				}
		);
		
		//--------------adding all the panels to this panel------ start--------------------------
		add(BeneficiaryButtonPanel);
		add(new JPanel());
		add(new JPanel());
		add(errorPanel);
		add(benNamePanel); // added to center the contents of this panel
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
