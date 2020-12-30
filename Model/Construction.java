package Model;
import java.util.ArrayList;

import javafx.scene.paint.*;
public class Construction {
	protected int left,top,right,bottom;
	protected boolean hasWindows,hasRoof,hasDoor;
	protected Color color;
	protected ArrayList<Window> windows;  
	protected Color roofColor;
	// we need to create this for the objects we add
  	
	public Construction() {
		left = 50;
		top = 50;
		right = 250;
		bottom = 300;
		hasWindows = false;
		hasRoof = false;
		hasDoor = false;
		color = Color.GRAY;
		windows = new ArrayList<>(); // this helps us to set the number of the windows 
		roofColor = Color.RED;
	}
	// we need this to get the list of the windows
	public ArrayList<Window> getWindows(){
		return windows;
	}
	
	
	public Color getRoofColor() {
		return roofColor;
	}
	public void setRoofColor(Color roofColor) {
		this.roofColor = roofColor;
	}
	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int getRight() {
		return right;
	}

	public int getBottom() {
		return bottom;
	}

	public boolean isHasWindows() {
		return hasWindows;
	}

	public boolean isHasRoof() {
		return hasRoof;
	}

	public boolean isHasDoor() {
		return hasDoor;
	}

	public Color getColor() {
		return color;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public void setHasWindows(boolean hasWindows) {
		this.hasWindows = hasWindows;
	}

	public void setHasRoof(boolean hasRoof) {
		this.hasRoof = hasRoof;
	}

	public void setHasDoor(boolean hasDoor) {
		this.hasDoor = hasDoor;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
