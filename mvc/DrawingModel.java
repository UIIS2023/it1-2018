package mvc;

import java.util.ArrayList;

import command.Command;
import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private ArrayList<Shape> garbageShapes = new ArrayList<Shape>();
	
	private static int currentId=1;
	
	public ArrayList<Shape> getShapes(){
		return shapes;
	}
	
	public void add(Shape shape){
		if(shape.getId()==0){
			shape.setId(currentId++);
		}else
			currentId++;
		garbageShapes.remove(shape);
		shapes.add(shape);
		
	}
	
	public boolean remove(Shape shape){
		garbageShapes.add(shape);
		return shapes.remove(shape);
	}
	
	public Shape get (int i){
		return shapes.get(i);
	}

	public ArrayList<Shape> getGarbageShapes() {
		return garbageShapes;
	}

	public void setGarbageShapes(ArrayList<Shape> garbageShapes) {
		this.garbageShapes = garbageShapes;
	}

	public static int getCurrentId() {
		return currentId;
	}

	public static void setCurrentId(int currentId) {
		DrawingModel.currentId = currentId;
	}

}
