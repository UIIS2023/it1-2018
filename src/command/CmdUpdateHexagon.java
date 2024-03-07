package command;

import geometry.HexagonAdapter;
import hexagon.Hexagon;
import mvc.DrawingController;

public class CmdUpdateHexagon implements Command{
	private HexagonAdapter original;
	private HexagonAdapter newState;
	private HexagonAdapter oldState = null;
	
	private DrawingController controller;
	
	public CmdUpdateHexagon(HexagonAdapter original, HexagonAdapter newState, DrawingController controller) {
		this.original = original;
		this.newState = newState;
		this.controller = controller;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		oldState = new HexagonAdapter(0,0,0,null,null);
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setR(original.getR());
		oldState.setColor(original.getColor());
		oldState.setAreaColor(original.getAreaColor());
		oldState.setId(original.getId());
		
		original.setX(newState.getX());
		original.setY(newState.getY());
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
			newState.setX(original.getX());
			newState.setY(original.getY());
			newState.setColor(original.getColor());
			newState.setAreaColor(original.getAreaColor());
			newState.setR(original.getR());
			newState.setId(original.getId());
			return;
		}
		original.setX(oldState.getX());
		original.setY(oldState.getY());
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
