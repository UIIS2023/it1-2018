package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Rectangle;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

public class PnlDrawing extends JPanel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	Shape temp;
	Point startPoint;
	Point upperLeftPoint;
	private Color innerColorCircle;
	private Color innerColorRectangle;
	private Color borderColorRectangle;
	private Color borderColorCircle;
	private Color innerColorDonut;
	private Color borderColorDonut;
	private Color pointColor;
	private Color lineColor;

	private FrmDrawing frmDrawing;
	

	public PnlDrawing(FrmDrawing frmDrawing) {
		this.frmDrawing = frmDrawing;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				onMouseClicked(me);
			}
		});
	}

	private void onMouseClicked(MouseEvent me) {
		if (frmDrawing.getOption() == 1) {
			drawPoint(me);
		} else if (frmDrawing.getOption() == 2) {
			drawLine(me);
		} else if (frmDrawing.getOption() == 3) {
			drawCircle(me);
		} else if (frmDrawing.getOption() == 4) {
			drawRec(me);
		} else if (frmDrawing.getOption() == 5) {
			drawDonut(me);
		} else if (frmDrawing.getOption() == 6) {
			selectShape(me);
		}
		repaint();
	}

	public void drawPoint(MouseEvent me) {
		temp = new Point(me.getX(), me.getY(), false, Color.BLACK);
		shapes.add(temp);
		frmDrawing.getDlm().addElement(temp);
	}

	public void drawLine(MouseEvent me) {
		if (startPoint == null) {
			startPoint = new Point(me.getX(), me.getY());
		} else
		{
			temp = new Line(startPoint, new Point(me.getX(), me.getY()),false,Color.BLACK);
			shapes.add(temp);
			startPoint = null;
			frmDrawing.getDlm().addElement(temp);
		}
	}

	public void drawCircle(MouseEvent me) {
		Point center = new Point(me.getX(), me.getY());
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.setTxtX(Integer.toString(me.getX()));
		dlgCircle.setTxtY(Integer.toString(me.getY()));
		dlgCircle.Lock();
		dlgCircle.setVisible(true);
		try {	
		if (dlgCircle.getConfirm()) {
			
			int r = Integer.parseInt(dlgCircle.getTxtRadius().getText());
			if (r > 0) {
				
				temp = new Circle(center, r,false, Color.BLACK,Color.WHITE);
				if (dlgCircle.isColorConfirmation()) {
					temp.setColor(dlgCircle.fill);
					lineColor = dlgCircle.getFill();
				}
				else 
				{
					temp.setColor(lineColor);
				}	
				shapes.add(temp);
				frmDrawing.getDlm().addElement(temp);
			} else {
				JOptionPane.showMessageDialog(null, "the radius must be greater than zero");
			}
		}
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You must enter a number!");
		}
	}

	public void drawRec(MouseEvent me) {
		upperLeftPoint = new Point(me.getX(), me.getY());
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.setTxtX(Integer.toString(me.getX()));
		dlgRectangle.setTxtY(Integer.toString(me.getY()));
		dlgRectangle.Lock();
		dlgRectangle.setVisible(true);
		try {
		if (dlgRectangle.confirmation) {
			
			int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText().toString());
			int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText().toString());
			if (width > 0 && height > 0) {
				temp = new Rectangle(upperLeftPoint, width, height,false, Color.BLACK, Color.WHITE);
				if (dlgRectangle.isColorConfirmation()) {
					temp.setColor(dlgRectangle.fill);
					lineColor = dlgRectangle.getFill();
				}
				else 
				{
					temp.setColor(lineColor);
				}	
				shapes.add(temp);
				frmDrawing.getDlm().addElement(temp);
			} else {
				JOptionPane.showMessageDialog(null, "The width and height must be greather than zero");
			}
		}
	}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You must enter a number!");
		}
	}

	public void drawDonut(MouseEvent me) {
		Point center = new Point(me.getX(), me.getY());
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.setTxtX(Integer.toString(me.getX()));
		dlgDonut.setTxtY(Integer.toString(me.getY()));
		dlgDonut.Lock();
		dlgDonut.setVisible(true);
		try {
			if (dlgDonut.confirmation) {
				
				int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
				int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());
				if (innerRadius < radius) {
					
					temp = new Donut(center, radius, innerRadius,false,Color.BLACK,Color.WHITE);
					if (dlgDonut.isColorConfirmation()) {
						temp.setColor(dlgDonut.fill);
						lineColor = dlgDonut.getFill();
					}
					else 
					{
						temp.setColor(lineColor);
					}	
					shapes.add(temp);
					frmDrawing.getDlm().addElement(temp);
				} else {
					JOptionPane.showMessageDialog(null,
							"The  radius mast be greather than inner radius or inner radius mast be greather than zero");
				}
			}
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You must enter a number!");
		}
		
	}

	public void selectShape(MouseEvent me) {
		Shape select = null;
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			Shape shape = it.next();
			shape.setSelected(false);
			if (shape.contains(me.getX(), me.getY())) {
				select = shape;
			}
		}
		if (select != null) {
			select.setSelected(true);
		}
		repaint();
	}

	public void modifyShape() {
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i) instanceof Point) {
				try {
				if (shapes.get(i).isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					Point point = (Point) shapes.get(i);
					dlgPoint.getTxtX().setText(Integer.toString(point.getX()));
					dlgPoint.getTxtY().setText(Integer.toString(point.getY()));
					dlgPoint.setVisible(true);
					if (dlgPoint.confirmation) {
						
						int x = Integer.parseInt(dlgPoint.getTxtX().getText());
						int y = Integer.parseInt(dlgPoint.getTxtY().getText());
						
						Point temp = new Point(x, y, false, dlgPoint.getBtnColor().getBackground());

						if (dlgPoint.isColorConfirmation()) {
							temp.setColor(dlgPoint.fill);
							pointColor = dlgPoint.getFill();
						}
						else
						{
							temp.setColor(pointColor);
						}
						shapes.remove(shapes.get(i));
						shapes.add(temp);
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(temp);
						repaint();

					}
				}
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You must enter a number!");
				}
			}
			else if (shapes.get(i) instanceof Line) {
				if (shapes.get(i).isSelected()) {
					DlgLine dlgLine = new DlgLine();
					Line line = (Line) shapes.get(i);
					dlgLine.getTxtStartX().setText(Integer.toString(line.getStartPoint().getX()));
					dlgLine.getTxtStartY().setText(Integer.toString(line.getStartPoint().getY()));
					dlgLine.getTxtEndX().setText(Integer.toString(line.getEndPoint().getX()));
					dlgLine.getTxtEndY().setText(Integer.toString(line.getEndPoint().getY()));
					dlgLine.setVisible(true);
					if (dlgLine.confirmation) {
						try {
						int startX = Integer.parseInt(dlgLine.getTxtStartX().getText());
						int startY = Integer.parseInt(dlgLine.getTxtStartY().getText());
						int endX = Integer.parseInt(dlgLine.getTxtEndX().getText());
						int endY = Integer.parseInt(dlgLine.getTxtEndY().getText());
						Line temp = new Line(new Point(startX, startY), new Point(endX, endY));
						}
						catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, "You must enter a number!");
						}
						
						if (dlgLine.isColorConfirmation()) {
							temp.setColor(dlgLine.fill);
							lineColor = dlgLine.getFill();
						}
						else 
						{
							temp.setColor(lineColor);
						}
						shapes.add(temp);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(temp);
						repaint();
					}
				}
			}
			else if (shapes.get(i) instanceof Rectangle) {
				try {
				if (shapes.get(i).isSelected()) {
					
					DlgRectangle dlgRec = new DlgRectangle();
					Rectangle r = (Rectangle) shapes.get(i);
					dlgRec.getTxtHeight().setText(Integer.toString(r.getHeight()));
					dlgRec.getTxtWidth().setText(Integer.toString(r.getWidth()));
					dlgRec.getTxtX().setText(Integer.toString(r.getUpperLeftPoint().getX()));
					dlgRec.getTxtY().setText(Integer.toString(r.getUpperLeftPoint().getY()));
					dlgRec.setVisible(true);
					if (dlgRec.confirmation) {
						int x = Integer.parseInt(dlgRec.getTxtX().getText());
						int y = Integer.parseInt(dlgRec.getTxtY().getText());
						int width = Integer.parseInt(dlgRec.getTxtWidth().getText());
						int height = Integer.parseInt(dlgRec.getTxtHeight().getText());
						Rectangle temp = new Rectangle(new Point(x, y), width, height);
						if (dlgRec.isColorConfirmation()) {
							temp.setColor(dlgRec.fill);
							lineColor = dlgRec.getFill();
						}
						else 
						{
							temp.setColor(lineColor);
						}	
						shapes.add(temp);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().removeElementAt(i);
						frmDrawing.getDlm().addElement(temp);
						repaint();
					}
				}
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You must enter a number!");
				}
			}
			else if (shapes.get(i) instanceof Donut) {
				try {
				if (shapes.get(i).isSelected()) {

					DlgDonut dlgDonut = new DlgDonut();
					Donut donut = (Donut) shapes.get(i);

					dlgDonut.getTxtX().setText(Integer.toString(donut.getCenter().getX()));
					dlgDonut.getTxtY().setText(Integer.toString(donut.getCenter().getY()));
					dlgDonut.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
					dlgDonut.getTxtRadius().setText(Integer.toString(donut.getRadius()));
					dlgDonut.setVisible(true);

					if (dlgDonut.isConfirmation()) {
						int x = Integer.parseInt(dlgDonut.getTxtX().getText());
						int y = Integer.parseInt(dlgDonut.getTxtY().getText());
						int r = Integer.parseInt(dlgDonut.getTxtRadius().getText());
						int innerR = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
						Donut temp = new Donut(new Point(x, y), r, innerR);
						if (dlgDonut.isColorConfirmation()) {
							temp.setColor(dlgDonut.fill);
							lineColor = dlgDonut.getFill();
						}
						else 
						{
							temp.setColor(lineColor);
						}	
						
						shapes.add(temp);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(temp);
						repaint();

					}
				}
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You must enter a number!");
				}
			}
			else if (shapes.get(i) instanceof Circle) {
				try {
				if (shapes.get(i).isSelected()) {
					Circle c = (Circle) shapes.get(i);
					DlgCircle dlgCircle = new DlgCircle();

					dlgCircle.getTxtX().setText(Integer.toString(c.getCenter().getX()));
					dlgCircle.getTxtY().setText(Integer.toString(c.getCenter().getY()));
					dlgCircle.getTxtRadius().setText(Integer.toString(c.getRadius()));
					dlgCircle.setVisible(true);
					if (dlgCircle.getConfirm()) {
						int x = Integer.parseInt(dlgCircle.getTxtX().getText());
						int y = Integer.parseInt(dlgCircle.getTxtY().getText());
						int r = Integer.parseInt(dlgCircle.getTxtRadius().getText());
						Circle temp = new Circle(new Point(x, y), r);

						
						if (dlgCircle.isColorConfirmation()) {
							temp.setColor(dlgCircle.fill);
							lineColor = dlgCircle.getFill();
						}
						else 
						{
							temp.setColor(lineColor);
						}	
						shapes.add(temp);
						shapes.remove(shapes.get(i));
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement(temp);
						repaint();

					}
				}
			}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You must enter a number!");
				}
			}

		}
	}

	public void delete() {
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).isSelected()) {
				DlgDelete dlgDelete = new DlgDelete();
				dlgDelete.setVisible(true);
				if (dlgDelete.getConfirm()) {

					shapes.remove(shapes.get(i));
					frmDrawing.getDlm().removeElementAt(i);
					repaint();

				}

			}

		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> i = shapes.iterator();
		while (i.hasNext()) {
			temp = i.next();
			temp.draw(g);
		}

	}
}
