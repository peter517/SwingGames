package snakemunch.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import snakemunch.Direction;
import snakemunch.Snake;
import snakemunch.SnakeBodyPoint;
import snakemunch.TurningPoint;

public class ShowPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Random ramdom = new Random();
	private boolean init = false;
	private boolean isGameOver = false;

	private Point beanPoint = new Point(-1, -1);
	private Snake snake = new Snake();

	/**
	 * 每次移动步长
	 */
	private int moveGap = 5;

	public boolean isGameOver() {
		return isGameOver;
	}

	public List<TurningPoint> getTurningPointList() {
		return snake.getTurningPointList();
	}

	public List<SnakeBodyPoint> getMovingPointList() {
		return snake.getSnakeBodyPointList();
	}

	public ShowPanel() {
	}

	public void setFirstMovingPointLocation() {
		Point point = createRandomPoint();
		snake.getSnakeBodyPointList().add(new SnakeBodyPoint(point.x, point.y, Direction.RIGHT));
	}

	public int getSnakeDirection() {
		return snake.getSnakeDirection();
	}

	public void setSnakeDirection(int snake) {
		this.snake.setSnakeDirection(snake);
	}

	/**
	 * 进行绘制
	 * 
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.BLUE);
		
		if (!init) {
			setFirstMovingPointLocation();
			drawNewBeanPoint(g);
			init = true;
		}

		paintSnake(g);
		
		if (isGameOver(g)) {
			String str = "GAME OVER!!!";
			g.drawString(str, getWidth() / 2, getHeight() / 2);
		}

	}
	
	/**
	 * 重绘所有点
	 * 
	 */
	private void paintSnake(Graphics g) {

		snake.setDirectionOfPoint();
		
		movingSnake(g);

		if (snake.isEatTheBean(beanPoint)) {
			snake.addSnakeBodyPointToTail(moveGap);
			drawNewBeanPoint(g);
		} else {
			drawBeanPoint(g);
		}

	}

	public void turnUP() {
		snake.turnUP();
	}

	public void turnDown() {
		snake.turnDown();
	}

	public void turnLeft() {
		snake.turnLeft();
	}

	public void turnRight() {
		snake.turnRight();
	}

	private void moveLeft(Graphics g, SnakeBodyPoint point) {
		point.x -= moveGap;
		g.drawString("o", point.x, point.y);

	}

	private void moveRight(Graphics g, SnakeBodyPoint point) {
		point.x += moveGap;
		g.drawString("o", point.x, point.y);

	}

	private void moveUp(Graphics g, SnakeBodyPoint point) {
		point.y -= moveGap;
		g.drawString("o", point.x, point.y);

	}

	private void moveDown(Graphics g, SnakeBodyPoint point) {
		point.y += moveGap;
		g.drawString("o", point.x, point.y);

	}

	public SnakeBodyPoint getSnakeHeadPoint() {
		return snake.getSnakeHeadPoint();
	}


	/**
	 * 游戏是否结束
	 * 
	 */
	private boolean isGameOver(Graphics g) {

		for (SnakeBodyPoint movingPoint : snake.getSnakeBodyPointList()) {

			boolean isWidthOut = movingPoint.x > getWidth() || movingPoint.x < 0;
			boolean isHeightOut = movingPoint.y > getHeight() || movingPoint.y < 0;

			if (isWidthOut || isHeightOut) {
				return true;
			}

			if (snake.getSnakeBodyPointList().indexOf(movingPoint) != snake.getSnakeBodyPointList().lastIndexOf(movingPoint)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 画出所有有效的拐点
	 * 
	 */
	private void paintAllValidTurninPoint(Graphics g) {
		g.setColor(Color.GREEN);
		for (TurningPoint turningPoint : snake.getTurningPointList()) {
			if (turningPoint.getValidTimes() != 0) {
				g.drawString("o", turningPoint.x, turningPoint.y);
			}
		}
	}

	/**
	 * 画豆子
	 * 
	 */
	private void drawBeanPoint(Graphics g) {
		g.setColor(Color.RED);
		g.drawString("o", beanPoint.x, beanPoint.y);
	}

	/**
	 * 画新豆子
	 * 
	 */
	private void drawNewBeanPoint(Graphics g) {
		beanPoint = createRandomPoint();
		g.setColor(Color.RED);
		g.drawString("o", beanPoint.x, beanPoint.y);
	}

	/**
	 * 移动蛇身
	 * 
	 */
	private void movingSnake(Graphics g) {
		
		for (SnakeBodyPoint movingPoint : snake.getSnakeBodyPointList()) {
			switch (movingPoint.getDirection()) {
			case Direction.RIGHT:
				moveRight(g, movingPoint);
				break;
			case Direction.UP:
				moveUp(g, movingPoint);
				break;
			case Direction.DOWN:
				moveDown(g, movingPoint);
				break;
			case Direction.LEFT:
				moveLeft(g, movingPoint);
				break;
			}
		}
		
		paintAllValidTurninPoint(g);
	}

	/**
	 * 生成随机点
	 * 
	 */
	private Point createRandomPoint() {

		Point point = new Point();
		boolean isValid = false;
		
		while (!isValid) {

			point.x = ramdom.nextInt(getWidth()) % (getWidth() - moveGap) + moveGap;
			point.y = ramdom.nextInt(getHeight()) % (getHeight() - moveGap) + moveGap;

			point.x = point.x - point.x % moveGap;
			point.y = point.y - point.y % moveGap;

			isValid = true;

			for (SnakeBodyPoint movingPoint : snake.getSnakeBodyPointList()) {
				if (movingPoint.equals(point)) {
					isValid = false;
				}
			}

			System.out.println(point.x + " " + point.y);
		}

		return point;
	}
}
