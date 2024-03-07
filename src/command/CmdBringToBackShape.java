package command;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdBringToBackShape implements Command{
	private DrawingModel model;
	private Shape shape;
	private DrawingController controller;
	private int index;
	
	public CmdBringToBackShape(DrawingModel model, Shape shape, DrawingController controller, int index) {
		this.model = model;
		this.shape = shape;
		this.controller = controller;
		this.index = index;
	}

	@Override
	public void execute() {
			if (controller.getCurrent() != null) {
				for (int i = 0; i < model.getShapes().size(); i++) {
					if (model.getShapes().get(i).equals(controller.getCurrent())) {
						if (i > 0) {
							Shape temp = model.getShapes().get(i);
							model.getShapes().remove(i);
							model.getShapes().add(0, temp);
							controller.notifyAllObserverUndoRedo();
							controller.notifyAllObservers();
						}
					}
				}
			}
		}

	@Override
	public void unexecute() {
		int helper = 0;
		for (int j = 0; j < model.getShapes().size()-1; j++) {
			if (model.getShapes().get(j).isSelected()) {
				helper = j;
			}
		}
			if (controller.getCurrent() != null) {
				for (int i = 0; i < model.getShapes().size()-1; i++) {
					if (model.getShapes().get(i).equals(controller.getCurrent())) {
						if (i < model.getShapes().size()) {
							Shape temp = model.getShapes().get(i);
							model.getShapes().remove(i);
							model.getShapes().add(controller.getPos(), temp);
							controller.notifyAllObserverUndoRedo();
							controller.notifyAllObservers();
						}
					}
				}
			}
			
	}
	
	public String toString(){
		return "Command:BringToBack;id:"+shape.getId() + ";index:" + index;
	}

}

