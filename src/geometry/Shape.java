package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Comparable,  Serializable{
	private String strColor = "crna";
	private Color color = Color.BLACK;
	private boolean selected;
	private int id;

	public Shape(){
		
	}
	public Shape(String strColor){
		this.strColor = strColor;
	}
	public Shape(Color color){
		this.color=color;
	}
	
	public abstract void paint(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	
	public static Color findColor(String color){
		if(color.equalsIgnoreCase("crna"))
			return Color.BLACK;
		else if(color.equalsIgnoreCase("bela"))
			return Color.WHITE;
		else if(color.equalsIgnoreCase("plava"))
			return Color.BLUE;
		else if(color.equalsIgnoreCase("crvena"))
			return Color.RED;
		else if(color.equalsIgnoreCase("zelena"))
			return Color.GREEN;
		else if(color.equalsIgnoreCase("zuta"))
			return Color.YELLOW;
		else if(color.equalsIgnoreCase("pink"))
			return Color.PINK;
		else
			return Color.BLACK;
		
	}
	
	public String getStrColor() {
		return strColor;
	}
	public void setStrColor(String color) {
		this.strColor = color;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
