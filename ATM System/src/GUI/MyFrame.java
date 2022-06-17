package GUI;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import MainClass.Main;


//this frame contains the whole system
public class MyFrame extends JFrame
{
	//dimensions of the whole frame
	private final int WIDTH_OF_FRAME = 400;
	private final int LENGTH_OF_FRAME = 400;
	
	public MyFrame()
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
}
