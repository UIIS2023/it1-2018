package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends AreaShape implements Moveable{
	
	private Hexagon hex;
	
	public HexagonAdapter() {
		
	}
	
	public HexagonAdapter(Hexagon hex) {
		this.hex = hex;
	}
	
	public HexagonAdapter(Hexagon hex, Color color) {
		this(hex);
		setColor(color);
	}

	public HexagonAdapter(Hexagon hex, Color color, Color areaColor) {
		this(hex, color);
		setAreaColor(areaColor);
	}
	
	public String toString(){
		return "Shape:Hexagon;Center:("+hex.getX()+","+hex.getY()+");radius:"+hex.getR()+";Edge color:" 
				+ hex.getBorderColor().getRed() + "," + hex.getBorderColor().getGreen() + "," + hex.getBorderColor().getBlue()
				+";Surface color:" + hex.getAreaColor().getRed()+"," + hex.getAreaColor().getGreen() + "," +hex.getAreaColor().getBlue()
				+";id:"+this.getId();	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HexagonAdapter) {
			Hexagon hexaFromObj = ((HexagonAdapter) obj).getHex();
			return hex.getX() == hexaFromObj.getX() && hex.getY() == hexaFromObj.getY()
					&& hex.getR() == hexaFromObj.getR();
		}
		return false;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof HexagonAdapter) {
			return hex.getR() - ((HexagonAdapter) arg0).getHex().getR();
		}
		return 0;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		hex.paint(g);
		if(isSelected())
			selected(g);
	}

	@Override
	public void selected(Graphics g) {
		// TODO Auto-generated method stub
		int square = (int) (hex.getR()*Math.sqrt(3)/2);
		new Point(hex.getX()-hex.getR(), hex.getY()).selected(g);
		new Point(hex.getX()+hex.getR(), hex.getY()).selected(g);
		new Point(hex.getX()-hex.getR()/2, hex.getY()+square).selected(g);
		new Point(hex.getX()-hex.getR()/2, hex.getY()-square).selected(g);
		new Point(hex.getX()+hex.getR()/2, hex.getY()+square).selected(g);
		new Point(hex.getX()+hex.getR()/2, hex.getY()-square).selected(g);
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return hex.doesContain(x, y);
	}

	public void fill(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isSelected() {
		return hex.isSelected();
	}

	public void setSelected(boolean selected) {
		hex.setSelected(selected);
	}

	public Hexagon getHex() {
		return hex;
	}

	public void setHex(Hexagon hex) {
		this.hex = hex;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		hex.setX(x);
		hex.setY(y);
	}

	@Override
	public void moveFor(int x, int y) {
		// TODO Auto-generated method stub
		hex.setX(hex.getX() + x);
		hex.setY(hex.getY() + y);
	}
	
	public void setColor(Color color) {
		hex.setBorderColor(color);
	}
	
	public void setAreaColor(Color color) {
		hex.setAreaColor(color);
	}
	
	public Color getColor() {
		return hex.getBorderColor();
	}
	
	public Color getAreaColor() {
		return hex.getAreaColor();
	}
	
	public int getX() {
		
		int x = hex.getX();
		return x;
	}
	
	public int getY() {
		return hex.getY();
	}
	
	public int getR() {
		return hex.getR();
	}
	
	public void setX(int x) {
		hex.setX(x);
	}
	
	public void setY(int y) {
		hex.setY(y);
	}
	
	public void setR(int r) {
		hex.setR(r);
	}

}
