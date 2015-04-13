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
	private float scoreThreshold;
	private float speed;
	
	public Catcher(float x, float y, Robot robot, ArrayList<Goal> goalList, float confidenceThreshold, float scoreThreshold, float speed) {
		super(x, y);
		this.robot=robot;
		this.goalList = goalList;
		goalPredicted = null;
		this.confidenceThreshold = confidenceThreshold;
		this.scoreThreshold = scoreThreshold;
		this.speed = speed;
	}

	@Override
	public void update(JComponent comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		super.draw(g);

	}
}
