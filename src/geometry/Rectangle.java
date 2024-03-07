package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends RectangleDraw{
	private int height;
	

	public Rectangle(){

	}
	public Rectangle(Point upperLeft, int width, int height){
		super(upperLeft, width);
		this.height = height;
	}

	public Rectangle(Point upperLeft, int width, int height,Color color){
		this(upperLeft,width,height);
		setColor(color);
	}
	public Rectangle(Point upperLeft, int width, int height,Color color, Color areaColor){
		this(upperLeft,width,height,color);
		setAreaColor(areaColor);
	}

	public int surface(){
		return height * a;
	}
	public int scope(){
		return  2 * height + 2 * a;
	}
	//Tacka gore levo=(xGoreLevo,yGoreLevo), sirina=sirina, visina=visina
	public String toString(){
		return "Shape:Rectangle;UpperLeft:("+upperLeft.getX()+","+upperLeft.getY()+");sideLength:"+a + ";height:"+ height +";Edge color:" + getColor().getRed() +","+getColor().getGreen()
				+","+getColor().getBlue() + ";Surface color:" + getAreaColor().getRed()+"," + getAreaColor().getGreen() 
				+ "," + getAreaColor().getBlue() +";id:"+this.getId();	}

	public boolean equals(Object obj){
		if(obj instanceof Rectangle){
			Rectangle helper = (Rectangle) obj;
			if(upperLeft.equals(helper.upperLeft) && a == helper.a && height == helper.height)
				return true;
			else
				return false;

		}
		else
			return false;
	}
	public Line diagonal(){
		return new Line(upperLeft, new Point(upperLeft.getX() + a,upperLeft.getY() + height));
	}
	public Point center(){
		return diagonal().lineMiddle();
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(findColor("plava"));
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+a, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+height)).selected(g);
		new Line(new Point(getUpperLeft().getX()+a, getUpperLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+height), diagonal().getpEnd()).selected(g);
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + a)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + height))
			return true;
		else 
			return false;

	}
	public void paint(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), a, height);
		fill(g);
		if(isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, a-1, height-1);
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
