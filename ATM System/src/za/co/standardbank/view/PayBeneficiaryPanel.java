package za.co.standardbank.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PayBeneficiaryPanel extends JPanel {
	public PayBeneficiaryPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(MainFrame.frame.getMainMenuButtonPanel());
		MainFrame.frame.drawFrame(this);
	}
}
