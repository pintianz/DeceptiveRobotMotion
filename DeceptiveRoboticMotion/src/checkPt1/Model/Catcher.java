package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Catcher extends Coordinate implements Drawable {

	private Robot robot;
	private ArrayList<Goal> goalList;
	private Goal goalPredicted;
	private double confidenceThreshold;
	private double distanceThreshold;
	private Coordinate rStart;
	private float normalizerVal;
	private Goal tg;
	private Goal fg;
	private boolean setGoal;
	private Coordinate targetGoal;
	private Coordinate curWayPoint;
	private float deltaY;
	private float deltaX;
	private float robotTotalCost;
	float speed;
	
	public Catcher(float x, float y, Robot robot, Goal tg, Goal fg, float normalizerVal, double confidenceThreshold, double distanceThreshold,float speed) {
		super(x, y);
		this.robot=robot;
		goalPredicted = null;
		this.confidenceThreshold = confidenceThreshold;
		this.distanceThreshold = distanceThreshold;
		rStart = null;
		this.normalizerVal = normalizerVal;
		this.tg = tg;
		this.fg=fg;
		robotTotalCost = 0;
		this.speed = speed;
	}
	public void setRTotalCost(float robotTotalCost){
		this.robotTotalCost = robotTotalCost;
	}
	public void setRStart(Coordinate robot){
		rStart = new Coordinate(robot.getX(),robot.getY());
	}
	
	private void setDeltaX(){
		float wpDistance = getCostBetween(this, curWayPoint);
		wpDistance = wpDistance *10;
		deltaX = ((curWayPoint.getX() - getX())/wpDistance)*speed;
		return;
	}
	private void setDeltaY(){
		float wpDistance = getCostBetween(this, curWayPoint);
		wpDistance = wpDistance *10;
		deltaY = ((curWayPoint.getY() - getY())/wpDistance)*speed;
		return;
	}

	@Override
	public void update(JComponent comp) {
		if(!setGoal){
			if(robot.costSoFar/robotTotalCost>distanceThreshold){
				if(getConditionalP(tg)/(getConditionalP(tg)+getConditionalP(fg)) > 0.5){
					setGoal = true;
					curWayPoint = tg;
					setDeltaX();
					setDeltaY();
				} else {
					setGoal = true;
					curWayPoint = fg;
					setDeltaX();
					setDeltaY();
				}
			} 
			else if(getConditionalP(tg)/(getConditionalP(tg)+getConditionalP(fg)) > confidenceThreshold){
				setGoal = true;
				curWayPoint = tg;
				setDeltaX();
				setDeltaY();
			} else if (getConditionalP(fg)/(getConditionalP(tg)+getConditionalP(fg)) > confidenceThreshold) {
				setGoal = true;
				curWayPoint = fg;
				setDeltaX();
				setDeltaY();
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

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		super.draw(g);

	}
	
	private double getConditionalP(Coordinate goalCoord){
		
		float numeratorExp = (-1)*((robot.costSoFar)/10 + getCostBetween(robot,goalCoord)/10);
		float numerator = (float)Math.pow(Math.E, numeratorExp);
		float demoninatorExp = (-1)*(getCostBetween(rStart,goalCoord)/10);
		float demoninator = (float)Math.pow(Math.E, demoninatorExp);
		return numerator/demoninator;
	}
}
