package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Goal extends Coordinate implements Drawable {

	ArrayList<CoreObject> occupiedList;
	boolean trueGoal;
	Robot robot;
	public boolean yetToReach;
	Catcher catcher;
	float catcherConfidenceThreshold;
	float catcherDistanceThreshold;
	PrintWriter writer;
	public Goal(float x, float y, boolean trueGoal, Robot robot, float catcherConfidenceThreshold, float catcherDistanceThreshold,PrintWriter writer) {
		super(x, y);
		occupiedList = new ArrayList<CoreObject>();
		this.trueGoal = trueGoal;
		this.robot = robot;
		yetToReach = true;
		this.catcher = null;
		this.catcherConfidenceThreshold = catcherConfidenceThreshold;
		this.catcherDistanceThreshold = catcherDistanceThreshold;
		this.writer = writer;
		// TODO Auto-generated constructor stub
	}

	public boolean contains(Coordinate co){
		return (co.getX() == getX() && co.getY() == getY());
	}
	public void setCatcher(Catcher catcher){
		this.catcher = catcher;
	}
	
	@Override
	public void update(JComponent comp) {
		if(trueGoal && contains(catcher) && yetToReach){
			writer.println(catcherConfidenceThreshold +", "+catcherDistanceThreshold+", "+"CATCHER");
			System.out.println(catcherConfidenceThreshold +", "+catcherDistanceThreshold+", "+"CATCHER");
			yetToReach = false;
		}
		if(trueGoal && contains(robot) && yetToReach){
			writer.println(catcherConfidenceThreshold +", "+catcherDistanceThreshold+", "+"ROBOT");
			System.out.println(catcherConfidenceThreshold +", "+catcherDistanceThreshold+", "+"ROBOT");
			yetToReach = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		if(trueGoal){
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.YELLOW);
		}
		super.draw(g);

	}

}
