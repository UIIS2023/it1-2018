package command;


import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdAddShape implements Command {
	private DrawingModel model = new DrawingModel();
	private Shape shape;
	private DrawingController controller;
	
	public CmdAddShape(DrawingModel model, Shape shape,  DrawingController controller) {
		this.model = model;
		this.shape = shape;
		this.controller = controller;
	}
	
	public void unexecute() {
		model.remove(shape);
		controller.notifyAllObserverUndoRedo();
	}
	
	public void execute() {
		model.add(shape);
		controller.notifyAllObserverUndoRedo();
		
	}
	
	public String toString(){
		return "Command:Add;"+shape.toString();
	}
}
