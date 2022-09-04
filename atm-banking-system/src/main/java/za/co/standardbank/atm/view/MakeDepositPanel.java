package za.co.standardbank.atm.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.standardbank.atm.control.BalanceController;
import za.co.standardbank.atm.control.DepositController;



public class MakeDepositPanel extends JPanel {
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
	
	public MakeDepositPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
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
		accountComboBox.addItem("Professional");
		accountComboBox.addItem("Student Achiever");
		
		
		depositButton = new JButton("Deposit");
		depositButton.addActionListener(new depositButtonAction());
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
		add(MainFrame.frame.getMainMenuButtonPanel());
		add(new JPanel()); // added to center the contents of this panel
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
	
	class depositButtonAction implements ActionListener
	{
		@Override		
		public void actionPerformed(ActionEvent e) 
		{
			errorLabel.setText(DepositController.
					makeDeposit(amountTextField.getText(), (String)accountComboBox.getSelectedItem()));
			errorLabel.setForeground(Color.red);
			
			if(errorLabel.getText().length() == 0)
			{
				String message = "Deposit Successful\n new Balance is R"
									+BalanceController.getBalance((String)accountComboBox.getSelectedItem());
				JOptionPane.showMessageDialog(MainFrame.frame,message);	
			}
			
			amountTextField.setText("");
			repaint();				
		}		
	}	
}
