package command;

import java.util.ArrayList;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdRemoveShape implements Command{
	private DrawingModel model = new DrawingModel();
	private Shape shape;
	private DrawingController controller;
	private ArrayList<Shape> garbageShapes= new ArrayList<Shape>();
	
	public CmdRemoveShape(DrawingModel model, Shape shape,  DrawingController controller) {
		this.model = model;
		this.shape = shape;
		this.controller=controller;
	}
	
	public void execute() {
		model.remove(shape);
		controller.notifyAllObserverUndoRedo();
	}
	
	public void unexecute() {
		model.add(shape);
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Delete;"+shape.toString();
	}
}
