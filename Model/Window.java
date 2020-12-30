package Model;

import javafx.scene.paint.*;

public class Window {

	
	// in the homework this will be for example clean or dirty
	public static final Color COLOR1 = Color.DARKBLUE;
	public static final Color COLOR2 = Color.YELLOW;

	private double left, top, width, height;
	private Color color;

	public Window(double l, double t, double w, double h) { // why we wont need color in the constructor?
		left = l;
		top = t;
		width = w;
		height = h;
		color = COLOR1;
	}
	// we dont need setters here, we gonna create a method which will change the color of the object
	// but we need getters for everything else(except color1 and color2
	// the rest we gonna need to "draw" the window and set it's color.
	
	
	
	// change color
	public void changeColor() {
		if(color == COLOR1)
			color = COLOR2;
		else
			color = COLOR1;
		
	}



	public double getLeft() {
		return left;
	}



	public double getTop() {
		return top;
	}



	public double getWidth() {
		return width;
	}



	public double getHeight() {
		return height;
	}



	public Color getColor() {
		return color;
	}
	
	
	
	
}

