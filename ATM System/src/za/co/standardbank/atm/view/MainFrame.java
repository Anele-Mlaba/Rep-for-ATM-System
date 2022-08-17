package za.co.standardbank.atm.view;

import java.awt.FlowLayout;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import za.co.standardbank.atm.annotations.TableName;
import za.co.standardbank.atm.model.Customer;
import za.co.standardbank.atm.orm.EntityManager;
import za.co.standardbank.atm.orm.EntityManagerFactory;

//this frame contains the whole system
public class MainFrame extends JFrame
{
	//dimensions of the whole frame
	private final int WIDTH_OF_FRAME = 600;
	private final int LENGTH_OF_FRAME = 600;
	
	public static MainFrame frame;
	
	public MainFrame()
	{
		// initializes the frame to these values
		this.setTitle("ATM System");
		this.setSize(WIDTH_OF_FRAME, LENGTH_OF_FRAME);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.add(new LoginPanel());// this is where the program should start, the LoginPanel
		this.setVisible(true);
		
	}
	
	/*
	 *  this method makes sure that when this panel is created, all of it's contents gets painted to the
	 *  JFrame (MyFrame)
	 */
	public  <T extends JPanel> void drawFrame(T c)
	{
		this.getContentPane().removeAll();
		this.repaint();
		this.add(c);
		this.setVisible(true);
	}
	
	
	/*
	 * this method adds the main menu panel and its button to the calling panel
	 */
	public JPanel getMainMenuButtonPanel()
	{
		JButton mainMenuButton = new JButton("MainMenu");
		mainMenuButton.addActionListener((e)->new MainMenuPanel());
		
		JPanel mainMenuButtonPanel = new JPanel();
		mainMenuButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		mainMenuButtonPanel.add(mainMenuButton);
		
		return mainMenuButtonPanel;
	}
	
	public JPanel getAccountsButtonPanel()
	{
		JButton mainMenuButton = new JButton("Accounts");
		mainMenuButton.addActionListener((e)->new AccountsPanel());
		
		JPanel mainMenuButtonPanel = new JPanel();
		mainMenuButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		mainMenuButtonPanel.add(mainMenuButton);
		
		return mainMenuButtonPanel;
	}
	
	public static void main(String [] args) 
	{
		frame = new MainFrame();
		
		 
		
		/*
		String connectionString = "jdbc:mysql://localhost:3306/standardbankatmdb";
		String userName = "root";
		String password = "0633386@NE|_e";
		try 
		{
			Connection conn = DriverManager.getConnection(connectionString, userName, password);
			if (conn.isValid(2))
				System.out.print(true);
			else
				System.out.print(false);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}*/
	}
		
}
