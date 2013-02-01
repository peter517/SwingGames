package snakemunch.component;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import snakemunch.Direction;
import tools.JFrameTools;

public class SnakeMunchFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ShowPanel showPanel = new ShowPanel();
	public ControllerPanel controllPanel = new ControllerPanel();
	// 确保绘图和按键同步，按一次键，绘一次图
	public boolean isFinshed = true;
	public int drawGap = 100;

	public SnakeMunchFrame() {
		this.add(showPanel, BorderLayout.CENTER);
		this.addKeyListener(inputListener);
		controllPanel.startThread = new StartThread();
		controllPanel.startThread.start();
	}

	/**
	 * 监听键盘按键
	 * 
	 */
	private KeyListener inputListener = new KeyListener() {

		public void keyPressed(KeyEvent e) {
			if (isFinshed) {
				if (e.getKeyCode() == Direction.LEFT) {
					showPanel.turnLeft();
				} else if (e.getKeyCode() == Direction.RIGHT) {
					showPanel.turnRight();
				} else if (e.getKeyCode() == Direction.UP) {
					showPanel.turnUP();
				} else if (e.getKeyCode() == Direction.DOWN) {
					showPanel.turnDown();
				}

				isFinshed = false;
			}
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
		}
	};
	
	/**
	 * 每隔指定时间重绘一次
	 * 
	 */
	private class StartThread extends Thread {
		public void run() {
			while (!showPanel.isGameOver()) {
				showPanel.repaint();
				isFinshed = true;
				try {
					TimeUnit.MILLISECONDS.sleep(drawGap);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class ControllerPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private boolean isStart = false;
		private JButton button = new JButton("开始");
		private Thread startThread = null;

		public ControllerPanel() {
			this.add(button, BorderLayout.SOUTH);
			this.button.addActionListener(startListener);
		}

		private ActionListener startListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isStart == false) {
					isStart = true;
					startThread = new StartThread();
					startThread.start();
				}
			}
		};

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

	}



	public static void main(String[] args) {

		SnakeMunchFrame frame = new SnakeMunchFrame();

		JFrameTools.createFrame(frame, 400, 250);
		JFrameTools.setFrameCenter(frame);

	}
}
