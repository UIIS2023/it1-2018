package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdDeselectShape implements Command{
	private DrawingModel model = new DrawingModel();
	private Shape shape;
	private DrawingController controller;
	
	private ArrayList<Shape> selectedShapes;
	
	public CmdDeselectShape(DrawingModel model, Shape shape, DrawingController controller, ArrayList<Shape> selectedShapes) {
		this.model = model;
		this.shape = shape;
		this.controller = controller;
		this.selectedShapes = selectedShapes;
	}
	
	public void unexecute() {
		selectedShapes.remove(shape);
		shape.setSelected(true);
		controller.notifyAllObservers();
		controller.notifyAllObserverUndoRedo();
	}
	
	public void execute() {
		selectedShapes.add(shape);
		shape.setSelected(false);
		controller.notifyAllObservers();
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString() {
		return "Command:Deselect;"+shape.toString();
	}

}
