package checkPt1.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import checkPt1.Model.Coordinate;
import checkPt1.Model.Catcher;
import checkPt1.Model.Drawable;
import checkPt1.Model.Goal;
import checkPt1.Model.Robot;
import checkPt1.View.TestPane;

public class twoGoalController {
	
	public static final float numOfWayPoint = 10;
	public static final float normalizerVal = 1;
	
	
	private List<Drawable> drawables;
	Goal g1;
	Goal g2;
	Robot robot;
	Catcher catcher;
	TestPane tp;
	PlannerObj planner;
	
    public twoGoalController() {
    	drawables= new ArrayList<Drawable>();
    	robot = new Robot(10,0);
    	g1 = new Goal(20,10, true, robot);
    	g2 = new Goal(0,10, false, robot);
    	ArrayList<Goal> goalList = new ArrayList<Goal>();
    	goalList.add(g1);
    	goalList.add(g2);
    	catcher = new Catcher(10,10, robot, g1,g2, normalizerVal, 0, 0);
    	planner = new Planner(robot, g1, g2, numOfWayPoint, normalizerVal);
    	
    	drawables.add(g1);
    	drawables.add(g2);
    	drawables.add(robot);
    	drawables.add(catcher);
    	
    	tp = new TestPane(drawables);
    }
    
    public void runTest(final String testType){
    	
    	ArrayList<Coordinate> optimalPath = planner.generateOptimalPath();
    	ArrayList<Coordinate> resultDeceptiveLoop = optimalPath;
		for(int i=0; i< 17; i++){
			resultDeceptiveLoop = planner.generateDeceptivePath(resultDeceptiveLoop);
		}
		for(Coordinate c: resultDeceptiveLoop){
			scaleVisual(c);
		}
		scaleVisual(robot);
		scaleVisual(g1);
		scaleVisual(g2);
		scaleVisual(catcher);
		catcher.setRStart(robot);
		resultDeceptiveLoop.add(g1);
		robot.setTrajectory(resultDeceptiveLoop);
		
    	
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame(testType + " - Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(tp);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    	Timer timer = new Timer(10, new ActionListener() {
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
    private void scaleVisual(Coordinate c){
    	c.setX(c.getX()*10);
    	c.setY(c.getY()*10);
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