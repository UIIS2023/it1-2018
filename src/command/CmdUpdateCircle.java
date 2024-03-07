package command;

import geometry.Circle;
import mvc.DrawingController;

public class CmdUpdateCircle implements Command{
	
	private Circle original;
	private Circle newState;
	private Circle oldState = null;
    private DrawingController controller;
	
	public CmdUpdateCircle(Circle original, Circle newState, DrawingController controller) {
		this.original = original;
		this.newState = newState;
		this.controller = controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldState = new Circle();
		oldState.setCenter(original.getCenter());
		oldState.setR(original.getR());
		oldState.setColor(original.getColor());
		oldState.setAreaColor(original.getAreaColor());
		oldState.setId(original.getId());
		
		original.setCenter(newState.getCenter());
		original.setR(newState.getR());
		original.setColor(newState.getColor());
		original.setAreaColor(newState.getAreaColor());
		original.setSelected(false);
		newState.setId(original.getId());
		controller.notifyAllObserverUndoRedo();
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		if (oldState == null) {
			newState.setCenter(original.getCenter());
			newState.setColor(original.getColor());
			newState.setAreaColor(original.getAreaColor());
			newState.setR(original.getR());
			newState.setId(original.getId());
			return;
		}
		original.setCenter(oldState.getCenter());
		original.setR(oldState.getR());
		original.setColor(oldState.getColor());
		original.setAreaColor(oldState.getAreaColor());
		original.setId(oldState.getId());
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Update;OldState:[" + oldState.toString() + "];NewState:[" + newState.toString() + "]";
	}

}
