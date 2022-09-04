package za.co.standardbank.atm.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import za.co.standardbank.atm.util.AccountViewTemplate;



public class StudentAchieverPanel extends JPanel 
{
	public StudentAchieverPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(AccountViewTemplate.createAccountViewTemplate("Student Achiever"));		
		MainFrame.frame.drawFrame(this);		
	}
}
