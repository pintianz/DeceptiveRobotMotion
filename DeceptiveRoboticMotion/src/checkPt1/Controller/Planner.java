package checkPt1.Controller;

import java.util.ArrayList;

import checkPt1.Model.Coordinate;

public class Planner implements PlannerObj{
	private Coordinate robotCoord;
	private Coordinate trueGoalCoord;
	private Coordinate falseGoalCoord;
	private float numOfWayPoint;
	private float normalizerVal;
	
	public Planner(Coordinate robotCoord, Coordinate trueGoalCoord, Coordinate falseGoalCoord, float numOfWayPoint, float normalizerVal){
		this.robotCoord = robotCoord;
		this.trueGoalCoord = trueGoalCoord;
		this.falseGoalCoord = falseGoalCoord;
		this.numOfWayPoint = numOfWayPoint;
		this.normalizerVal = normalizerVal;
	}
	
	public ArrayList<Coordinate> generateOptimalPath(){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		float deltaX = (trueGoalCoord.getX()-robotCoord.getX())/numOfWayPoint;
		float deltaY = (trueGoalCoord.getY()-robotCoord.getY())/numOfWayPoint;
		
		float curX = robotCoord.getX();
		float curY = robotCoord.getY();
		
		System.out.println("deltaX: "+deltaX);
		System.out.println("curX: "+curX);
		System.out.println("deltaY: "+deltaY);
		System.out.println("curY: "+curY);
		
		while(Math.abs(curX+deltaX)<Math.abs(trueGoalCoord.getX())){
			curX = curX+deltaX;
			curY = curY+deltaY;
			Coordinate c = new Coordinate(curX, curY);
			c.setCost(getCostBetween(c, robotCoord));
			result.add(c);
		}
		return result;
	}
	
	public ArrayList<Coordinate> generateDeceptivePath(ArrayList<Coordinate> inputPath){
		ArrayList<Coordinate> result = new ArrayList<Coordinate>();
		
		Coordinate oldCoordResult = robotCoord;
		for(Coordinate c: inputPath){
			float deltaX = (-1)*getConditionalP(c)*( (-1)*((falseGoalCoord.getX()-c.getX())/getCostBetween(c,falseGoalCoord)));
			float deltaY = (-1)*getConditionalP(c)*( (-1)*((falseGoalCoord.getY()-c.getY())/getCostBetween(c,falseGoalCoord)));
			if(getCostBetween(c,falseGoalCoord)<=4){
				deltaX = deltaX/2;
				deltaY = deltaY/2;
			}
			if(getCostBetween(c,falseGoalCoord)<=1){
				deltaX = 0;
				deltaY = 0;
			}
			float newX = c.getX() + deltaX;
			float newY = c.getY() + deltaY;
			System.out.println(c.toString());
			System.out.println("XXX "+ ((falseGoalCoord.getX()-c.getX())/getCostBetween(c,falseGoalCoord)));
			System.out.println("YYY "+ ((falseGoalCoord.getY()-c.getY())/getCostBetween(c,falseGoalCoord)));
			System.out.println("CondP "+ getConditionalP(c));
//			
//			System.out.println("new x "+ newX + " new y "+newY);
			
			Coordinate tempC = new Coordinate(newX, newY);
			tempC.setCost(oldCoordResult.getCost() + getCostBetween(oldCoordResult, tempC));
			result.add(tempC);
		}
		return result;
	}
	
	
	private float getConditionalP(Coordinate curCoord){
		float numeratorExp = (-1)*(curCoord.getCost() + getCostBetween(curCoord,falseGoalCoord));
		float numerator = (float)Math.pow(Math.E, numeratorExp);
		float demoninatorExp = (-1)*(getCostBetween(robotCoord,falseGoalCoord));
		float demoninator = (float)Math.pow(Math.E, demoninatorExp);
		
//		System.out.println("numerator "+ numerator + " demoninator "+demoninator);
//		System.out.println("numerator/demoninator "+ (numerator/demoninator));
		
		return (((normalizerVal*(numerator/demoninator))-1)/5)+1;
	}
	
	private float getCostBetween(Coordinate coord1, Coordinate coord2){
		return (float)(Math.sqrt( Math.pow((coord1.getX()-coord2.getX()),2) + Math.pow((coord1.getY()-coord2.getY()),2)));
	}
}
