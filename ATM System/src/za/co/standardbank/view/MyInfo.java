package za.co.standardbank.view;


import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import za.co.standardbank.main.Main;

public class MyInfo extends JPanel
{
	public MyInfo()
	{
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton changePinButton = new JButton("Change Pin");
		
		JLabel infoLabel = new JLabel();
		infoLabel.setText("user infor here!");
		
		JPanel infoLabelPanel = new JPanel();
		infoLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		infoLabelPanel.add(infoLabel);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		ButtonPanel.add(changePinButton);
		add(Main.frame.getMainMenuButtonPanel());
		add(infoLabelPanel);
		add(ButtonPanel);
		
		Main.frame.drawFrame(this);
		
	}
}
