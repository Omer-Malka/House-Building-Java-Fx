package Model;

public class Model {
	private Construction construction;
	
	public Model() {
		construction = new House();
	}
	
	public void updateModel(int kind,boolean hasW, boolean hasR, boolean hasD) {
		if(kind == 1)
			construction = new House();
		else if (kind == 2)
			construction = new Building();
		else
			construction = new Castle();
		
		construction.setHasWindows(hasW);
		construction.setHasRoof(hasR);
		construction.setHasDoor(hasD);
		
		
	}

	public Construction getConstruction() {
		return construction;
	}
}
