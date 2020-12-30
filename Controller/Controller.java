package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
	private Model.Model theModel;
	private View.View theView;
	public Controller(Model.Model model, View.View view) {
		theModel = model;
		theView = view;
		
		
	ChangeListener<Toggle> cl = new ChangeListener<Toggle>() {

		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
			RadioButton rb =(RadioButton)theView.getTgr().getSelectedToggle();
			if(rb == theView.getRbHouse()) {
				// 1. create house (in model)
				
				theModel.updateModel(1,
				theView.getCbWindows().isSelected(), 
				theView.getCbRoof().isSelected(), 
				theView.getCbDoor().isSelected());
				// 2.create windows in view
				// run over all windows in model 
				theView.getWindows().clear(); // remove previous windows
				for( int i =0 ;i<theModel.getConstruction().getWindows().size();i++) {
					
					Rectangle tempR = new Rectangle
							(theModel.getConstruction().getWindows().get(i).getLeft(),
							 theModel.getConstruction().getWindows().get(i).getTop(),
							 theModel.getConstruction().getWindows().get(i).getWidth(),
							 theModel.getConstruction().getWindows().get(i).getHeight());
					tempR.setFill(theModel.getConstruction().getWindows().get(i).getColor());
					theView.getWindows().add(tempR);
				}
			
			
				// 3. add EventHandler to each window
				for(int i=0;i<theModel.getConstruction().getWindows().size();i++) {
					theView.addWindowsEventHandler(i, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						// find the index of the window that is the target event 
						int index =theView.getWindows().indexOf(event.getTarget());
						// example: row of babies, one is crying, we need to shut him up
						// we search for him, hes the target and we shut him with motzetz
						// we didnt give him the motzetz yet.
						
						// 1. update the model
						theModel.getConstruction().getWindows().get(index).changeColor();
						
						//2. update the view
						theView.getWindows().get(index).setFill(theModel.getConstruction().getWindows().get(index).getColor());
						// 3. show view
						theView.updateShape(theModel.getConstruction());
						
					}
						
					}
					);
					
				}
			
			
			}
			else if (rb == theView.getRbBuilding())
				theModel.updateModel(2,
				theView.getCbWindows().isSelected(), 
				theView.getCbRoof().isSelected(), 
				theView.getCbDoor().isSelected());
			else	theModel.updateModel(3,
					theView.getCbWindows().isSelected(), 
					theView.getCbRoof().isSelected(), 
					theView.getCbDoor().isSelected());

			// in any case
			theView.updateShape(theModel.getConstruction());
		}
	};
	theView.addShapeChangeListener(cl);
	
	//add roof colors change listner
	theView.addRoofColorChangeListner(new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(newValue=="Red") 
				theModel.getConstruction().setRoofColor(Color.RED);
			else if(newValue=="Green") 
				theModel.getConstruction().setRoofColor(Color.GREEN);
			else if(newValue=="Gray") 
				theModel.getConstruction().setRoofColor(Color.GRAY);
			
			theView.updateShape(theModel.getConstruction());
		}
	});
	
	// add event handler to roof checkbox
	theView.addEventHandlerToRoof(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			//1. update Model
			theModel.getConstruction().setHasRoof(theView.getCbRoof().isSelected());
			//2. Update view
			theView.updateShape(theModel.getConstruction());		
		}
	});
	// add event handler to windows checkbox
	theView.addEventHandlerToWindows(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			//1. update Model
			theModel.getConstruction().setHasWindows(theView.getCbWindows().isSelected());
			//2. Update view
			theView.updateShape(theModel.getConstruction());		
		}
	});
	// add event handler to door checkbox
	theView.addEventHandlerToDoor(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			//1. update Model
			theModel.getConstruction().setHasDoor(theView.getCbDoor().isSelected());
			//2. Update view
			theView.updateShape(theModel.getConstruction());		
		}
	});
		
	}

}
