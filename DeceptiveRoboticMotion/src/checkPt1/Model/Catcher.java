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
	
	public Catcher(float x, float y, Robot robot, Goal tg, Goal fg, float normalizerVal, double confidenceThreshold, double distanceThreshold) {
		super(x, y);
		this.robot=robot;
		this.goalList = goalList;
		goalPredicted = null;
		this.confidenceThreshold = confidenceThreshold;
		this.distanceThreshold = distanceThreshold;
		rStart = null;
		this.normalizerVal = normalizerVal;
		this.tg = tg;
		this.fg=fg;
	}
	
	public void setRStart(Coordinate robot){
		rStart = new Coordinate(robot.getX(),robot.getY());
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

	@Override
	public void update(JComponent comp) {
		if(!setGoal){
			if(getConditionalP(tg)/(getConditionalP(tg)+getConditionalP(fg)) > confidenceThreshold){
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
				float deltaCost = getCostBetween(this, curWayPoint);
				setX(curWayPoint.getX());
				setY(curWayPoint.getY());
			} else {
				float deltaCost = (float)(Math.sqrt( Math.pow((getX()-(getX()+deltaX)),2) + Math.pow((getY()-(getY()+deltaY)),2)));
				setX(getX()+deltaX);
				setY(getY()+deltaY);
			}
		}
		System.out.println("tg/fg - "+getConditionalP(tg)/(getConditionalP(tg)+getConditionalP(fg)));
		System.out.println("fg/tg - "+getConditionalP(fg)/(getConditionalP(tg)+getConditionalP(fg)));

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
//		System.out.println("rStart"+rStart.toString());
//		System.out.println("+++"+goalCoord.toString());
//		System.out.println("*****"+demoninator);
		
		//return (float) ((float) 1-Math.exp(-1*(numerator/demoninator)));
		return numerator/demoninator;
	}
}
