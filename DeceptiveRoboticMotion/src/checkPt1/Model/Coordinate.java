package checkPt1.Model;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Coordinate implements Drawable {
	private float x;
	private float y;
	private float cost;
	private int width;
    private int height;
    
	public Coordinate(float x, float y){
		this.x = x;
		this.y = y;
		cost = -1;
		width = 10;
        height = 10;
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
	@Override
	public void update(JComponent comp){
		return;
	}
	
	@Override
	public void draw(Graphics g) {
		g.fillRect((int)x, (int)y, width, height);
		
	}
}
