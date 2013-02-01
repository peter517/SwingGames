package tools;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class JFrameTools {

	public static void setFrameCenter(JFrame frame) {

		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		frame.setLocation((screenWidth - frame.getWidth()) / 2, (screenHeight - frame.getHeight()) / 2);

	}
	
	public static void createFrame(JFrame frame, int width, int height){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
