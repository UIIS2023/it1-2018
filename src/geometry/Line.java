package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	private Point pBegin;
	private Point pEnd;

	public Line(){

	}
	public Line(Point pBegin, Point tKrajnja){
		this.pBegin = pBegin;
		this.pEnd = tKrajnja;
	}
	public Line(Point pBegin, Point pEnd, String color){
		this(pBegin,pEnd);
		setStrColor(color);
	}
	public Line(Point pBegin, Point pEnd, Color color){
		this(pBegin,pEnd);
		setColor(color);
	}

	public void moveFor(int x, int y){
		pBegin.setX(pBegin.getX()+x);
		pBegin.setY(pBegin.getY()+y);
		pEnd.setX(pEnd.getX()+x);
		pEnd.setY(pEnd.getY()+y);		
	}

	public double lenght(){
		return pBegin.udaljenost(pEnd);
	}

	public String toString(){
		return "Shape:Line;Start:("+pBegin.getX()+ "," + pBegin.getY() +");End:("+pEnd.getX() + "," + pEnd.getY()+");Edge color:" + getColor().getRed() +","+getColor().getGreen() + "," + getColor().getBlue()+";id:"+this.getId();	}
	
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line helper = (Line) obj;
			if(pBegin.equals(helper.getpBegin()) && pEnd.equals(helper.getpEnd()))
				return true;
			else
				return false;

		}
		else
			return false;
	}
	public Point lineMiddle(){
		int middleX = (pBegin.getX() + pEnd.getX()) / 2;
		int middleY = (pBegin.getY() + pEnd.getY()) / 2;
		return new Point(middleX, middleY);
	}
	public boolean contains(int x, int y){
		Point placeOfClick = new Point(x, y);
		if(placeOfClick.udaljenost(pBegin)+placeOfClick.udaljenost(pEnd)-this.lenght()<0.5)
			return true;
		else 
			return false;
	}
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		pBegin.selected(g);
		pEnd.selected(g);
		lineMiddle().selected(g);
		setSelected(true);
	}
	public void paint(Graphics g){
		g.setColor(getColor());
		g.drawLine(pBegin.getX(), pBegin.getY(), pEnd.getX(), pEnd.getY());
		if(isSelected())
			selected(g);
	}


	public int compareTo(Object o) {
		if(o instanceof Line){
			Line helper = (Line) o;
			return (int) (this.lenght() - helper.lenght());
		}
		else
			return 0;
	}
	public Point getpBegin(){
		return pBegin;
	}
	public Point getpEnd(){
		return pEnd;
	}
	public void setpBegin(Point pBegin){
		this.pBegin = pBegin;
	}
	public void setpEnd(Point pEnd){
		this.pEnd = pEnd;
	}

}
