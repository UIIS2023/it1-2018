package command;

import geometry.Donut;
import mvc.DrawingController;

public class CmdUpdateDonut implements Command{
	
	private Donut original;
	private Donut newState;
	private Donut oldState = null;
	
	private DrawingController controller;
	
	public CmdUpdateDonut (Donut original, Donut newState,DrawingController controller ) {
		this.original = original;
		this.newState = newState;
		this.controller = controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldState = new Donut();
		oldState.setCentar(original.getCentar());
		oldState.setR(original.getR());
		oldState.setColor(original.getColor());
		oldState.setAreaColor(original.getAreaColor());
		oldState.setId(original.getId());
		
		original.setCentar(newState.getCentar());
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
			newState.setCentar(original.getCentar());
			newState.setColor(original.getColor());
			newState.setAreaColor(original.getAreaColor());
			newState.setR(original.getR());
			newState.setId(original.getId());
			return;
		}
		original.setCentar(oldState.getCentar());
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
