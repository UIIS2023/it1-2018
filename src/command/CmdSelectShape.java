package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdSelectShape implements Command{
	private DrawingModel model = new DrawingModel();
	private Shape shape;
	private DrawingController controller;
	
	private ArrayList<Shape> selectedShapes;
	
	public CmdSelectShape(DrawingModel model, Shape shape, DrawingController controller, ArrayList<Shape> selectedShapes) {
		this.model = model;
		this.shape = shape;
		this.controller = controller;
		this.selectedShapes = selectedShapes;
	}
	
	public void unexecute() {
		selectedShapes.add(shape);
		shape.setSelected(false);
		controller.notifyAllObservers();
		controller.notifyAllObserverUndoRedo();
		
	}
	
	public void execute() {
		selectedShapes.remove(shape);
		shape.setSelected(true);
		controller.notifyAllObservers();
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Select;"+shape.toString();
	}
}
