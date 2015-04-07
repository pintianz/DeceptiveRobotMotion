package checkPt1.Model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public abstract class CoreObject implements Drawable {

    private int x;
    private int y;
    private int width;
    private int height;

    public CoreObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    abstract public void update(JComponent comp);

    @Override
    public void draw(Graphics g){
    	g.fillRect(x, y, width, height);
    }
    
    public boolean overlapp(CoreObject co){
    	return (x==co.x && y==co.y);
    }

}