package checkPt1.Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
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
	JFrame frame;
	
    public twoGoalController(float catcherConfidenceThreshold, float catcherDistanceThreshold,PrintWriter writer) {
    	drawables= new ArrayList<Drawable>();
    	robot = new Robot(10,0);
    	g1 = new Goal(20,10, true, robot, catcherConfidenceThreshold, catcherDistanceThreshold, writer);
    	g2 = new Goal(0,10, false, robot, catcherConfidenceThreshold, catcherDistanceThreshold, writer);
    	ArrayList<Goal> goalList = new ArrayList<Goal>();
    	goalList.add(g1);
    	goalList.add(g2);
    	catcher = new Catcher(10,10, robot, g1,g2, normalizerVal, catcherConfidenceThreshold, catcherDistanceThreshold,2);
    	planner = new Planner(robot, g1, g2, numOfWayPoint, normalizerVal);
    	g1.setCatcher(catcher);
    	g2.setCatcher(catcher);
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
		
		float robotTotalCost = getRTotalCost(resultDeceptiveLoop);
		catcher.setRTotalCost(robotTotalCost);
		robot.setTrajectory(resultDeceptiveLoop);
		
    	
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                frame = new JFrame(testType + " - Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(tp);
                frame.pack();
                frame.setLocationRelativeTo(null);
                //frame.setVisible(true);
            }
        });
    	Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (Drawable d : drawables) {
                    d.update(tp);
                }
                tp.repaint();
                if(!g1.yetToReach){
                	//frame.setVisible(false); //you can't see me!
                	frame.dispose(); //Destroy the JFrame object
                	//System.exit(0);
                }
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
    
    public float getRTotalCost(ArrayList<Coordinate> resultDeceptiveLoop){
		Coordinate cd1 = robot;
		Coordinate cd2;
		float robotTotalCost = 0;
		for(int i = 1; i< resultDeceptiveLoop.size(); i++){
			cd2 = resultDeceptiveLoop.get(i);
			robotTotalCost = robotTotalCost + Coordinate.getCostBetween(cd1, cd2);
			cd1 = cd2;
		}
		return robotTotalCost;
	}
}