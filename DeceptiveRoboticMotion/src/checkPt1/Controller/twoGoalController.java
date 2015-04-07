package checkPt1.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import checkPt1.Model.Catcher;
import checkPt1.Model.Drawable;
import checkPt1.Model.Goal;
import checkPt1.Model.Robot;
import checkPt1.View.TestPane;

public class twoGoalController {
	
	private List<Drawable> drawables;
	Goal g1;
	Goal g2;
	Goal trueGoal;
	Robot robot;
	Catcher catcher;
	TestPane tp;
	
    public twoGoalController() {
    	private List<Drawable> drawables= null;
    	Goal g1 = new Goal();
    	Goal g2 = new Goal();
    	Goal trueGoal = pickTrueGoal(g1, g2);
    	Robot robot = new Robot();
    	Catcher catcher = new Catcher();
    	drawables.add(g1);
    	drawables.add(g2);
    	drawables.add(trueGoal);
    	drawables.add(robot);
    	drawables.add(catcher);
    	
    	tp = new TestPane(drawables);
    }
    
    public void runTest(){
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(tp);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    	Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (Drawable d : drawables) {
                    d.update(tp);
                }
                tp.repaint();
            }
        });
        timer.start();
    }
    

    private Goal pickTrueGoal(Goal g1, Goal g2){
    	int num = randomWithRange(1,2);
    	if(num==1){
    		return g1;
    	} else {
    		return g2;
    	}
    }
    private int randomWithRange(int min, int max){
       int range = (max - min) + 1;     
       return (int)(Math.random() * range) + min;
    }
}