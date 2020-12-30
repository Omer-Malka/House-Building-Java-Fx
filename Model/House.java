package Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class House extends Construction {
	
	public House() {
		super();
		super.setColor(Color.SANDYBROWN);
		//setup windows
		
		// local vars
		double width = 40, height = 40, gap = 40;
		
		double top = getTop()+gap;
		
		for(int i = 0; i<2;i++) // 2 floors in house 
		{
			double left = getLeft()+gap;
		
			for(int j = 0; j<2;j++) // 2 columms of windows 
			{
				Window tempW = new Window(left,top,width,height); // because window holds the same properties as rectangle 
				// we dont need to set the color becasue we aquaire it from the window class
				// default window color is dark blue 
				
				windows.add(tempW);
				
				left+=(width+gap);
			}
			top+=(height+gap);
		}
		
	}
	
}
