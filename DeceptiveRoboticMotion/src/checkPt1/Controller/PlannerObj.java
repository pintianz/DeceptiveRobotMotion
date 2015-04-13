package checkPt1.Controller;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

import checkPt1.Model.Coordinate;


public interface PlannerObj {

	public ArrayList<Coordinate> generateOptimalPath();

	public ArrayList<Coordinate> generateDeceptivePath(ArrayList<Coordinate> inputPath);

}