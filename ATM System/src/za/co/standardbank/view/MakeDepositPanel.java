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
import za.co.standardbank.control.Logic;
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
	
	/*
	 * takes the amount entered by the user and assigns it to the amount variable
	 */
	private void setAmount()
	{
		amount = Integer.parseInt(amountTextField.getText().trim());
	}
	
	/*
	 * takes the account entered by the user and writes it to the account variable
	 */
	private void setAccount()
	{
		account = (String) accountComboBox.getSelectedItem();
	}
	
	/*
	 * deposit button uses this method to display errors
	 */
	private void setError(String errorMessage)
	{
		errorLabel.setForeground(Color.RED);
		errorLabel.setText(errorMessage);
	}
	
	class depositButtonAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(amountTextField.getText().equals("0"))
			{	
				setError("You can't deposit R0");
			}
			else if(amountTextField.getText().contains("."))
			{
				setError("Make sure your amount does not contain decimals");
			}
			
			//when the amount entered by the user contains no decimals and not 0
			else 
			{
				// if the amount is an integer, the transaction will proceed
				try
				{
					// just to make sure the number is an integer before proceeding
					Integer.parseInt(amountTextField.getText().trim());
					
					setAmount();
					setAccount();
					
					boolean completed = Logic.makeDeposit(amount, account);	
					
					Logic.collect();  // updates the file after the deposit has been made
					//Logic.populate(Logic.fileName);
					
					
					//---make sure an appropriate message is displayed-----------------------------start---------
					if(completed)
					{
						if(account.equals("Professional"))
						{
							Professional professional =  (Professional)Logic.customer.getAccounts().get(0);
							String message = "Deposit Successful\n new Balance is R"+professional.getBalance();
							JOptionPane.showMessageDialog(Main.frame,message);
							setError("");
							amountTextField.setText("");
						}
						else if(account.equals("Student Achiever"))
						{
							StudentAchiever studentAchiever =  (StudentAchiever)Logic.customer.getAccounts().get(1);
							String message = "Deposit Successful\nnew Balance is R"+studentAchiever.getBalance();
							JOptionPane.showMessageDialog(Main.frame,message);
							setError("");
							amountTextField.setText("");
						}
					}				
					else// in case the user did not choose an account
					{
						setError("Make sure you choose an account!");
					}
					//--------------------------------------------------------------------------------end-------------				
				}
				
				//if the amount is not a number, show the user these errors
				catch(NumberFormatException f)
				{
					if(amountTextField.getText().trim().equals(""))
					{
						setError("Amount field can't be empty!");
						amountTextField.setText("");
					}
					else
					{
						setError("Make sure your amount is numeric");
						amountTextField.setText("");
					}					
				}
			}			
		}		
	}	
}
