package command;

import geometry.Rectangle;
import geometry.RectangleDraw;
import mvc.DrawingController;

public class CmdUpdateRectangle implements Command{
	private Rectangle original;
	private Rectangle newState;
	private Rectangle oldState = null;
	
	private DrawingController controller;
	
	public CmdUpdateRectangle (Rectangle original, Rectangle newState, DrawingController controller) {
		this.original = original;
		this.newState = newState;
		this.controller = controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldState = new Rectangle();
		oldState.setUpperLeft(original.getUpperLeft());
		oldState.setA(original.getA());
		oldState.setHeight(original.getHeight());
		oldState.setColor(original.getColor());
		oldState.setAreaColor(original.getAreaColor());
		oldState.setId(original.getId());
		
		original.setUpperLeft(newState.getUpperLeft());
		original.setA(newState.getA());
		original.setHeight(newState.getHeight());
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
			newState.setUpperLeft(original.getUpperLeft());
			newState.setColor(original.getColor());
			newState.setAreaColor(original.getAreaColor());
			newState.setA(original.getA());
			newState.setHeight(original.getHeight());
			newState.setId(original.getId());
			return;
		}
		original.setUpperLeft(oldState.getUpperLeft());
		original.setA(oldState.getA());
		original.setHeight(oldState.getHeight());
		original.setColor(oldState.getColor());
		original.setAreaColor(oldState.getAreaColor());
		original.setId(oldState.getId());
		
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Update;OldState:[" + oldState.toString() + "];NewState:[" + newState.toString() + "]";
	}
}
