package GUI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import MainClass.Main;

public class MakeWithdrawalPanel extends JPanel {
	public MakeWithdrawalPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Main.frame.getMainMenuButtonPanel());
		Main.frame.drawFrame(this);
	}
}
