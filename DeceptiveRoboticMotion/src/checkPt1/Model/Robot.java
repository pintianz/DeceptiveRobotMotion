package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Robot extends Coordinate implements Drawable {
	ArrayList<Coordinate> trajectory;
	Coordinate curWayPoint;
	public Robot(float x, float y) {
        super(x,y);
        trajectory = null;
    }
	
	public void setTrajectory(ArrayList<Coordinate> trajectory){
		this.trajectory = trajectory;
		curWayPoint = trajectory.get(0);
	}
	
	public float getWayPointX(){
		Math.sqrt( Math.pow((coord1.getX()-coord2.getX()),2) + Math.pow((coord1.getY()-coord2.getY()),2))
		this.trajectory = trajectory;
		curWayPoint = trajectory.get(0);
		return 0;
	}
	public float getWayPointY(){
		this.trajectory = trajectory;
		curWayPoint = trajectory.get(0);
		return 0;
	}
	
	private float getCostBetween(Coordinate coord1, Coordinate coord2){
		return (float)(Math.sqrt( Math.pow((coord1.getX()-coord2.getX()),2) + Math.pow((coord1.getY()-coord2.getY()),2)));
	}
	
	@Override
	public void update(JComponent comp) {
		x = getWayPointX;
        y += yDelta;
        if (x < 0) {
            x = 0;
            xDelta *= -1;
        } else if (x + width > comp.getWidth()) {
            x = comp.getWidth() - width;
            xDelta *= -1;
        }
        if (y < 0) {
            y = 0;
            yDelta *= -1;
        } else if (y + height > comp.getHeight()) {
            y = comp.getHeight() - height;
            yDelta *= -1;
        }
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.draw(g);

	}

}
