package checkPt1.View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import checkPt1.Model.Catcher;
import checkPt1.Model.Drawable;
import checkPt1.Model.Goal;
import checkPt1.Model.Robot;


public class TestPane extends JPanel {

    private List<Drawable> drawables;
	
    public TestPane(List<Drawable> drawables) {
    	this.drawables = drawables;
//    	Goal g1 = new Goal();
//    	Goal g2 = new Goal();
//    	Goal trueGoal = pickTrueGoal(g1, g2);
//    	Robot robot = new Robot();
//    	Catcher catcher = new Catcher();
    	
    	
    	
        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (Drawable d : drawables) {
                    d.update(TestPane.this);
                }
                repaint();
            }
        });
        timer.start();
    }
    

//    private Goal pickTrueGoal(Goal g1, Goal g2){
//    	int num = randomWithRange(1,2);
//    	if(num==1){
//    		return g1;
//    	} else {
//    		return g2;
//    	}
//    }
//    private int randomWithRange(int min, int max){
//       int range = (max - min) + 1;     
//       return (int)(Math.random() * range) + min;
//    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable d : drawables) {
            d.draw(g);
        }
    }
}