package za.co.standardbank.atm.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountsPanel extends JPanel{
	public AccountsPanel()
	{
		add(MainFrame.frame.getMainMenuButtonPanel());
			
		
		JPanel buttonsOuterPanel = new JPanel();
		buttonsOuterPanel.setLayout(new BorderLayout(10,10));
		
		JPanel twoButtons = new JPanel();
		twoButtons.setLayout(new GridLayout(1,2));
		
		JButton StudentAchieverButton = new JButton("Student Achiever");
		StudentAchieverButton.addActionListener((e)->new StudentAchieverPanel());
		
		JButton ProfessionalButton = new JButton("Professional");
		ProfessionalButton.addActionListener((e)->new ProfessionalAccountPanel());
		
		twoButtons.add(StudentAchieverButton);
		twoButtons.add(ProfessionalButton);
		
		buttonsOuterPanel.add(twoButtons);
		add(buttonsOuterPanel);	
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		MainFrame.frame.drawFrame(this);
	}
}
