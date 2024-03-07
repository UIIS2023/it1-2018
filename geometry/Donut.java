package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {

	private int innerRadius;
	//private int r;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
		
	}
	public Donut(Point center, int r,int ir,String strColor){
		this(center, r,ir);
		setStrColor(strColor);
	}
	public Donut(Point center,int r, int ir,String color, String areaColor){
		this(center,r,ir,color);
		setStrAreaColor(areaColor);
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		
		
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) { 
		this(center, radius, innerRadius, selected);
		setColor(color);
		
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) { 
		this(center, radius, innerRadius, selected, color);
		setAreaColor(innerColor);
		
	}
	
	
	
	/*public void fill(Graphics g) {
		g.setColor(getAreaColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
		/*
	}
		g.setColor(getAreaColor());
		super.fill(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	*/
		/*g.setColor(getAreaColor());
		super.fill(g);
		g.setColor(Color.WHITE);*/
		//g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
	//	g.setColor(getAreaColor());
		//g.setColor(Color.YELLOW);
	//	super.fill(g);
		//g.fillOval(getCenter().getX()-this.r+1, getCenter().getY()-this.r+1, 2*this.r-2, this.r+this.r-2);
//		g.setColor(Color.WHITE);
	//	g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	
		//g.fillOval(getCenter().getX()-super.getR()+1, getCenter().getY()-super.getR()+1, 2*super.getR()-2, super.getR()+super.getR()-2);
		//OVDE MORA FILL2 da popuni rupu da bude bela
		/*g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	
	}
	public void fill2(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	
	}
	
	public void paint(Graphics g){
		/*super.paint(g);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
	*//*
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setColor(this.getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		*/
		//g.setColor(getColor());
	/*/
		g.setColor(getColor());
		g.fillOval(getCenter().getX()-this.r+1, getCenter().getY()-this.r+1, 2*this.r-2, this.r+this.r-2);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setColor(getColor());
		g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
		
		
		/*
		g.setColor(getColor());
		
		//super.paint(g);
		g.drawOval(super.getCenter().getX()-super.getR(), super.getCentar().getY()-super.getR(),super.getR() * 2, super.getR() * 2);
		super.fill(g);
		
		g.drawOval(super.getCenter().getX()-this.innerRadius, super.getCentar().getY()-this.innerRadius,this.innerRadius * 2, this.innerRadius * 2);
		//fill2(g);
		//g.drawOval(getCenter().getX() - this.innerRadius, getCenter().getY() - this.innerRadius, this.innerRadius * 2, this.innerRadius * 2);
		
		
		if(isSelected())
			selected(g);
	}*/
	
	
	
	
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
		g.setColor(getColor());
		g.drawOval(center.getX() - innerRadius, center.getY() - innerRadius, innerRadius * 2, innerRadius * 2);
			
		g.setColor(Color.WHITE);
		g.fillOval(center.getX() - innerRadius, center.getY() - innerRadius, innerRadius * 2, innerRadius * 2);
		
		
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getR() == d.getR() &&
					this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	
	public int getInnerRadius() {
		return this.innerRadius;
	}
	public int getr()
	{
		return this.r;
	}
	
	public void setInnerRadius(int innerRadius) throws Exception{
		if(innerRadius>0)
		{
		this.innerRadius = innerRadius;
		}
		else
		{
			throw new NumberFormatException("innerRadius has'nnto be a value greater then 0............... not");
		}
	}
	
	public String toString(){
		return "Shape:Donut;Center:("+center.getX()+","+center.getY()+");radius:"+r+";Edge color:" 
				+ getColor().getRed() + "," + getColor().getGreen() + "," + getColor().getBlue()
				+";Surface color:" + getAreaColor().getRed()+"," + getAreaColor().getGreen() + "," + getAreaColor().getBlue()
				+";id:"+this.getId()+"; inner radius:"+innerRadius;	}
	/*public String toString() {
		return "Donut ," super.toString() + "; inner radius=" + innerRadius;
	}*/
	
}
