package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Catcher extends Coordinate implements Drawable {

	private Robot robot;
	private ArrayList<Goal> goalList;
	private Goal goalPredicted;
	private float confidenceThreshold;
	private float distanceThreshold;
	private Coordinate rStart;
	private float normalizerVal;
	private Goal tg;
	private Goal fg;
	
	public Catcher(float x, float y, Robot robot, Goal tg, Goal fg, float normalizerVal, float confidenceThreshold, float distanceThreshold) {
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

	@Override
	public void update(JComponent comp) {
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
