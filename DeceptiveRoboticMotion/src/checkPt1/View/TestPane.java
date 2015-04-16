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
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable d : drawables) {
            d.draw(g);
        }
    }
}