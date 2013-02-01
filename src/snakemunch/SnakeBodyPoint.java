package snakemunch;

import java.awt.Point;

/**
 * 蛇身上的一个点
 * 
 */
public class SnakeBodyPoint extends Point {

	private static final long serialVersionUID = 1L;
	
	//蛇身点的方向
	private int direction;
	
	public SnakeBodyPoint(int x, int y){
		super(x,y);
	}
	
	public SnakeBodyPoint(int x, int y, int direction){
		super(x,y);
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
}
