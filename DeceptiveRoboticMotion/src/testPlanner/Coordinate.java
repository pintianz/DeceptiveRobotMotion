package testPlanner;

public class Coordinate {
	private float x;
	private float y;
	private float cost;
	public Coordinate(float x, float y){
		this.x = x;
		this.y = y;
		cost = -1;
	}
	public float getX(){
		return x;
	}
	public void setX(float x){
		this.x = x;
	}
	public float getY(){
		return y;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getCost(){
		return cost;
	}
	public void setCost(float cost){
		this.cost = cost;
	}
	
	public String toString(){
		return "("+x+" ,"+y+")";
	}
}
