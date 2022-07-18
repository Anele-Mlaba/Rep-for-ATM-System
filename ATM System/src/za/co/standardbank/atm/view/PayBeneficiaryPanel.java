package za.co.standardbank.atm.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import za.co.standardbank.atm.control.BeneficiaryController;

public class PayBeneficiaryPanel extends JPanel {
	
	JPanel bensPanel;
	JTextField searchBenTextField;
	JButton searchBenButton;
	JButton addBenButton;
	JPanel searchPanel;
	
	public PayBeneficiaryPanel()
	{
		searchBenTextField = new JTextField(30);
		
		searchBenButton = new JButton("Search");
		searchBenButton.addActionListener(new searchBenButtonActionListener());
		
		addBenButton = new JButton("Add Beneficiary");
		
		bensPanel = new JPanel();
		
		bensPanel.setLayout(new BoxLayout(bensPanel, BoxLayout.Y_AXIS));
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(3,3,3));
		searchPanel.setSize(400, 5);
		
		searchPanel.add(searchBenTextField);
		searchPanel.add(searchBenButton);
	
	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(MainFrame.frame.getMainMenuButtonPanel());
		add(searchPanel);
		
		for(String ben: BeneficiaryController.getbeneficiaries(0))
		{
			String[] temp = ben.split(",");
			bensPanel.add(this.BeneficiaryPanel(temp[0], temp[1], temp[2]));
		}
		
		bensPanel.setBackground(Color.red);
		add(bensPanel);
		add(new JPanel());
		add(new JPanel());
		add(addBenButton);
		MainFrame.frame.drawFrame(this);		
	}
	
	private JPanel BeneficiaryPanel(String benName, String benBankName, String benAccountNumber)
	{
		JPanel benPanel = new JPanel();
		benPanel.setLayout(new FlowLayout(3,3,3));
		JLabel benInfoLabel = new JLabel("   "+benName+" "+" "+benBankName+" "+benAccountNumber);
		
		JButton deleteBen = new JButton("Delete");
		JButton editBen = new JButton("Edit");
		JButton Pay = new JButton("Pay");
		
		benPanel.add(benInfoLabel);
		benPanel.add(Pay);
		benPanel.add(editBen);
		benPanel.add(deleteBen);
		
		benPanel.setSize(400, 5);
		
		System.out.println(benName);
		return benPanel;
	}
	
	private void redraw()
	{

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(MainFrame.frame.getMainMenuButtonPanel());
		add(searchPanel);
				
		bensPanel.setBackground(Color.red);
		add(bensPanel);
		add(new JPanel());
		add(new JPanel());
		add(addBenButton);
		MainFrame.frame.drawFrame(this);
	}
	
	private class searchBenButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			bensPanel.removeAll();
			for (String ben :
				BeneficiaryController.getSearchedbeneficiaries(searchBenTextField.getText()))
			{
				String[] temp = ben.split(",");
				bensPanel.add(BeneficiaryPanel(temp[0], temp[1], temp[2]));
				
			}
			removeAll();
			redraw();
			
		}
		
	}
	
}
