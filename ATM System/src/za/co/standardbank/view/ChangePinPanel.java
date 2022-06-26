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

import za.co.standardbank.control.ChangePinController;
import za.co.standardbank.main.Main;
import za.co.standardbank.view.MakeDepositPanel.depositButtonAction;

public class ChangePinPanel extends JPanel {
	JLabel errorLabel;
	JTextField newPinTextField;
	JTextField oldPinTextField;
	JLabel oldPinLabel;
	JPanel oldPinPanel;
	JPanel newPinPanel;
	JPanel saveButtonPanel;
	JPanel errorPanel;
	JLabel newPinLabel;
	JButton saveButton;
	JPanel leftUp;
	JPanel rightUp;
	JPanel leftDown;
	JPanel rightDown;

	public ChangePinPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		//creating panels that are going to be visible in this panel---------- start------
		 oldPinPanel = new JPanel();
		 newPinPanel = new JPanel();
		 saveButtonPanel = new JPanel();
		
		oldPinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		newPinPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		saveButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		//--------------------------------------------------------------------- end-----
		
		// creating and populating the contents of this page------------------- start------
		oldPinLabel = new JLabel("Old Pin");
		oldPinTextField = new JTextField(13);
		
		newPinLabel = new JLabel("new Pin");
		
		newPinTextField = new JTextField(13);
				
		saveButton = new JButton("Save");
		saveButton.addActionListener(new ChangePinAction());
		//------------------------------------------------------------------------end--------
		
		//----- these panels make it easy to align the contents------- start--------
		JPanel leftUp = new JPanel();
		JPanel rightUp = new JPanel();
		JPanel leftDown = new JPanel();
		JPanel rightDown = new JPanel();
		
		leftUp.add(oldPinLabel);
		rightUp.add(oldPinTextField);
		leftDown.add(newPinLabel);
		rightDown.add(newPinTextField);
		
		oldPinPanel.add(leftUp);
		oldPinPanel.add(rightUp);
		newPinPanel.add(leftDown);
		newPinPanel.add(rightDown);
		saveButtonPanel.add(saveButton);
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
		
		
		add(oldPinPanel);
		add(newPinPanel);
		add(saveButtonPanel);
		
		
		//these panels are only added to center the contents of this panel
		add(new JPanel());
		add(new JPanel());
		add(new JPanel());
		//--------------adding all the panels to this panel------ end----------------------------
		errorLabel.setForeground(Color.RED);
		
		Main.frame.drawFrame(this);
	}
	
	private class ChangePinAction implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			errorLabel.setText(ChangePinController.changePin(oldPinTextField.getText(), newPinTextField.getText()));
			System.out.println(errorLabel.getText());
			if(errorLabel.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(Main.frame,"pin successfuly changed!");
				oldPinTextField.setText("");
				newPinTextField.setText("");
			}
				
			repaint();
		}
		
	}
	
}
