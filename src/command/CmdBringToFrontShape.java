package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CmdBringToFrontShape implements Command{
	
	
	private DrawingModel model;
	private Shape shape;
	private DrawingController controller;
	private int index;
	
	public CmdBringToFrontShape(DrawingModel model, Shape shape, DrawingController controller, int index) {
		this.model = model;
		this.shape = shape;
		this.controller = controller;
		this.index = index;
	}

	@Override
	public void execute() {
			if (controller.getCurrent() != null) {
				for (int i = 0; i < model.getShapes().size() - 1; i++) {
					if (model.getShapes().get(i).equals(controller.getCurrent())) {
						if (i < model.getShapes().size()) {
							Shape temp = model.getShapes().get(i);
							model.getShapes().remove(i);
							model.getShapes().add(model.getShapes().size(), temp);
							controller.notifyAllObserverUndoRedo();
							controller.notifyAllObservers();
						}
					}
				}
			}
		}

	@Override
	public void unexecute() {
		if (controller.getCurrent() != null) {
			for (int i = 0; i < model.getShapes().size(); i++) {
				if (model.getShapes().get(i).equals(controller.getCurrent())) {
					if (i < model.getShapes().size()) {
						Shape temp = model.getShapes().get(i);
						model.getShapes().remove(i);
						model.getShapes().add(controller.getPos2(), temp);
						controller.notifyAllObserverUndoRedo();
						controller.notifyAllObservers();
					}
				}
			}
		}
	}
	
	public String toString(){
		return "Command:BringToFront;id:"+shape.getId() + ";index:" + index;
	}
}
