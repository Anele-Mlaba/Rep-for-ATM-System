package MainClass;
import java.util.Objects;
import java.util.Optional;

import GUI.MyFrame;


public class Main {
	//public static MyFrame frame;
	public static void main(String [] args) 
	{
		//frame = new MyFrame();
		Optional<String> middleName = Optional.of("cabby");
		System.out.println(middleName.toString()
				);
	}
}
