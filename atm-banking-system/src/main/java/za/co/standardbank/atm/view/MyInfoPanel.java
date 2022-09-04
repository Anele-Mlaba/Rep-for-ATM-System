package za.co.standardbank.atm.view;


import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyInfoPanel extends JPanel
{
	public MyInfoPanel()
	{
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton changePinButton = new JButton("Change Pin");
		changePinButton.addActionListener((e)->new ChangePinPanel());
		
		JLabel infoLabel = new JLabel();
		infoLabel.setText("user infor here!");
		
		JPanel infoLabelPanel = new JPanel();
		infoLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		infoLabelPanel.add(infoLabel);
		
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		ButtonPanel.add(changePinButton);
		add(MainFrame.frame.getMainMenuButtonPanel());
		add(infoLabelPanel);
		add(ButtonPanel);
		
		MainFrame.frame.drawFrame(this);
		
	}
}
