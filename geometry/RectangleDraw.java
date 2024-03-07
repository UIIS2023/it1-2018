package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class RectangleDraw extends AreaShape implements Moveable{
	protected Point upperLeft;
	protected int a;
	
	public RectangleDraw(){

	}
	public RectangleDraw(Point upperLeft, int a){
		this.upperLeft = upperLeft;
		this.a = a;
	}
	public RectangleDraw(Point upperLeft, int a,String strColor){
		this(upperLeft,a);
		setStrColor(strColor);
	}
	public RectangleDraw(Point upperLeft, int a, String color, String strAreaColor){
		this(upperLeft, a, color);
		setStrAreaColor(strAreaColor);
	}
	public RectangleDraw(Point upperLeft, int a, Color color){
		this(upperLeft,a);
		setColor(color);
	}
	public RectangleDraw(Point upperLeft, int a, Color color, Color areaColor){
		this(upperLeft, a, color);
		setAreaColor(areaColor);
	}
	public void moveTo(int x, int y){
		upperLeft.setX(x);
		upperLeft.setY(y);
	}

	public void moveFor(int x, int y){
		upperLeft.setX(upperLeft.getX()+x);
		upperLeft.setY(upperLeft.getY()+y);
	}

	public int scope(){
		return 4 * a;
	}
	public int surface(){
		return a * a;
	}
	//Tacka gore levo=(xGoreLevo,yGoreLevo), duzina stranice=duzinaStranice
	public String toString(){
		return "Shape:Square;UpperLeft:("+upperLeft.getX()+","+upperLeft.getY()+");sideLength:"+a + ";Edge color:" + getColor().getRed() +","+getColor().getGreen()
				+","+getColor().getBlue() + ";Surface color:" + getAreaColor().getRed()+"," + getAreaColor().getGreen() 
				+ "," + getAreaColor().getBlue() +";id:"+this.getId();	}

	public boolean equals(Object obj){
		if(obj instanceof RectangleDraw){
			RectangleDraw helper = (RectangleDraw) obj;
			if(upperLeft.equals(helper.upperLeft) && a == helper.a)
				return true;
			else
				return false;

		}
		else
			return false;
	}
	public Line diagonal(){
		return new Line(upperLeft, new Point(upperLeft.getX() + a,upperLeft.getY() + a));
	}

	public Point center(){
		return diagonal().lineMiddle();
	}
	public boolean contains(int x, int y) {
		if(this.getUpperLeft().getX()<=x 
				&& x<=(this.getUpperLeft().getX() + a)
				&& this.getUpperLeft().getY()<=y 
				&& y<=(this.getUpperLeft().getY() + a))
			return true;
		else 
			return false;

	}
	public void selected(Graphics g) {
		g.setColor(findColor("plava"));
		new Line(getUpperLeft(), new Point(getUpperLeft().getX()+a, getUpperLeft().getY())).selected(g);
		new Line(getUpperLeft(), new Point(getUpperLeft().getX(), getUpperLeft().getY()+a)).selected(g);
		new Line(new Point(getUpperLeft().getX()+a, getUpperLeft().getY()), diagonal().getpEnd()).selected(g);
		new Line(new Point(getUpperLeft().getX(), getUpperLeft().getY()+a), diagonal().getpEnd()).selected(g);
	}
	public void paint(Graphics g){
		g.setColor(getColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), a, a);
		fill(g);
		if(isSelected())
			selected(g);
	}
	
	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillRect(upperLeft.getX()+1, upperLeft.getY()+1, a-1, a-1);
		
	}
	
	public int compareTo(Object o) {
		if(o instanceof RectangleDraw){
			RectangleDraw helper = (RectangleDraw) o;
			return (int) (this.surface() - helper.surface());
		}
		else
			return 0;
	}
	public Point getUpperLeft() {
		return upperLeft;
	}
	
	public int getA() {
		return a;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	
	public void setA(int a) {
		this.a = a;
	}
		
}
