package command;

import geometry.Point;
import mvc.DrawingController;

public class CmdUpdatePoint implements Command{
	private Point original;
	private Point newState;
	private Point oldState = null;
	
	private DrawingController controller;
	
	public CmdUpdatePoint(Point original, Point newState, DrawingController controller) {
		this.original = original;
		this.newState = newState;
		this.controller=controller;
	}
	
	@Override
	public void execute() {
		oldState = new Point();
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setColor(original.getColor());
		oldState.setId(original.getId());
		
		original.setX(newState.getX());
		original.setY(newState.getY());
		original.setColor(newState.getColor());
		original.setSelected(false);
		newState.setId(original.getId());
		
		controller.notifyAllObserverUndoRedo();
	}

	@Override
	public void unexecute() {
		if (oldState == null) {
			newState.setX(original.getX());
			newState.setY(original.getY());
			newState.setColor(original.getColor());
			//newState.setId(original.getId());
			return;
		}
		original.setX(oldState.getX());
		original.setY(oldState.getY());
		original.setColor(oldState.getColor());
		original.setId(oldState.getId());
		
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Update;OldState:[" + oldState.toString() + "];NewState:[" + newState.toString() + "]";
	}
	

}
