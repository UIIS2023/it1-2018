package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements Moveable{
	private int x;
	private int y;


	public Point(){

	}
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point(int x, int y, String strColor){
		this(x, y);
		setStrColor(strColor);
	}
	public Point(int x, int y, Color color){
		this(x,y);
		setColor(color);
	}

	public void moveTo(int newX, int newY){
		x = newX;
		setY(newY);
	}
	public void moveFor(int newX, int newY){
		x = x + newX;
		setY(getY()+newY);
	}

	public double udaljenost(Point t2){
		double dx = x - t2.getX();
		double dy = y - t2.getY();
		double result = Math.sqrt(dx*dx + dy*dy);

		return result;
	}
	// (x,y)
	public String toString(){
		return "Shape:Point;Coordinates:("+x+","+y+");"+"Edge Color:"+getColor().getRed()+"," + getColor().getGreen()+ "," + getColor().getBlue()+";id:"+this.getId();	}
	public boolean equals(Object obj){
		if(obj instanceof Point){
			Point helper = (Point) obj;
			if(x == helper.x && y == helper.y)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean contains(int x, int y){
		Point placeOfClick = new Point(x, y);
		if(placeOfClick.udaljenost(this)<=2)
			return true;
		else
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3, 6, 6);
		//setSelektovan(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(getColor());
		g.drawLine(x+2, y, x-2, y);
		g.drawLine(x, y-2, x, y+2);
		if(isSelected())
			selected(g);

	}

	public int compareTo(Object o) {
		if(o instanceof Point){
			Point helper = (Point) o;
			return (int) this.udaljenost(new Point(0, 0)) - (int)helper.udaljenost(new Point(0, 0));
			
		}
		else
			return 0;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int newX){
		x = newX;
	}
	public void setY(int newY){
		y = newY;
	}

}
