package hanio.component;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.JFrameTools;

public class HanioFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private ShowPanel paintPanel;
	protected ControllerPanel choosePanle;

	public HanioFrame() {
		
		paintPanel = new ShowPanel();
		choosePanle = new ControllerPanel();
		this.add(paintPanel, BorderLayout.CENTER);
		this.add(choosePanle, BorderLayout.PAGE_END);

	}

	private class ControllerPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private boolean isStart = false;
		private Thread startThread = null;
		private JTextField textField = new JTextField(10);
		private JButton button = new JButton("开始");
		
	    public ControllerPanel() {
			
			this.add(textField, BorderLayout.PAGE_START);
			this.add(button, BorderLayout.SOUTH);
			
			this.button.addActionListener(startListener);
			this.textField.addKeyListener(inputListener);
		}
	
		KeyListener inputListener = new KeyListener() {
			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
					e.setKeyChar((char) 0);
					return;
				}
			}
		};
		
		private ActionListener startListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isStart == false) {
					
					isStart = true;
					paintPanel.refresh();
					
					startThread = new StartThread();
					startThread.start();	
				}
			}
		};
		
		private class StartThread extends Thread {
			public void run() {
				try {
					if (choosePanle.textField.getText().equals("")){
						isStart = false;
						return;
					}
					paintPanel.firstBolckCount = Integer.valueOf(choosePanle.textField.getText());
					paintPanel.initFirstPillar();

					paintPanel.hanio(paintPanel.firstBolckCount, ShowPanel.first, ShowPanel.second, ShowPanel.third);
					
					isStart = false;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}

	public static void main(String[] args) {

		HanioFrame frame = new HanioFrame();

		JFrameTools.createFrame(frame, 800, 450);
		JFrameTools.setFrameCenter(frame);

	}

}
