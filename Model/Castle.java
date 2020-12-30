package Model;

public class Castle extends Construction {
	private int towerWidth,towerHeight;
	
	public Castle() {
		super();
		super.setLeft(80);
		super.setRight(220);
		super.setTop(80);
		towerWidth = 60;
		towerHeight = 250;
	}

	public int getTowerWidth() {
		return towerWidth;
	}

	public int getTowerHeight() {
		return towerHeight;
	}
}

