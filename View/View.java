package View;
import javafx.scene.input.*; 
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

import java.util.ArrayList;

import Model.Castle;
import Model.Construction;
import javafx.beans.value.ChangeListener;
import javafx.event.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.shape.*;

public class View {
	private RadioButton rbHouse, rbBuilding, rbCastle;
	private CheckBox cbWindows, cbRoof, cbDoor;
	private Group root;
	private ToggleGroup tgr;
	private ArrayList<Rectangle> windows;
	private ComboBox comboB;

	public View(Stage stage) {
		BorderPane bp = new BorderPane();
		root = new Group();

		windows = new ArrayList<>();
		// left side of stage
		VBox vb = new VBox();
		tgr = new ToggleGroup();

		rbHouse = new RadioButton("House");
		rbHouse.setToggleGroup(tgr);
		rbBuilding = new RadioButton("Building");
		rbBuilding.setToggleGroup(tgr);
		rbCastle = new RadioButton("Castle");
		rbCastle.setToggleGroup(tgr);

		vb.getChildren().addAll(rbHouse, rbBuilding, rbCastle);
		vb.setMargin(rbHouse, new Insets(20, 20, 20, 20)); // right,bottom,left,top
		vb.setMargin(rbBuilding, new Insets(20, 20, 20, 20));
		vb.setMargin(rbCastle, new Insets(20, 20, 20, 20));

		bp.setLeft(vb);

		VBox vb1 = new VBox();
		cbWindows = new CheckBox("Windows");
		cbRoof = new CheckBox("Roof");
		cbDoor = new CheckBox("Door");
		vb1.getChildren().addAll(cbWindows, cbRoof, cbDoor);
		vb1.setMargin(cbWindows, new Insets(5, 20, 5, 20)); // right,bottom,left,top
		vb1.setMargin(cbRoof, new Insets(5, 20, 5, 20));
		vb1.setMargin(cbDoor, new Insets(5, 20, 5, 20));

		
		// create new ComboBox
		comboB= new ComboBox();
		comboB.setPromptText("Roof Colors");
		comboB.getItems().addAll("Red", "Green", "Gray");
	
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(comboB);
		bp.setBottom(hb);
		
		
		
		
		bp.setRight(vb1);
		// bp.setTop(l3);
		// bp.setBottom(l4);
		bp.setCenter(root);
		
	
		Scene scene = new Scene(bp, 500, 500);

		stage.setScene(scene);
		stage.show();
	}

	public void updateShape(Model.Construction construction) {
		// 1. draw mutual object
		drawMainRectangle(construction);
		// 2. add roof if it is checked
		if (construction.isHasRoof())
			drawRoof(construction);
		// 3. add windows if checked
		if (construction.isHasWindows())
			drawWindows(construction);
		// 4. add door if checked
		if (construction.isHasDoor())
			drawDoor(construction);
	}

	public void drawMainRectangle(Model.Construction construction) {
		Rectangle rct = new Rectangle(construction.getLeft(), construction.getTop(),
				construction.getRight() - construction.getLeft(), construction.getBottom() - construction.getTop());
		rct.setFill(construction.getColor());

		root.getChildren().clear();
		root.getChildren().add(rct);

		if (construction instanceof Model.Castle) // add towers
		{
			Rectangle rct1 = new Rectangle(construction.getLeft() - ((Model.Castle) construction).getTowerWidth(),
					construction.getBottom() - ((Model.Castle) construction).getTowerHeight(),
					((Model.Castle) construction).getTowerWidth(), ((Model.Castle) construction).getTowerHeight());
			rct1.setFill(Color.DARKGRAY);

			Rectangle rct2 = new Rectangle(construction.getRight(),
					construction.getBottom() - ((Model.Castle) construction).getTowerHeight(),
					((Model.Castle) construction).getTowerWidth(), ((Model.Castle) construction).getTowerHeight());
			rct2.setFill(Color.DARKGRAY);

			root.getChildren().addAll(rct1, rct2);

		}

	}

	public void drawWindows(Model.Construction construction) {
		if(construction instanceof Model.House) {
		for(int i=0;i<construction.getWindows().size();i++){
			// show each window as rectangle 
			
			//root.getChildren().add(tempR);
			// we drew the windows but now we need to connect the windows to the house.
			// this construction was added to the controller
			root.getChildren().add(windows.get(i));
		}
		}
		else if(construction instanceof Model.Building) {
			double width = 20, height = 20, gap = 10;
			double top = construction.getTop()+gap;
			
			for(int i = 0; top<construction.getBottom()-2*(height+gap);i++)
			{
				double left = construction.getLeft()+gap;
			
				for(int j = 0;left<construction.getRight()-(gap) ;j++)
				{
					Rectangle rct = new Rectangle(left,top,width,height);
					rct.setFill(Color.NAVY);
					root.getChildren().add(rct);
					left+=(width+gap);
				}
				top+=(height+gap);
			}
		}

	}

