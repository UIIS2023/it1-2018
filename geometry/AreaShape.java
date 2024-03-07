package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class AreaShape extends Shape {
	private String strAreaColor = "bela";
	private Color areaColor = Color.white;

	public abstract void fill(Graphics g);

	public String getStrAreaColor() {
		return strAreaColor;
	}

	public void setStrAreaColor(String strAreaColor) {
		this.strAreaColor = strAreaColor;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	

}
