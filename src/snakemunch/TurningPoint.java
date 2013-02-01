package snakemunch;

import java.awt.Point;

/**
 * 移动时候的一个拐点
 * 
 */
public class TurningPoint extends Point {
	
	private static final long serialVersionUID = 1L;
	//拐点的方向
	private int direction;
	//拐点的有效次数
	private int validTimes;
	
	public TurningPoint(int x, int y){
		super(x,y);
	}
	
	public TurningPoint(int x, int y, int direction, int validCount){
		super(x,y);
		this.direction = direction;
		this.validTimes = validCount;
	}

	public int getDirection() {
		return direction;
	}

	public int getValidTimes() {
		return validTimes;
	}
	
	public void addValidTimes(){
		validTimes++;
	}
	
	public void reduceeValidTimes(){
		validTimes--;
	}
	
	
}
