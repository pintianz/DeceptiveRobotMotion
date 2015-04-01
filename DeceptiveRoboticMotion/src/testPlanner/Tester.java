package testPlanner;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		Coordinate robotCoord = new Coordinate(10,0);
		Coordinate trueGoalCoord = new Coordinate(20,10);
		Coordinate falseGoalCoord = new Coordinate(0,10);
		float numOfWayPoint = 20;
		Planner planner = new Planner(robotCoord, trueGoalCoord, falseGoalCoord, numOfWayPoint, 4f);
		ArrayList<Coordinate> resultOptimal = planner.generateOptimalPath();
		ArrayList<Coordinate> resultDeceptive = planner.generateDeceptivePath(resultOptimal);
		ArrayList<Coordinate> resultDeceptive2 = planner.generateDeceptivePath(resultDeceptive);
		ArrayList<Coordinate> resultDeceptive3 = planner.generateDeceptivePath(resultDeceptive2);
		for(int i =0; i<resultOptimal.size();i++){
			System.out.println(resultOptimal.get(i).toString()+", "+resultDeceptive.get(i).toString()+","+resultDeceptive2.get(i).toString()+","+resultDeceptive3.get(i).toString()+",");
		}
	}

}
