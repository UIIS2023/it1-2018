package wb;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import geometry.Shape;

public class Drawing extends JPanel{
	int x,y;
	private ArrayList <Shape> oblik;
	
	public Drawing() {
		super();
		oblik = new ArrayList<Shape>();	
	}
	
	public void crtaj(Shape o)
	{
		oblik.add(o);
		o.paint(this.getGraphics());
	}
	
	public void paint(Graphics g){
		super.paint(g);
		for (Shape o : oblik){
			o.paint(g);
		}
	}
	
	public Shape selektuj(int x, int y)
	{
		for (int i = oblik.size()-1; i >= 0; i--){
			if(oblik.get(i).contains(x, y)){
				oblik.get(i).setSelected(true);
				for (Shape o : oblik){
					if (oblik.get(i) != o)
						o.setSelected(false);
				}
				repaint();
				return oblik.get(i);
			}
		}
		for (Shape o: oblik)
			o.setSelected(false);
		repaint();
		return null;
	}
	
	public void izbrisi(Shape o)
	{
		oblik.remove(o);
		repaint();
	}
}