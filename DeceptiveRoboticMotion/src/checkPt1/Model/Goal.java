package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Goal extends Coordinate implements Drawable {

	ArrayList<CoreObject> occupiedList;
	boolean trueGoal;
	Robot robot;
	boolean yetToReach;
	public Goal(float x, float y, boolean trueGoal, Robot robot) {
		super(x, y);
		occupiedList = new ArrayList<CoreObject>();
		this.trueGoal = trueGoal;
		this.robot = robot;
		yetToReach = true;
		// TODO Auto-generated constructor stub
	}

	public boolean contains(Coordinate co){
		return (co.getX() == getX() && co.getY() == getY());
	}
	
	@Override
	public void update(JComponent comp) {
		if(trueGoal && contains(robot) && yetToReach){
			System.out.println("ROBOT REACHED THE GOAL");
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
