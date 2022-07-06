package za.co.standardbank.view;

import za.co.standardbank.control.LoginController;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	final int TEXT_FIELD_WIDTH = 100;
	final int TEXT_FIELD_HEIGHT = 50;
	String id = "";
	String pin = "";
	JTextField userIDTextField;
	JPasswordField pinTextField;
	JLabel errorLabel;
	JPanel userIDPanel;
	JPanel pinPanel;
	JPanel loginButtonPanel;
	JPanel errorPanel;
	JLabel userIDLabel;
	JLabel pinLabel;
	JButton loginButton;

	
	//constructor for when the system is opened
	public LoginPanel()
	{	
		initialize();
	}
	
	//constructor for when the user logs out
	public LoginPanel(Boolean LoggedOut)
	{	
		initialize();
		MainFrame.frame.drawFrame(this);
	}
	
	// initializes the panel
	private void initialize()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// creating panels for all the contents of this panel----------start-----
		userIDPanel = new JPanel();
		pinPanel = new JPanel();
		loginButtonPanel = new JPanel();
		errorPanel = new JPanel();
		
		userIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		pinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		loginButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		//--------------------------------------------------------------end------
		
		//-------------content-------------------------------start-------
		userIDLabel = new JLabel("User ID");
		userIDTextField = new JTextField(20);
		
		pinLabel = new JLabel("PIN");
		pinTextField = new JPasswordField(20);
		
		loginButton = new JButton("login");
		loginButton.addActionListener(new LoginButtonAction());
		
		errorLabel = new JLabel();
		
		//------------------------------------------------------end--------
		
		// making additional panels for better alignment---------start-----
		errorPanel.add(errorLabel);
		JPanel leftUp = new JPanel();
		JPanel rightUp = new JPanel();
		JPanel leftDown = new JPanel();
		JPanel rightDown = new JPanel();
		
		leftUp.add(userIDLabel);
		rightUp.add(userIDTextField);
		leftDown.add(pinLabel);
		rightDown.add(pinTextField);
		
		userIDPanel.add(leftUp);
		userIDPanel.add(rightUp);
		pinPanel.add(leftDown);
		pinPanel.add(rightDown);
		loginButtonPanel.add(loginButton);
		//-------------------------------------------------------------------end-----
		
		
		
		
		//-----------adds all the panels to this panel-------------------------start--
		//for centering
		add(new JPanel());
		add(new JPanel());
		
		add(errorPanel);
		
		
		add(userIDPanel);
		add(pinPanel);
		add(loginButtonPanel);
		
		//for centering
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		//-----------------------------------------------------------------------end--
		
	}
	
	private void setId()
	{
		id = userIDTextField.getText();
	}
	
	private void setPin()
	{
		pin = pinTextField.getText();
	}
	
	//used by login button to write errors
	private void writeError()
	{
		errorLabel.setText("Incorrect pin or invalid ID");
		errorLabel.setForeground(Color.red);
		repaint();
	}
	
	
	
	class LoginButtonAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			setPin();
			setId();
			/*
			 * if the login method returns true
			 * then take the user to the main menu
			 */
			if(LoginController.login(id, pin))
			{
				new MainMenuPanel();
			}
			
			else
			{
				writeError();
				pinTextField.setText("");
				userIDTextField.setText("");
			}			
		}		
	}
}
