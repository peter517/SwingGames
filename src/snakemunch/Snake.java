package snakemunch;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {

	// 蛇前进方向
	private int snakeDirection = Direction.RIGHT;

	// 组成蛇的所有点
	private List<SnakeBodyPoint> snakeBodyPointList = new ArrayList<SnakeBodyPoint>();
	// 所有拐点
	private List<TurningPoint> turningPointList = new ArrayList<TurningPoint>();

	public List<SnakeBodyPoint> getSnakeBodyPointList() {
		return snakeBodyPointList;
	}

	public int getSnakeDirection() {
		return snakeDirection;
	}

	public void setSnakeDirection(int snakeDirection) {
		this.snakeDirection = snakeDirection;
	}

	public List<TurningPoint> getTurningPointList() {
		return turningPointList;
	}

	public SnakeBodyPoint getSnakeHeadPoint() {
		return getSnakeBodyPointList().get(0);
	}

	public SnakeBodyPoint getSnakeTailPoint() {
		return this.getSnakeBodyPointList().get(this.getSnakeBodyPointList().size() - 1);
	}

	/**
	 * 当按键是向上时
	 * 
	 */
	public void turnUP() {
		if (getSnakeDirection() == Direction.LEFT || getSnakeDirection() == Direction.RIGHT) {
			SnakeBodyPoint point = getSnakeHeadPoint();
			getTurningPointList().add(new TurningPoint(point.x, point.y, Direction.UP, getSnakeBodyPointList().size()));
		}
		setSnakeDirection(Direction.UP);
	}

	/**
	 *  当按键是向下时
	 * 
	 */
	public void turnDown() {

		if (getSnakeDirection() == Direction.LEFT || getSnakeDirection() == Direction.RIGHT) {
			SnakeBodyPoint point = getSnakeHeadPoint();
			getTurningPointList().add(new TurningPoint(point.x, point.y, Direction.DOWN, getSnakeBodyPointList().size()));
		}
		setSnakeDirection(Direction.DOWN);
	}

	/**
	 *  当按键是向左时
	 * 
	 */
	public void turnLeft() {
		if (getSnakeDirection() == Direction.UP || getSnakeDirection() == Direction.DOWN) {
			SnakeBodyPoint point = getSnakeHeadPoint();
			getTurningPointList().add(new TurningPoint(point.x, point.y, Direction.LEFT, getSnakeBodyPointList().size()));
		}
		setSnakeDirection(Direction.LEFT);
	}

	/**
	 *  当按键是向右时
	 * 
	 */
	public void turnRight() {
		if (getSnakeDirection() == Direction.UP || getSnakeDirection() == Direction.DOWN) {
			SnakeBodyPoint point = getSnakeHeadPoint();
			getTurningPointList().add(new TurningPoint(point.x, point.y, Direction.RIGHT, getSnakeBodyPointList().size()));
		}
		setSnakeDirection(Direction.RIGHT);
	}

	/**
	 * 确定蛇身每个点的移动方向
	 * 
	 */
	public void setDirectionOfPoint() {

		for (SnakeBodyPoint snakeBodyPoint : getSnakeBodyPointList()) {
			for (TurningPoint turningPoint : getTurningPointList()) {

				if (turningPoint.getValidTimes() > 0 && snakeBodyPoint.equals(turningPoint)) {

						snakeBodyPoint.setDirection(turningPoint.getDirection());
						turningPoint.reduceeValidTimes();
						// if (getTurningPointList().get(i).validCount == 0) {
						// getTurningPointList().remove(i);
						// }
					}
				}
			}
	}

	/**
	 * 判断蛇是否迟到了豆子
	 * 
	 */
	public boolean isEatTheBean(Point beanPoint) {

		SnakeBodyPoint firstPoint = getSnakeHeadPoint();
		boolean isCreateNewBean = false;
		if (firstPoint.equals(beanPoint)) {
			isCreateNewBean = true;
			// addMovingPoint(moveGap);
			updateTuringPointList();
		}

		return isCreateNewBean;
	}

	/**
	 * 更新拐点
	 * 
	 */
	private void updateTuringPointList() {

		for (SnakeBodyPoint snakeBodyPoint : getSnakeBodyPointList()) {
			for (TurningPoint turningPoint : getTurningPointList()) {
				// 从尾部开始遍历蛇，找到第一个有效拐点
				if (turningPoint.getValidTimes() != 0 && snakeBodyPoint.equals(turningPoint)) {
					turningPoint.addValidTimes();
				}
			}
		}
	}

	/**
	 * 给蛇尾增加一个点
	 * 
	 */
	public void addSnakeBodyPointToTail(int moveGap) {

		SnakeBodyPoint lastMoivingPoint = getSnakeTailPoint();

		switch (lastMoivingPoint.getDirection()) {
		case Direction.RIGHT:
			getSnakeBodyPointList().add(new SnakeBodyPoint(lastMoivingPoint.x - moveGap, lastMoivingPoint.y, lastMoivingPoint.getDirection()));
			break;
		case Direction.UP:
			getSnakeBodyPointList().add(new SnakeBodyPoint(lastMoivingPoint.x, lastMoivingPoint.y + moveGap, lastMoivingPoint.getDirection()));
			break;
		case Direction.DOWN:
			getSnakeBodyPointList().add(new SnakeBodyPoint(lastMoivingPoint.x, lastMoivingPoint.y - moveGap, lastMoivingPoint.getDirection()));
			break;
		case Direction.LEFT:
			getSnakeBodyPointList().add(new SnakeBodyPoint(lastMoivingPoint.x + moveGap, lastMoivingPoint.y, lastMoivingPoint.getDirection()));
			break;
		}

	}

}
