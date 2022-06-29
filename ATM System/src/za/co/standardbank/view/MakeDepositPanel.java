package za.co.standardbank.view;

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

import za.co.standardbank.main.Main;
import za.co.standardbank.control.ChangePinController;
import za.co.standardbank.control.DepositController;
import za.co.standardbank.model.Professional;
import za.co.standardbank.model.StudentAchiever;


public class MakeDepositPanel extends JPanel {
	JLabel errorLabel;
	JComboBox accountComboBox;
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
		
		accountComboBox = new JComboBox();
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
		add(Main.frame.getMainMenuButtonPanel());
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
		
		
		
		Main.frame.drawFrame(this);	//draw this Panel to the frame
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
				if(((String)accountComboBox.getSelectedItem()).equals("Professional"))
				{
					Professional professional =  (Professional)Main.customer.getAccounts().get(0);
					String message = "Deposit Successful\n new Balance is R"+professional.getBalance();
					JOptionPane.showMessageDialog(Main.frame,message);					
				}
				else if(((String)accountComboBox.getSelectedItem()).equals("Student Achiever"))
				{
					StudentAchiever studentAchiever =  (StudentAchiever)Main.customer.getAccounts().get(1);
					String message = "Deposit Successful\nnew Balance is R"+studentAchiever.getBalance();
					JOptionPane.showMessageDialog(Main.frame,message);					
				}
			}	
			amountTextField.setText("");
			repaint();				
		}		
	}	
}
