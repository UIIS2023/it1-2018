package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends AreaShape implements Moveable{
	protected Point center;
	protected int r;
	
	
	public Circle(){
		
	}
	public Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}
	public Circle(Point center, int r,String strColor){
		this(center, r);
		setStrColor(strColor);
	}
	public Circle(Point center,int r, String color, String areaColor){
		this(center,r,color);
		setStrAreaColor(areaColor);
	}
	public Circle(Point centar, int r, Color color){
		this(centar, r);
		setColor(color);
	}
	public Circle(Point centar,int r, Color color, Color areaColor){
		this(centar,r,color);
		setAreaColor(areaColor);
	}
	public void moveTo(int x, int y){
		center.moveTo(x, y);
	}
	public void moveFor(int x, int y){
		center.moveFor(x, y);
	}
	public double surface(){
		return r * r * Math.PI;
	}
	public double scope(){
		return 2 * r * Math.PI;
	}
	//Centar=(xCentra,yCentra), poluprecnik=r”.
	public String toString(){
		return "Shape:Circle;Center:("+center.getX()+","+center.getY()+");radius:"+r+";Edge color:" 
				+ getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue()
				+";Surface color:" + getAreaColor().getRed()+"," + getAreaColor().getGreen() + "," + getAreaColor().getBlue()
				+";id:"+this.getId();	}
	public boolean equals(Object obj){
		if(obj instanceof Circle){
			Circle pomocni = (Circle) obj;
			if(center.equals(pomocni.center) && r == pomocni.r)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean contains(int x, int y){
		Point placeOfClick = new Point(x, y);
		if(placeOfClick.udaljenost(center)<=r)
			return true;
		else
			return false;		
	}
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
		setSelected(true);
	}
	public void paint(Graphics g){
		g.setColor(getColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r+r);
		fill(g);
		if(isSelected())
			selected(g);
	}

	public void fill(Graphics g) {
		g.setColor(getAreaColor());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, r+r-2);
		
	}
	public int compareTo(Object o) {
		if(o instanceof Circle){
			Circle helper = (Circle) o;
			return (int) (this.r - helper.r);
		}
		else
			return 0;
	}
	
	
	public Point getCentar() {
		return getCenter();
	}
	public Point getCenter() {
		return center;
	}
	public int getR() {
		return r;
	}
	public void setCentar(Point center) {
		setCenter(center);
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public void setR(int r) {
		this.r = r;
	}
	
}
