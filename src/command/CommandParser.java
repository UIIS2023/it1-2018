package command;

import java.awt.Color;
import java.util.ArrayList;

import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;
import mvc.DrawingController;
import mvc.DrawingModel;

public class CommandParser {
	
	public static Command createCommandFromString(String commandString, DrawingModel model, CommandList commandList, DrawingController controller, ArrayList<Shape> selectedShapes) {
		String[] commandProperties = commandString.split(";");
		String commandType = commandProperties[0];

		String[] operation = commandProperties[1].split(":");
		String commandOperation = operation[1];
		String[] shapes = commandProperties[2].split(":");
		String commandShape = shapes[1];

		
		if(commandProperties[0].equals("[New Command]" ) || commandProperties[0].equals("[Redo]")){
			if (commandOperation.equals("BringToFront")) {
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				int index = parseValueFromString(commandProperties[3].split(":"));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdBringToFront = new CmdBringToFrontShape(model, shape, controller, controller.getPos2());
				return cmdBringToFront;
			} else if (commandOperation.equals("BringToBack")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				int index = parseValueFromString(commandProperties[3].split(":"));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdBringToBack = new CmdBringToBackShape(model, shape, controller, controller.getPos());
				return cmdBringToBack;
			} else if (commandOperation.equals("ToFront")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdToFront = new CmdToFrontShape(model, shape, controller);
				return cmdToFront;
			} else if (commandOperation.equals("ToBack")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdToBack = new CmdToBackShape(model, shape, controller);
				return cmdToBack;
			}
			else if(commandOperation.equals("Add")) {
				Shape shape;
				if (commandShape.equals("Line")) {
					shape = parseLineFromString(commandProperties,3);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					controller.setHelper(controller.getHelper()+1);
					return cmdAdd;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					shape = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shape.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shape.setId(id);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					controller.setHelper(controller.getHelper()+1);
					return cmdAdd;
				}
				
				else if (commandShape.equals("Rectangle")) {
					shape = parseRectangleFromString(commandProperties,3);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					controller.setHelper(controller.getHelper()+1);
					return cmdAdd;
				}
				else if (commandShape.equals("Circle")) {
					shape = parseCircleFromString(commandProperties,3);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					controller.setHelper(controller.getHelper()+1);
					return cmdAdd;
				}
				else if (commandShape.equals("Hexagon")) {
					shape = parseHexagonFromString(commandProperties,3);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					controller.setHelper(controller.getHelper()+1);
					return cmdAdd;
				}
			}
			else if (commandOperation.equals("Update")) {
				Shape shape = null;
				int commandTypeEnd = commandString.indexOf("]");
				commandString = commandString.substring(commandTypeEnd+2);
				int startOfOldState = commandString.indexOf("[");
				int endOfOldState = commandString.indexOf("]");
				String oldState = commandString.substring(startOfOldState, endOfOldState);
				oldState = oldState.replace("[", "");
				oldState = oldState.replace("]", "");
				
				int startOfNewState = commandString.lastIndexOf("[");
				int endOfNew = commandString.lastIndexOf("]");
				String newState = commandString.substring(startOfNewState, endOfNew);
				newState = newState.replace("[", "");
				newState = newState.replace("]", "");
				
				String[] oldStateProperties = oldState.split(";");
				String[] newStateProperties = newState.split(";");
				commandShape = oldStateProperties[0].split(":")[1];
				if (commandShape.equals("Point")) {
					Shape shapeOldState = parsePointFromString(oldStateProperties[1].split(":")[1]);
					shapeOldState.setColor(parseColorFromString(oldStateProperties[2].split(":")[1]));
					shapeOldState.setId(parseValueFromString(oldStateProperties[3].split(":")));
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeOldState = model.get(i);
							break;
						}
					}
					Shape shapeNewState = parsePointFromString(newStateProperties[1].split(":")[1]);
					shapeNewState.setColor(parseColorFromString(newStateProperties[2].split(":")[1]));
					shapeNewState.setId(parseValueFromString(newStateProperties[3].split(":")));
					
					System.out.println(shapeOldState);
					System.out.println(shapeNewState);
					Command cmdModify = new CmdUpdatePoint((Point)shapeOldState, (Point)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Line")) {
					Shape shapeOldState = parseLineFromString(oldStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeOldState = model.get(i);
							break;
						}
					}
					Shape shapeNewState = parseLineFromString(newStateProperties, 1);
					Command cmdModify = new CmdUpdateLine((Line)shapeOldState, (Line)shapeNewState,controller); 
					return cmdModify;
				
				}else if (commandShape.equals("Rectangle")) {
					Shape shapeOldState = parseRectangleFromString(oldStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeOldState = model.get(i);
							break;
						}
					}
					Shape shapeNewState = parseRectangleFromString(newStateProperties, 1);
					Command cmdModify = new CmdUpdateRectangle((Rectangle)shapeOldState, (Rectangle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Circle")) {
					Shape shapeOldState = parseCircleFromString(oldStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeOldState = model.get(i);
							break;
						}
					}
					Shape shapeNewState = parseCircleFromString(newStateProperties, 1);
					Command cmdModify = new CmdUpdateCircle((Circle)shapeOldState, (Circle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Hexagon")) {
					Shape shapeOldState = parseHexagonFromString(oldStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeOldState = model.get(i);
							break;
						}
					}
					Shape shapeNewState = parseHexagonFromString(newStateProperties, 1);
					Command cmdModify = new CmdUpdateHexagon((HexagonAdapter)shapeOldState, (HexagonAdapter)shapeNewState,controller); 
					return cmdModify;
				}
//				System.out.println(oldState + "\nNewState:  " + newState);
//				System.out.println(oldState[1]);
//				String[] newState = commandProperties[3].split(":");
//				System.out.println(newState[1]);
			}
			else if (commandOperation.equals("Select")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Deselect")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Delete")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					controller.setHelper(controller.getHelper()-1);
					return cmdDelete;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
			}
		} else if (commandProperties[0].equals("[Undo]")) {
			if (commandOperation.equals("BringToFront")) {
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				int index = parseValueFromString(commandProperties[3].split(":"));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdBringToFront = new CmdBringToFrontShape(model, shape, controller, controller.getPos2());
				return cmdBringToFront;
			} else if (commandOperation.equals("BringToBack")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				int index = parseValueFromString(commandProperties[3].split(":"));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdBringToBack = new CmdBringToBackShape(model, shape, controller, controller.getPos());
				return cmdBringToBack;
			} else if (commandOperation.equals("ToFront")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdToFront = new CmdToFrontShape(model, shape, controller);
				return cmdToFront;
			} else if (commandOperation.equals("ToBack")){
				Shape shape = new Point();
				shape.setId(parseValueFromString(commandProperties[2].split(":")));
				for (int i = 0; i < model.getShapes().size();i++) {
					if (model.get(i).getId() == shape.getId()) {
						shape = model.get(i);
						break;
					}
				}
				Command cmdToBack = new CmdToBackShape(model, shape, controller);
				return cmdToBack;
			}
			else if(commandOperation.equals("Add")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				
				else if (commandShape.equals("Rectangle")) {
					
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()-1);
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
			}	else if (commandOperation.equals("Deselect")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);
					return cmdSelect;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Select")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Delete")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()+1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()+1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()+1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()+1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					controller.setHelper(controller.getHelper()+1);
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
			}
			else if (commandOperation.equals("Update")) {
				Shape shape = null;
				int commandTypeEnd = commandString.indexOf("]");
				commandString = commandString.substring(commandTypeEnd+2);
				int startOfOldState = commandString.indexOf("[");
				int endOfOldState = commandString.indexOf("]");
				String oldState = commandString.substring(startOfOldState, endOfOldState);
				oldState = oldState.replace("[", "");
				oldState = oldState.replace("]", "");
				
				int startOfNewState = commandString.lastIndexOf("[");
				int endOfNew = commandString.lastIndexOf("]");
				String newState = commandString.substring(startOfNewState, endOfNew);
				newState = newState.replace("[", "");
				newState = newState.replace("]", "");
				
				String[] oldStateProperties = oldState.split(";");
				String[] newStateProperties = newState.split(";");
				commandShape = oldStateProperties[0].split(":")[1];
				if (commandShape.equals("Point")) {
					Shape shapeOldState = parsePointFromString(oldStateProperties[1].split(":")[1]);
					shapeOldState.setColor(parseColorFromString(oldStateProperties[2].split(":")[1]));
					shapeOldState.setId(parseValueFromString(oldStateProperties[3].split(":")));
					
					Shape shapeNewState = parsePointFromString(newStateProperties[1].split(":")[1]);
					shapeNewState.setColor(parseColorFromString(newStateProperties[2].split(":")[1]));
					shapeNewState.setId(parseValueFromString(newStateProperties[3].split(":")));
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					
					System.out.println(shapeOldState);
					System.out.println(shapeNewState);
					Command cmdModify = new CmdUpdatePoint((Point)shapeOldState, (Point)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Line")) {
					Shape shapeOldState = parseLineFromString(oldStateProperties, 1);
					Shape shapeNewState = parseLineFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateLine((Line)shapeOldState, (Line)shapeNewState,controller); 
					return cmdModify;
				
				}else if (commandShape.equals("Rectangle")) {
					Shape shapeOldState = parseRectangleFromString(oldStateProperties, 1);
					Shape shapeNewState = parseRectangleFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateRectangle((Rectangle)shapeOldState, (Rectangle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Circle")) {
					Shape shapeOldState = parseCircleFromString(oldStateProperties, 1);
					Shape shapeNewState = parseCircleFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateCircle((Circle)shapeOldState, (Circle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Hexagon")) {
					Shape shapeOldState = parseHexagonFromString(oldStateProperties, 1);
					Shape shapeNewState = parseHexagonFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateHexagon((HexagonAdapter)shapeOldState, (HexagonAdapter)shapeNewState,controller); 
					return cmdModify;
				}
			}
		}
		else if (commandProperties[0].equals("[Undo]")) {
			if(commandOperation.equals("Add")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				
				else if (commandShape.equals("Rectangle")) {
					
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId()==shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdAdd = new CmdAddShape(model, shape,controller);
					return cmdAdd;
				}
			}	else if (commandOperation.equals("Deselect")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);
					return cmdSelect;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdDeselectShape(model,shape,controller, selectedShapes);

					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Select")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeToFind.getId()) {
							shape = model.get(i);
							break;
						}
					}
					Command cmdSelect = new CmdSelectShape(model,shape,controller,selectedShapes);
					return cmdSelect;
				}
			}
			else if (commandOperation.equals("Delete")) {
				Shape shape = null;
				if (commandShape.equals("Line")) {
					Shape shapeToFind = parseLineFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				} else if (commandShape.equals("Point")) {
					String[] coordinates = commandProperties[3].split(":");
					Shape shapeToFind = parsePointFromString(coordinates[1]);
					String[] colorString = commandProperties[4].split(":");
					shapeToFind.setColor(parseColorFromString(colorString[1]));
					int id = Integer.parseInt(commandProperties[5].split(":")[1]);
					shapeToFind.setId(id);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				
				else if (commandShape.equals("Rectangle")) {
					Shape shapeToFind = parseRectangleFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Circle")) {
					Shape shapeToFind = parseCircleFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
				else if (commandShape.equals("Hexagon")) {
					Shape shapeToFind = parseHexagonFromString(commandProperties,3);
					for (int i = 0; i < model.getGarbageShapes().size(); i++) {
						if (model.getGarbageShapes().get(i).getId() == shapeToFind.getId()) {
							shape = model.getGarbageShapes().get(i);
							break;
						}
					}
					Command cmdDelete = new CmdRemoveShape(model, shape,controller);
					return cmdDelete;
				}
			}
			else if (commandOperation.equals("Update")) {
				Shape shape = null;
				int commandTypeEnd = commandString.indexOf("]");
				commandString = commandString.substring(commandTypeEnd+2);
				int startOfOldState = commandString.indexOf("[");
				int endOfOldState = commandString.indexOf("]");
				String oldState = commandString.substring(startOfOldState, endOfOldState);
				oldState = oldState.replace("[", "");
				oldState = oldState.replace("]", "");
				
				int startOfNewState = commandString.lastIndexOf("[");
				int endOfNew = commandString.lastIndexOf("]");
				String newState = commandString.substring(startOfNewState, endOfNew);
				newState = newState.replace("[", "");
				newState = newState.replace("]", "");
				
				String[] oldStateProperties = oldState.split(";");
				String[] newStateProperties = newState.split(";");
				commandShape = oldStateProperties[0].split(":")[1];
				if (commandShape.equals("Point")) {
					Shape shapeOldState = parsePointFromString(oldStateProperties[1].split(":")[1]);
					shapeOldState.setColor(parseColorFromString(oldStateProperties[2].split(":")[1]));
					shapeOldState.setId(parseValueFromString(oldStateProperties[3].split(":")));
					
					Shape shapeNewState = parsePointFromString(newStateProperties[1].split(":")[1]);
					shapeNewState.setColor(parseColorFromString(newStateProperties[2].split(":")[1]));
					shapeNewState.setId(parseValueFromString(newStateProperties[3].split(":")));
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					
					System.out.println(shapeOldState);
					System.out.println(shapeNewState);
					Command cmdModify = new CmdUpdatePoint((Point)shapeOldState, (Point)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Line")) {
					Shape shapeOldState = parseLineFromString(oldStateProperties, 1);
					Shape shapeNewState = parseLineFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateLine((Line)shapeOldState, (Line)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Rectangle")) {
					Shape shapeOldState = parseRectangleFromString(oldStateProperties, 1);
					Shape shapeNewState = parseRectangleFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeOldState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateRectangle((Rectangle)shapeOldState, (Rectangle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Circle")) {
					Shape shapeOldState = parseCircleFromString(oldStateProperties, 1);
					Shape shapeNewState = parseCircleFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateCircle((Circle)shapeOldState, (Circle)shapeNewState,controller); 
					return cmdModify;
				} else if (commandShape.equals("Hexagon")) {
					Shape shapeOldState = parseHexagonFromString(oldStateProperties, 1);
					Shape shapeNewState = parseHexagonFromString(newStateProperties, 1);
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.get(i).getId() == shapeNewState.getId()) {
							shapeNewState = model.get(i);
							break;
						}
					}
					Command cmdModify = new CmdUpdateHexagon((HexagonAdapter)shapeOldState, (HexagonAdapter)shapeNewState,controller); 
					return cmdModify;
				}
			}
		}
		return null;
	}
	
	private static Point parsePointFromString(String point) {
		point = point.replace("(", "");
		point = point.replace(")","");
		String[] coordinates = point.split(",");
		System.out.println("X:" + coordinates[0] + " Y:"+ coordinates[1]);
		Point newPoint = new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
		return newPoint;
	}
	private static Line parseLineFromString(String[] commandProperties, int i) {
		String[] commandStartPoint = commandProperties[i].split(":");
		Point startLinePoint = parsePointFromString(commandStartPoint[1]); 
		String[] commandEndPoint = commandProperties[i+1].split(":");
		Point endLinePoint = parsePointFromString(commandEndPoint[1]);
		String[] lineEdgeColor = commandProperties[i+2].split(":");
		Color edgeColor = parseColorFromString(lineEdgeColor[1]);
		int id = Integer.parseInt(commandProperties[i+3].split(":")[1]);
		Line shape = new Line(startLinePoint, endLinePoint, edgeColor);
		shape.setId(id);
		return shape;
	}
	
	
	private static Rectangle parseRectangleFromString(String[] commandProperties,int i) {
		Rectangle shape;
		String[] upperLeft = commandProperties[i].split(":");
		Point upperLeftPoint = parsePointFromString(upperLeft[1]);
		int sideLength = parseValueFromString(commandProperties[i+1].split(":"));
		int height = parseValueFromString(commandProperties[i+2].split(":"));
		Color edgeColor = parseColorFromString(commandProperties[i+3].split(":")[1]);
		Color surface = parseColorFromString(commandProperties[i+4].split(":")[1]);
		int id = Integer.parseInt(commandProperties[i+5].split(":")[1]);
		shape = new Rectangle(upperLeftPoint, sideLength, height, edgeColor, surface);
		shape.setId(id);
		return shape;
	}
	
	private static Circle parseCircleFromString(String[] commandProperties,int i) {
		Circle shape;
		String[] upperLeft = commandProperties[i].split(":");
		Point center = parsePointFromString(upperLeft[1]);
		int radius = parseValueFromString(commandProperties[i+1].split(":"));
		Color edgeColor = parseColorFromString(commandProperties[i+2].split(":")[1]);
		Color surfaceColor = parseColorFromString(commandProperties[i+3].split(":")[1]);
		int id = Integer.parseInt(commandProperties[i+4].split(":")[1]);
		shape = new Circle(center, radius, edgeColor, surfaceColor);
		shape.setId(id);
		return shape;
	}
	
	private static HexagonAdapter parseHexagonFromString(String[] commandProperties,int i) {
		Point center = parsePointFromString(commandProperties[i].split(":")[1]);
		int radius = parseValueFromString(commandProperties[i+1].split(":"));
		Color edgeColor = parseColorFromString(commandProperties[i+2].split(":")[1]);
		Color surfaceColor = parseColorFromString(commandProperties[i+3].split(":")[1]);
		int id = Integer.parseInt(commandProperties[i+4].split(":")[1]);
		HexagonAdapter hexagon = new HexagonAdapter(center.getX(), center.getY() ,radius, edgeColor, surfaceColor);
		hexagon.setId(id);
		return hexagon;
	}
	
	private static Color parseColorFromString(String color) {
		color=color.replace(" ", "");
		String[] colorValues = color.split(",");
		int R = Integer.parseInt(colorValues[0]);
		int G = Integer.parseInt(colorValues[1]);
		int B = Integer.parseInt(colorValues[2]);
		Color parsedColor = new Color(R, G, B);
		return parsedColor;
	}
	
	private static int parseValueFromString(String[] string) {
		return Integer.parseInt(string[1]);
	}
	
}