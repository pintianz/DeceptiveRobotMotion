package checkPt1.Controller;

import java.util.ArrayList;

import checkPt1.Model.Coordinate;

public class Tester {

	public static void main(String[] args) {
		Coordinate robotCoord = new Coordinate(10,0);
		Coordinate trueGoalCoord = new Coordinate(20,10);
		Coordinate falseGoalCoord = new Coordinate(0,10);
		float numOfWayPoint = 10;
		Planner planner = new Planner(robotCoord, trueGoalCoord, falseGoalCoord, numOfWayPoint, 1f);
		System.out.println("=========OPTIMAL=========");
		ArrayList<Coordinate> resultOptimal = planner.generateOptimalPath();
		System.out.println("\n\n=========Deceptive1=========");
		ArrayList<Coordinate> resultDeceptive = planner.generateDeceptivePath(resultOptimal);
		System.out.println("\n\n=========Deceptive2=========");
		ArrayList<Coordinate> resultDeceptive2 = planner.generateDeceptivePath(resultDeceptive);
		System.out.println("\n\n=========Deceptive3=========");
		ArrayList<Coordinate> resultDeceptive3 = planner.generateDeceptivePath(resultDeceptive2);
		
		System.out.println("\n\n=========DeceptiveLoop=========");
		ArrayList<Coordinate> resultDeceptiveLoop = resultDeceptive3;
		for(int i=0; i< 14; i++){
			resultDeceptiveLoop = planner.generateDeceptivePath(resultDeceptiveLoop);
		}
		
		
		for(int i =0; i<resultOptimal.size();i++){
			System.out.println(resultOptimal.get(i).toString()+", "+resultDeceptive.get(i).toString()+","+resultDeceptive2.get(i).toString()+","+resultDeceptive3.get(i).toString()+",");
		}
		
		System.out.println("\n\n=========DeceptiveLoop**=========");
		
		for(int i =0; i<resultOptimal.size();i++){
			System.out.println(resultDeceptiveLoop.get(i).toString()+",");
		}
	}

}
