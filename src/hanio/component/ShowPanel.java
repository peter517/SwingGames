package hanio.component;

import hanio.Block;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ShowPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int widthStart;
	private int heightStart;
	private int bottomWidth;
	private int bottomHeight;
	private int difference;
	private int firstMid;
	private int secondMid;
	private int thirdMid;
	
	public final static String first = "first";
	public final static String second = "second";
	public final static String third = "third";
	
	public int firstBolckCount;
	
	public Stack<Block> firstPillar = new Stack<Block>();
	public Stack<Block> secondPillar = new Stack<Block>();
	public Stack<Block> thirdPillar = new Stack<Block>();

	public Map<String,Stack<Block>> map = new HashMap<String,Stack<Block>>();
	
	public void initPaintPanelPara() {
		
		widthStart = (int) (this.getWidth() * 0.1);
		heightStart = (int) (this.getHeight() * 0.8);
		bottomWidth = (int) (this.getWidth() * 0.2);
		bottomHeight = (int) (this.getHeight() * 0.05);
		difference = 8;
		
		map.put(ShowPanel.first, firstPillar);
		map.put(ShowPanel.second, secondPillar);
		map.put(ShowPanel.third, thirdPillar);
		
		firstMid = widthStart + bottomWidth / 2;
		secondMid = 2 * widthStart + bottomWidth + bottomWidth / 2;
		thirdMid = 3 * widthStart + 2 * bottomWidth + bottomWidth / 2;
		
		this.setBorder(new LineBorder(Color.RED));
	}

	public void initFirstPillar() {

		int bolckCount = firstBolckCount;
		while (bolckCount != 0) {

			bolckCount--;
			int radio = firstBolckCount - bolckCount;
			int heightUpEdgeOfBlock = heightStart - bottomHeight * radio;
			int bottomWidthOfBlock = bottomWidth - difference * radio * 2;

			if (heightUpEdgeOfBlock < 0 || bottomWidthOfBlock < 0) {
				break;
			}
			firstPillar.push(new Block(bottomWidthOfBlock, bottomHeight));

		}

	}
	
   public void hanio(int blockCount, String first, String second, String third) throws InterruptedException{
		
		if(blockCount == 1){
			map.get(third).push(map.get(first).pop());
			System.out.println(first + "-->" + third);
			this.repaint();
			TimeUnit.SECONDS.sleep(1);
			
		}else{
			hanio(blockCount - 1, first, third ,second);
			map.get(third).push(map.get(first).pop());
			this.repaint();
			
			TimeUnit.SECONDS.sleep(1);
			
			System.out.println(first + "-->" + third);
			hanio(blockCount - 1, second, first, third);
		}
		
	}
   
   public void refresh(){
	   
	   this.firstPillar.removeAllElements();
	   this.secondPillar.removeAllElements();
	   this.thirdPillar.removeAllElements();
	   
   }

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		initPaintPanelPara();
		
		g.setColor(Color.BLUE);
		g.drawRect(widthStart, heightStart, bottomWidth, bottomHeight);
		g.drawLine(widthStart + bottomWidth / 2, this.getHeight() - heightStart, widthStart + bottomWidth / 2, heightStart);

		g.drawRect(2 * widthStart + bottomWidth, heightStart, bottomWidth, bottomHeight);
		g.drawLine(2 * widthStart + bottomWidth + bottomWidth / 2, this.getHeight() - heightStart, 2 * widthStart + bottomWidth + bottomWidth / 2, heightStart);

		g.drawRect(3 * widthStart + 2 * bottomWidth, heightStart, bottomWidth, bottomHeight);
		g.drawLine(3 * widthStart + 2 * bottomWidth + bottomWidth / 2, this.getHeight() - heightStart, 3 * widthStart + 2 * bottomWidth + bottomWidth / 2, heightStart);

		g.setColor(Color.RED);
		paintFirstPillar(firstPillar, g);
		paintSecondPillar(secondPillar, g);
		paintThirdPillar(thirdPillar, g);
		
	}

	private void paintFirstPillar(Stack<Block> stack, Graphics g) {
		
		Iterator<Block> iterator = stack.iterator();
        int height = heightStart - bottomHeight;
		while (iterator.hasNext()) {
			Block b = iterator.next();
			g.drawRect(firstMid - b.width / 2, height, b.width, b.height);
			height -= bottomHeight;
		}
	}
	
    private void paintSecondPillar(Stack<Block> stack, Graphics g) {
		
    	Iterator<Block> iterator = stack.iterator();
        int height = heightStart - bottomHeight;
        while (iterator.hasNext()) {
			Block b = iterator.next();
			g.drawRect(secondMid - b.width / 2, height, b.width, b.height);
			height -= bottomHeight;
		}
	}
    
   private void paintThirdPillar(Stack<Block> stack, Graphics g) {
		
	   Iterator<Block> iterator = stack.iterator();
        int height = heightStart - bottomHeight;
        while (iterator.hasNext()) {
			Block b = iterator.next();
			g.drawRect(thirdMid - b.width / 2, height, b.width, b.height);
			height -= bottomHeight;
		}
	}
}
