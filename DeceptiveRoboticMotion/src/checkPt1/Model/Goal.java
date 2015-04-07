package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Goal extends CoreObject {

	ArrayList<CoreObject> occupiedList;
	boolean trueGoal;
	
	public Goal(int x, int y, int width, int height, boolean trueGoal) {
		super(x, y, width, height);
		occupiedList = new ArrayList<CoreObject>();
		this.trueGoal = trueGoal;
		// TODO Auto-generated constructor stub
	}

	public boolean contains(CoreObject co){
		for(CoreObject c: occupiedList){
			if(c == co)return true;
		}
		return false;
	}
	
	@Override
	public void update(JComponent comp) {

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
