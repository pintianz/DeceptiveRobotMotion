package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Robot extends Coordinate implements Drawable {
	ArrayList<Coordinate> trajectory;
	Coordinate curWayPoint;
	float deltaX;
	float deltaY;
	boolean isMoving;
	public Robot(float x, float y) {
        super(x,y);
        trajectory = null;
        isMoving = true;
    }
	
	public void setTrajectory(ArrayList<Coordinate> trajectory){
		this.trajectory = trajectory;
		for(int i=0; i< trajectory.size(); i++){
			System.out.println(trajectory.get(i).toString());
		}
		curWayPoint = trajectory.remove(0);
		setDeltaX();
		setDeltaY();
	}
	
	private void setDeltaX(){
		float wpDistance = getCostBetween(this, curWayPoint);
		wpDistance = wpDistance *10;
		deltaX = (curWayPoint.getX() - getX())/wpDistance;
		return;
	}
	private void setDeltaY(){
		float wpDistance = getCostBetween(this, curWayPoint);
		wpDistance = wpDistance *10;
		deltaY = (curWayPoint.getY() - getY())/wpDistance;
		return;
	}
	
	private float getCostBetween(Coordinate coord1, Coordinate coord2){
		return (float)(Math.sqrt( Math.pow((coord1.getX()-coord2.getX()),2) + Math.pow((coord1.getY()-coord2.getY()),2)));
	}
	
	@Override
	public void update(JComponent comp) {
		if(isMoving){
			if(getX() == curWayPoint.getX() && getY() == curWayPoint.getY() ){
				if(trajectory.size()>0){
					curWayPoint = trajectory.remove(0);
					setDeltaX();
					setDeltaY();
					
				} else {
					isMoving = false;
				}
			} else {
				if(Math.abs(getX()-curWayPoint.getX())<Math.abs(deltaX)){
					setX(curWayPoint.getX());
					setY(curWayPoint.getY());
				} else {
					setX(getX()+deltaX);
					setY(getY()+deltaY);
				}
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.draw(g);

	}

}
