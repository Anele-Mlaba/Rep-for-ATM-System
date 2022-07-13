package za.co.standardbank.atm.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import za.co.standardbank.atm.util.AccountViewTemplate;


public class ProfessionalAccountPanel extends JPanel {
	
	public  ProfessionalAccountPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(AccountViewTemplate.createAccountViewTemplate("Professional"));		
		MainFrame.frame.drawFrame(this);
	}
}
