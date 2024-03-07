package command;

import geometry.Line;
import geometry.Point;
import mvc.DrawingController;

public class CmdUpdateLine implements Command{
	
	private Line original;
	private Line newState;
	private Line oldState = null;
	
	private DrawingController controller;
	
	public CmdUpdateLine (Line original, Line newState,  DrawingController controller) {
		
		
		
		
		
		
		//ove ne treba controller nzm ocu ovo moci zbog logike videcu
		//donut
		//undo modify iz importa baca exception
		
		
		
		
		
		
		
		
		
		
		this.original = original;
		this.newState = newState;
		this.controller=controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method 
		oldState = new Line();
		oldState.setpBegin(original.getpBegin());
		oldState.setpEnd(original.getpEnd());
		oldState.setColor(original.getColor());
		oldState.setId(original.getId());
		
		original.setpBegin(newState.getpBegin());
		original.setpEnd(newState.getpEnd());
		original.setColor(newState.getColor());
		original.setSelected(false);
		newState.setId(original.getId());
		
		controller.notifyAllObserverUndoRedo();
		
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		if(oldState == null) {
			newState.setpBegin(original.getpBegin());
			newState.setpEnd(original.getpEnd());
			newState.setColor(original.getColor());
			return;
		}
		
		original.setpBegin(oldState.getpBegin());
		original.setpEnd(oldState.getpEnd());
		original.setColor(oldState.getColor());
		
		
		controller.notifyAllObserverUndoRedo();
	}
	
	public String toString(){
		return "Command:Update;OldState:[" + oldState.toString() + "];NewState:[" + newState.toString() + "]";
	}

}