	public void drawDoor(Model.Construction construction) {
		if (construction instanceof Model.House) {
			double width = 40;
			double height = 60;
			Rectangle rct = new Rectangle(construction.getRight() - 2 * width, construction.getBottom() - height, width,
					height);
			rct.setFill(Color.SADDLEBROWN);
			root.getChildren().add(rct);
		}
		if (construction instanceof Model.Building) {
			double width = 20;
			double height = 30;
			Rectangle rct = new Rectangle(construction.getRight() - 2 * width, construction.getBottom() - height, width,
					height);
			rct.setFill(Color.SANDYBROWN);
			root.getChildren().add(rct);
		}
		if (construction instanceof Model.Castle) {
			double width = 50;
			double height = 80;
			Rectangle rct = new Rectangle((construction.getRight() + construction.getLeft()) / 2 - width / 2,
					construction.getBottom() - height, width, height);
			rct.setFill(Color.SANDYBROWN);
			Arc ar = new Arc((construction.getRight() + construction.getLeft()) / 2, construction.getBottom() - height,
					width / 2, width / 2, 0, 180);
			ar.setFill(Color.SANDYBROWN);

			root.getChildren().addAll(rct, ar);
		}

	}

	public void drawRoof(Model.Construction construction) {
		if (construction instanceof Model.House || construction instanceof Model.Building) {
			Polygon pl = new Polygon();
			pl.getPoints()
					.addAll(new Double[] { (double) construction.getLeft(), (double) construction.getTop(),
							(construction.getLeft() + construction.getRight()) / 2.0, construction.getTop() - 40.0,
							(double) construction.getRight(), (double) construction.getTop() });
			pl.setFill(construction.getRoofColor());
			root.getChildren().add(pl);
		} else if (construction instanceof Model.Castle) {
			Polygon pl1 = new Polygon();
			Double[] pts1 = new Double[6]; // roof
			pts1[0] = (double) construction.getLeft() - ((Model.Castle) construction).getTowerWidth();
			pts1[1] = (double) construction.getBottom() - ((Model.Castle) construction).getTowerHeight();
			pts1[4] = (double) construction.getLeft();
			pts1[5] = pts1[1];
			pts1[2] = (pts1[0] + pts1[4]) / 2;
			pts1[3] = pts1[1] - 40;

			pl1.getPoints().addAll(pts1);
			pl1.setFill(construction.getRoofColor());

			Polygon pl2 = new Polygon();
			Double[] pts2 = new Double[6]; // roof
			// x of point 1
			pts2[0] = (double) construction.getRight();
			// y of point1
			pts2[1] = (double) construction.getBottom() - ((Model.Castle) construction).getTowerHeight();
			// x of point 3 ()
			pts2[4] = (double) construction.getRight() + ((Model.Castle) construction).getTowerWidth();
			// y of point 3
			pts2[5] = pts2[1];
			// x of point 2
			pts2[2] = (pts2[0] + pts2[4]) / 2;
			// y of point 2
			pts2[3] = pts2[1] - 40;

			pl2.getPoints().addAll(pts2);
			pl2.setFill(construction.getRoofColor());

			root.getChildren().addAll(pl1, pl2);
		}

	}

	// the Listener (cl) is defined in Controller
	// here we set it the Listener of ToggleGroup tgr
	public void addShapeChangeListener(ChangeListener<Toggle> cl) {
		tgr.selectedToggleProperty().addListener(cl);
	}
	
	public void addRoofColorChangeListner(ChangeListener<String> cl) {
		comboB.valueProperty().addListener(cl);
	}

	public void addEventHandlerToRoof(EventHandler<ActionEvent> event) {
		cbRoof.setOnAction(event);
	}

	public void addEventHandlerToWindows(EventHandler<ActionEvent> event) {
		cbWindows.setOnAction(event);
	}

	public void addEventHandlerToDoor(EventHandler<ActionEvent> event) {
		cbDoor.setOnAction(event);
	}

	public ToggleGroup getTgr() {
		return tgr;
	}

	public CheckBox getCbWindows() {
		return cbWindows;
	}

	public CheckBox getCbRoof() {
		return cbRoof;
	}

	public CheckBox getCbDoor() {
		return cbDoor;
	}

	public Group getRoot() {
		return root;
	}

	public RadioButton getRbHouse() {
		return rbHouse;
	}

	public RadioButton getRbBuilding() {
		return rbBuilding;
	}

	public RadioButton getRbCastle() {
		return rbCastle;
	}

	public ArrayList<Rectangle> getWindows() {
		return windows;
	}
	
	public void addWindowsEventHandler(int index, EventHandler<MouseEvent> event) {
		// connect event to window [index]
		windows.get(index).addEventHandler(MouseEvent.MOUSE_CLICKED,event);
		
	}
	
}
