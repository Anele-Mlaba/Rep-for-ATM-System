package za.co.standardbank.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class AccountsPanel extends JPanel{
	public AccountsPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(MyFrame.frame.getMainMenuButtonPanel());
		MyFrame.frame.drawFrame(this);
	}
}
