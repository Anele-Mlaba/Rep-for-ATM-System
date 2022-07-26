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

public class BeneficiaryPanel extends JPanel {
	
	private JPanel bensPanel;
	private JTextField searchBenTextField;
	private JButton searchBenButton;
	private JButton addBenButton;
	private JPanel searchPanel;
	private JButton clearButton;
	public static String tempAccountNumber, tempBenName, tempBenBankName;
	
	public BeneficiaryPanel()
	{
		searchBenTextField = new JTextField(30);
		
		searchBenButton = new JButton("Search");
		searchBenButton.addActionListener(new searchBenButtonActionListener());
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(new clearButtonActionListener());
		
		addBenButton = new JButton("Add Beneficiary");
		addBenButton.addActionListener(e -> new AddBeneficiaryPanel());
		
		bensPanel = new JPanel();
		
		bensPanel.setLayout(new BoxLayout(bensPanel, BoxLayout.Y_AXIS));
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
		searchPanel.setSize(400, 5);
		
		searchPanel.add(searchBenTextField);
		searchPanel.add(searchBenButton);
		searchPanel.add(clearButton);
	
		JPanel addBenButtonPanel = new JPanel();
		addBenButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));
		addBenButtonPanel.add(addBenButton);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(MainFrame.frame.getMainMenuButtonPanel());
		add(searchPanel);
		
		for(String ben: BeneficiaryController.getBeneficiaries(0))
		{
			String[] temp = ben.split(",");
			bensPanel.add(this.BeneficiarySubPanel(temp[0], temp[1], temp[2]));
		}
		
		add(bensPanel);
		add(new JPanel());
		add(addBenButtonPanel);	
		add(new JPanel());
		MainFrame.frame.drawFrame(this);		
	}
	
	private JPanel BeneficiarySubPanel(String benName, String benBankName, String benAccountNumber)
	{
		JPanel benPanel = new JPanel();
		benPanel.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
		StringBuilder formattedBenInfo = new StringBuilder( "   "+benName+" "+" "+benBankName+" "+benAccountNumber);
		
		JLabel benInfoLabel = new JLabel(formattedBenInfo.toString());
		
		JButton deleteBen = new JButton("Delete");
		JButton editBen = new JButton("Edit");
		
		JButton pay = new JButton("Pay");
		pay.addActionListener
		(	
					e->
					{
						 tempAccountNumber = benAccountNumber;
						 tempBenName = benName;
						 tempBenBankName =benBankName;
						 new PayBeneficiaryPanel();
					}
		);
		
		
		benPanel.add(benInfoLabel);
		benPanel.add(pay);
		benPanel.add(editBen);
		benPanel.add(deleteBen);
		
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
		add(addBenButton);
		add(new JPanel());
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
				bensPanel.add(BeneficiarySubPanel(temp[0], temp[1], temp[2]));				
			}
			removeAll();
			redraw();
			
		}
		
	}
	
	private class clearButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			bensPanel.removeAll();
			for (String ben :
				BeneficiaryController.getSearchedbeneficiaries(""))
			{
				String[] temp = ben.split(",");
				bensPanel.add(BeneficiarySubPanel(temp[0], temp[1], temp[2]));
				
			}
			searchBenTextField.setText("");
			removeAll();
			redraw();
		}
		
	}
	
}
