package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import command.CmdAddShape;
import command.CmdBringToBackShape;
import command.CmdBringToFrontShape;
import command.CmdDeselectShape;
import command.CmdToFrontShape;
import command.CmdUpdateCircle;
import command.CmdUpdateDonut;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdRemoveShape;
import command.CmdSelectShape;
import command.CmdToBackShape;

import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;

import command.Command;
import command.CommandList;
import command.CommandParser;
import geometry.Circle;
import geometry.Donut;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.RectangleDraw;
import hexagon.Hexagon;
import observer.Observer;
import observer.Subject;
import strategy.ExportManager;
import strategy.ImportDrawingFromFile;
import strategy.ImportManager;
import strategy.SaveLog;
import strategy.SerializeShapesToFile;
import strategy.ExportManager;
import strategy.ImportDrawingFromFile;
import strategy.ImportLog;
import strategy.ImportManager;
import strategy.SaveLog;
import strategy.SerializeShapesToFile;
import wb.DlgCircle;
import wb.DlgHexagon;
import wb.DlgLine;
import wb.DlgPoint;
import wb.DlgRectangle;
import wb.DlgRecDrawDrawing;
import wb.DlgRecDraw;
import java.awt.Graphics;

public class DrawingController implements Subject {
	
	private JFileChooser jfc;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList<Observer> observersUndoRedo = new ArrayList<Observer>();
	
	private ExportManager manager;
	private ExportManager saveLog;
	private BufferedReader bufferLog;
	private ImportManager importer;
	private ArrayList<Shape> selectedShapes;
	
	private int helper = 0;
	private Shape current;
	private int count;
	private Point firstLinePoint;
	private int x,y;
	private int pos;
	private int pos2;
	private int countCom;
	int countUndo=0;
	
	int counter = 0;
	private int openCounter = 0;
	
	private CmdUpdatePoint temp;
	private CmdUpdateLine cmdLine;
	private CmdUpdateDonut cmdDonut;
	private CmdUpdateRectangle cmdRectangle;
	private CmdUpdateCircle cmdCircle;
	
	private DrawingModel model;
	private DrawingFrame frame;
		
	private DlgPoint dlgT = new DlgPoint();
	
	private CommandList cmdList = new CommandList();
		
	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		cmdList.setCommandListener(frame.getTxtA());
		saveLog = new ExportManager(new SaveLog());
		importer= new ImportManager(new ImportLog());
		selectedShapes = new ArrayList<Shape>();
	}
	
	public void undo() {
		countUndo++;
		cmdList.undo();
		
	}
	
	public void redo() {
		countUndo--;
		cmdList.redo();
		
	}
	
	public void paint(MouseEvent e){
		x=e.getX();
		y=e.getY();
		if(frame.getTglbtnPoint().isSelected()){
			try{
				if (frame.getColor() == null) {
					Color edgeColor = JColorChooser.showDialog(null, "Izaberite boju tacke:", Color.BLACK);		
					if (edgeColor != null){
						Point t1 = new Point(x,y,edgeColor);
						model.add(t1);
						countUndo=0;
						CmdAddShape temp = new CmdAddShape(model, t1, this);
						cmdList.add(temp);
						cmdList.setPosition(cmdList.getCommands().size()-1);
						cmdList.newCommand(true);
						frame.getTglbtnSelect().setEnabled(true);
						selectedShapes.remove(current);
						checkExecute();
						notifyAllObserverUndoRedo();
						for (int i = 0; i < model.getShapes().size(); i++) {
							if (model.getShapes().get(i).isSelected()) {
								model.getShapes().get(i).setSelected(false);
	
							}
						}
						notifyAllObservers();

					}
				}
				else {
					Point t1 = new Point(x,y,frame.getColor());
					model.add(t1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, t1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
			}
		}
		else if (frame.getTglbtnLine().isSelected()) {
			try{
				if (frame.getColor() == null) {
					if (count %2 ==0) {
						 firstLinePoint = new Point(x,y);
					}
					else{
						Color edgeColor=JColorChooser.showDialog(null, "Izaberite boju linije:",Color.black);
						if (edgeColor != null){
							Line l1 = new Line(firstLinePoint,new Point(x,y),edgeColor);
							model.add(l1);
							countUndo=0;
							CmdAddShape temp = new CmdAddShape(model, l1, this);
							cmdList.add(temp);
							cmdList.setPosition(cmdList.getCommands().size()-1);
							cmdList.newCommand(true);
							selectedShapes.remove(current);
							notifyAllObserverUndoRedo();
							for (int i = 0; i < model.getShapes().size(); i++) {
								if (model.getShapes().get(i).isSelected()) {
									model.getShapes().get(i).setSelected(false);
	
								}
							}
							checkExecute();
							notifyAllObservers();

						}	
					}
						count++;	
				}
				else {
					if (count %2 ==0) {
						 firstLinePoint = new Point(x,y);
					}
					else{
							Line l1 = new Line(firstLinePoint,new Point(x,y),frame.getColor());
							model.add(l1);
							countUndo=0;
							CmdAddShape temp = new CmdAddShape(model, l1, this);
							cmdList.add(temp);
							cmdList.setPosition(cmdList.getCommands().size()-1);
							cmdList.newCommand(true);
							selectedShapes.remove(current);
							notifyAllObserverUndoRedo();
							for (int i = 0; i < model.getShapes().size(); i++) {
								if (model.getShapes().get(i).isSelected()) {
									model.getShapes().get(i).setSelected(false);
	
								}
							}
							checkExecute();
							notifyAllObservers();

					}
						count++;	
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednsoti.");
			}
		}
		
		else if(frame.getTglbtnRectangle().isSelected()){
			DlgRectangle dlgPrav = new DlgRectangle();
			dlgPrav.getTxtX().setText(Integer.toString(x));
			dlgPrav.getTxtY().setText(Integer.toString(y));
			dlgPrav.getTxtX().setEditable(false);
			dlgPrav.getTxtY().setEditable(false);
			try{
				if (frame.getColor() == null && frame.getAreaColor() == null) {
					dlgPrav.setVisible(true);
					int width = Integer.parseInt(dlgPrav.getTxtWidth().getText());
					int height = Integer.parseInt(dlgPrav.getTxtHeight().getText());
					Color edgeColor = dlgPrav.getPnlColor().getBackground();
					Color areaColor = dlgPrav.getPnlAreaColor().getBackground();
					
					Rectangle p1 = new Rectangle(new Point(x,y), width, height, edgeColor, areaColor);
					model.add(p1);
					CmdAddShape temp = new CmdAddShape(model, p1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);
	
						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() == null){
					dlgPrav.getPnlColor().setBackground(frame.getColor());
					dlgPrav.setVisible(true);
					int width = Integer.parseInt(dlgPrav.getTxtWidth().getText());
					int height = Integer.parseInt(dlgPrav.getTxtHeight().getText());
					Color edgeColor = dlgPrav.getPnlColor().getBackground();
					Color areaColor = dlgPrav.getPnlAreaColor().getBackground();
					
					Rectangle p1 = new Rectangle(new Point(x,y), width, height, edgeColor, areaColor);
					model.add(p1);
					CmdAddShape temp = new CmdAddShape(model, p1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);				selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);
	
						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() == null && frame.getAreaColor() != null){
					dlgPrav.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgPrav.setVisible(true);
					int width = Integer.parseInt(dlgPrav.getTxtWidth().getText());
					int height = Integer.parseInt(dlgPrav.getTxtHeight().getText());
					Color edgeColor = dlgPrav.getPnlColor().getBackground();
					Color areaColor = dlgPrav.getPnlAreaColor().getBackground();
					
					Rectangle p1 = new Rectangle(new Point(x,y), width, height, edgeColor, areaColor);
					model.add(p1);
					CmdAddShape temp = new CmdAddShape(model, p1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);
	
						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() != null){
					dlgPrav.getPnlColor().setBackground(frame.getColor());
					dlgPrav.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgPrav.setVisible(true);
					int width = Integer.parseInt(dlgPrav.getTxtWidth().getText());
					int height = Integer.parseInt(dlgPrav.getTxtHeight().getText());
					Color edgeColor = dlgPrav.getPnlColor().getBackground();
					Color areaColor = dlgPrav.getPnlAreaColor().getBackground();
					
					Rectangle p1 = new Rectangle(new Point(x,y), width, height, edgeColor, areaColor);
					model.add(p1);
					CmdAddShape temp = new CmdAddShape(model, p1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);
	
						}
					}
					checkExecute();
					notifyAllObservers();

				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgPrav.getTxtX().setText("");
				dlgPrav.getTxtY().setText("");
				dlgPrav.getTxtWidth().setText("");
				dlgPrav.getTxtHeight().setText("");
				dlgPrav.getPnlColor().setBackground(Color.BLACK);
				dlgPrav.getPnlAreaColor().setBackground(Color.WHITE);
			}
		}
		else if(frame.getTglbtnCircle().isSelected()){
			DlgCircle dlgKrug = new DlgCircle();
			dlgKrug.getTxtX().setText(Integer.toString(x));
			dlgKrug.getTxtY().setText(Integer.toString(y));
			dlgKrug.getTxtX().setEditable(false);
			dlgKrug.getTxtY().setEditable(false);
			try{
				if (frame.getColor() == null && frame.getAreaColor() == null) {
				dlgKrug.setVisible(true);
				int r = Integer.parseInt(dlgKrug.getTxtR().getText());
				Color edgeColor = dlgKrug.getPnlColor().getBackground();
				Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
				
				Circle kr1 = new Circle(new Point(x,y),r,edgeColor,areaColor);
				model.add(kr1);
				countUndo=0;
				CmdAddShape temp = new CmdAddShape(model, kr1, this);
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObserverUndoRedo();
				for (int i = 0; i < model.getShapes().size(); i++) {
					if (model.getShapes().get(i).isSelected()) {
						model.getShapes().get(i).setSelected(false);

					}
				}
				checkExecute();
				notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() == null){
					dlgKrug.getPnlColor().setBackground(frame.getColor());
					dlgKrug.setVisible(true);
					int r = Integer.parseInt(dlgKrug.getTxtR().getText());
					Color edgeColor = dlgKrug.getPnlColor().getBackground();
					Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
					
					Circle kr1 = new Circle(new Point(x,y),r,edgeColor,areaColor);
					model.add(kr1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, kr1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					checkExecute();
					notifyAllObservers();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
				}
				else if (frame.getColor() == null && frame.getAreaColor() != null){
					dlgKrug.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgKrug.setVisible(true);
					int r = Integer.parseInt(dlgKrug.getTxtR().getText());
					Color edgeColor = dlgKrug.getPnlColor().getBackground();
					Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
					
					Circle kr1 = new Circle(new Point(x,y),r,edgeColor,areaColor);
					model.add(kr1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, kr1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() != null){
					dlgKrug.getPnlColor().setBackground(frame.getColor());
					dlgKrug.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgKrug.setVisible(true);
					int r = Integer.parseInt(dlgKrug.getTxtR().getText());
					Color edgeColor = dlgKrug.getPnlColor().getBackground();
					Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
					
					Circle kr1 = new Circle(new Point(x,y),r,edgeColor,areaColor);
					model.add(kr1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, kr1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgKrug.getTxtX().setText("");
				dlgKrug.getTxtY().setText("");
				dlgKrug.getTxtR().setText("");
				dlgKrug.getPnlColor().setBackground(Color.BLACK);
				dlgKrug.getPnlAreaColor().setBackground(Color.WHITE);
				}
		}
		else if(frame.getTglbtnHexagon().isSelected()){
			DlgHexagon dlgHex = new DlgHexagon();
			dlgHex.getTxtX().setText(Integer.toString(x));
			dlgHex.getTxtY().setText(Integer.toString(y));
			dlgHex.getTxtX().setEditable(false);
			dlgHex.getTxtY().setEditable(false);
			try{
				if (frame.getColor() == null && frame.getAreaColor() == null) {
				dlgHex.setVisible(true);
				int r = Integer.parseInt(dlgHex.getTxtR().getText());
				Color edgeColor = dlgHex.getPnlColor().getBackground();
				Color areaColor = dlgHex.getPnlAreaColor().getBackground();
				
				Shape hex1 = new HexagonAdapter(x,y,r,edgeColor,areaColor);
				model.add(hex1);
				countUndo=0;
				CmdAddShape temp = new CmdAddShape(model, hex1, this);
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObserverUndoRedo();
				for (int i = 0; i < model.getShapes().size(); i++) {
					if (model.getShapes().get(i).isSelected()) {
						model.getShapes().get(i).setSelected(false);

					}
				}
				checkExecute();
				notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() == null){
					dlgHex.getPnlColor().setBackground(frame.getColor());
					dlgHex.setVisible(true);
					int r = Integer.parseInt(dlgHex.getTxtR().getText());
					Color edgeColor = dlgHex.getPnlColor().getBackground();
					Color areaColor = dlgHex.getPnlAreaColor().getBackground();
					
					Shape hex1 = new HexagonAdapter(x,y,r,edgeColor,areaColor);
					model.add(hex1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, hex1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() == null && frame.getAreaColor() != null){
					dlgHex.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgHex.setVisible(true);
					int r = Integer.parseInt(dlgHex.getTxtR().getText());
					Color edgeColor = dlgHex.getPnlColor().getBackground();
					Color areaColor = dlgHex.getPnlAreaColor().getBackground();
					
					Shape hex1 = new HexagonAdapter(x,y,r,edgeColor,areaColor);
					model.add(hex1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, hex1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
				else if (frame.getColor() != null && frame.getAreaColor() != null){
					dlgHex.getPnlColor().setBackground(frame.getColor());
					dlgHex.getPnlAreaColor().setBackground(frame.getAreaColor());
					dlgHex.setVisible(true);
					int r = Integer.parseInt(dlgHex.getTxtR().getText());
					Color edgeColor = dlgHex.getPnlColor().getBackground();
					Color areaColor = dlgHex.getPnlAreaColor().getBackground();
					
					Shape hex1 = new HexagonAdapter(x,y,r,edgeColor,areaColor);
					model.add(hex1);
					countUndo=0;
					CmdAddShape temp = new CmdAddShape(model, hex1, this);
					cmdList.add(temp);
					cmdList.setPosition(cmdList.getCommands().size()-1);
					cmdList.newCommand(true);
					selectedShapes.remove(current);
					notifyAllObserverUndoRedo();
					for (int i = 0; i < model.getShapes().size(); i++) {
						if (model.getShapes().get(i).isSelected()) {
							model.getShapes().get(i).setSelected(false);

						}
					}
					checkExecute();
					notifyAllObservers();

				}
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgHex.getTxtX().setText("");
				dlgHex.getTxtY().setText("");
				dlgHex.getTxtR().setText("");
				dlgHex.getPnlColor().setBackground(Color.BLACK);
				dlgHex.getPnlAreaColor().setBackground(Color.WHITE);
				}
		}
		else if(frame.getTglbtnSelect().isSelected()){
			current = select(x,y);
			cmdList.newCommand(true);
		}
	}
	
	public Shape select(int x, int y){
		for (int i = model.getShapes().size()-1; i >= 0; i--){
			if(model.getShapes().get(i).isSelected() && model.getShapes().get(i).contains(x, y)) {
				model.getShapes().get(i).setSelected(false);
				CmdDeselectShape temp = new CmdDeselectShape(model, model.getShapes().get(i), this, selectedShapes);
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(model.getShapes().get(i));
				countUndo=0;
				notifyAllObservers();
				checkExecute();
				notifyAllObserverUndoRedo();
				frame.getView().repaint();
				return model.getShapes().get(i);
			}
			else if(model.getShapes().get(i).contains(x, y)){
				model.getShapes().get(i).setSelected(true);
				CmdSelectShape temp = new CmdSelectShape(model, model.getShapes().get(i), this, selectedShapes);
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				countUndo=0;
				notifyAllObservers();
				checkExecute();
				notifyAllObserverUndoRedo();
				frame.getView().repaint();
				return model.getShapes().get(i);
			}
			else {
				
			}
		}
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
			model.getShapes().get(i).setSelected(false);
			CmdDeselectShape temp = new CmdDeselectShape(model, model.getShapes().get(i), this, selectedShapes);
			cmdList.add(temp);
			cmdList.setPosition(cmdList.getCommands().size()-1);
			cmdList.newCommand(true);
			selectedShapes.clear();
			notifyAllObservers();
			checkExecute();
			notifyAllObserverUndoRedo();
			}
		}
		frame.getView().repaint();
		return null;
	}
	
	public void delete(){
		notifyAllObserverUndoRedo();
		ArrayList<Shape> delete = new ArrayList<Shape>();
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).isSelected()) {
				delete.add(model.getShapes().get(i));
			}
		}
			Object[] areYouSure = {"Da","Ne"};
			int izbor=JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Brisanje oblika", JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE, null, areYouSure, null);
			if (izbor==JOptionPane.OK_OPTION) {
				for (Shape o: model.getShapes()) {
					if (o.isSelected()) {
						Shape temp1 = o;
						//model.getShapes().remove(i);
						countUndo=0;
						CmdRemoveShape temp = new CmdRemoveShape(model, temp1, this);
						cmdList.add(temp);
						cmdList.setPosition(cmdList.getCommands().size()-1);
						cmdList.newCommand(true);
						selectedShapes.remove(current);
						notifyAllObserverUndoRedo();
						checkExecute();
						notifyAllObservers();
					}
				}
				model.getShapes().removeAll(delete);
				for (int i = 0; i < delete.size(); i++) {
					counter--;
				}
				for (Observer observer: observers) {
					observer.update(counter);
				}
				delete.clear();
				System.out.println(counter);
				frame.getView().repaint();
			}
	}
	
	public void bringToFront() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).equals(current)) {
				pos2 = i;
			}
		}
		Shape temp1 = current;
		CmdBringToFrontShape temp = new CmdBringToFrontShape(model, temp1, this, this.getPos2());
		temp.execute();
		for (Observer observer: observers) {
			observer.update(counter);
		}
		cmdList.add(temp);
		checkExecute();
		cmdList.setPosition(cmdList.getCommands().size()-1);
		cmdList.newCommand(true);
		notifyAllObserverUndoRedo();
	}
	public void toBack() {
		Shape temp1 = current;
		CmdToBackShape temp = new CmdToBackShape(model, temp1, this);
		temp.execute();
		checkExecute();
		for (Observer observer: observers) {
			observer.update(counter);
		}
		countUndo=0;
		notifyAllObserverUndoRedo();
		cmdList.add(temp);
		cmdList.setPosition(cmdList.getCommands().size()-1);
		cmdList.newCommand(true);
	}
	
	public void bringToBack() {
		for (int i = 0; i < model.getShapes().size(); i++) {
			if (model.getShapes().get(i).equals(current)) {
				pos = i;
			}
		}checkExecute();
		Shape temp1 = current;
		CmdBringToBackShape temp = new CmdBringToBackShape(model, temp1, this, this.getPos());
		temp.execute();
		for (Observer observer: observers) {
			observer.update(counter);
		}
		countUndo=0;
		cmdList.add(temp);
		cmdList.setPosition(cmdList.getCommands().size()-1);
		cmdList.newCommand(true);
		notifyAllObserverUndoRedo();
	}
	
	public void toFront() {
		Shape temp1 = current;
		CmdToFrontShape temp = new CmdToFrontShape(model, temp1, this);
		temp.execute();
		checkExecute();
		for (Observer observer: observers) {
			observer.update(counter);
		}
		countUndo=0;
		notifyAllObserverUndoRedo();
		notifyAllObservers();
		cmdList.add(temp);
		cmdList.setPosition(cmdList.getCommands().size()-1);
		cmdList.newCommand(true);
	}
	
	
	public void modify(){
		if (current instanceof Point) {
			notifyAllObserverUndoRedo();
			DlgPoint dlgT = new DlgPoint();
			Point helper = (Point) current;
			
			dlgT.getTxtX().setText(Integer.toString(helper.getX()));
			dlgT.getTxtY().setText(Integer.toString(helper.getY()));
			dlgT.getPnlColor().setBackground(helper.getColor());
			try{
				dlgT.setVisible(true);
				
				int novoX = Integer.parseInt(dlgT.getTxtX().getText());
				int novoY = Integer.parseInt(dlgT.getTxtY().getText());
				Color novaBoja= dlgT.getPnlColor().getBackground();
				
				/*pom.setX(novoX);
				pom.setY(novoY);
				pom.setBoja(novaBoja);*/
				helper.setSelected(true);
				Point newPoint = new Point(novoX, novoY, novaBoja);
				
				CmdUpdatePoint temp = new CmdUpdatePoint(helper, newPoint, this);
				temp.execute();
				countUndo=0;
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObserverUndoRedo();
				checkExecute();
				notifyAllObservers();
				
				frame.getView().repaint();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrenosti.");
				dlgT.getTxtX().setText("");
				dlgT.getTxtY().setText("");
				dlgT.getPnlColor().setBackground(Color.BLACK);
			}				
		}
		else if (current instanceof Line) {
			notifyAllObserverUndoRedo();
			DlgLine dlgL = new DlgLine();
			Line helper = (Line) current;
			dlgL.getTxtXBegin().setText(Integer.toString(helper.getpBegin().getX()));
			dlgL.getTxtYBegin().setText(Integer.toString(helper.getpBegin().getY()));
			dlgL.getTxtXEnd().setText(Integer.toString(helper.getpEnd().getX()));
			dlgL.getTxtYEnd().setText(Integer.toString(helper.getpEnd().getY()));
			dlgL.getPnlColor().setBackground(helper.getColor());
			try{
				dlgL.setVisible(true);
				
				int newXBegin = Integer.parseInt(dlgL.getTxtXBegin().getText());
				int newYBegin = Integer.parseInt(dlgL.getTxtYBegin().getText());
				int newXEnd = Integer.parseInt(dlgL.getTxtXEnd().getText());
				int newYEnd = Integer.parseInt(dlgL.getTxtYEnd().getText());
				Color color = dlgL.getPnlColor().getBackground();
				
				Line newLine = new Line(new Point(newXBegin, newYBegin), new Point (newXEnd, newYEnd), color);
				
				/*pom.settPocetna(new Point(novoXPoc,novoYPoc));
				pom.settKrajnja(new Point(novoXKraj, novoYKraj));
				pom.setBoja(boja);*/
				helper.setSelected(true);
				
				CmdUpdateLine temp = new CmdUpdateLine(helper, newLine,this);
				temp.execute();
				countUndo=0;
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObserverUndoRedo();
				checkExecute();
				notifyAllObservers();
				
				frame.getView().repaint();
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrenosti.");
				dlgL.getTxtXBegin().setText("");
				dlgL.getTxtYBegin().setText("");
				dlgL.getTxtXEnd().setText("");
				dlgL.getTxtYEnd().setText("");
				dlgL.getPnlColor().setBackground(Color.BLACK);
			}
			
		}
		
		else if(current instanceof Rectangle){
			DlgRectangle dlgPrav = new DlgRectangle();
			Rectangle pom = (Rectangle) current;
			dlgPrav.getTxtX().setText(Integer.toString(pom.getUpperLeft().getX()));
			dlgPrav.getTxtY().setText(Integer.toString(pom.getUpperLeft().getY()));
			dlgPrav.getTxtWidth().setText(Integer.toString(pom.getA()));
			dlgPrav.getTxtHeight().setText(Integer.toString(pom.getHeight()));
			dlgPrav.getPnlColor().setBackground(pom.getColor());
			dlgPrav.getPnlAreaColor().setBackground(pom.getAreaColor());
			try{
				dlgPrav.setVisible(true);
				int newX = Integer.parseInt(dlgPrav.getTxtX().getText());
				int newY = Integer.parseInt(dlgPrav.getTxtY().getText());
				int newLenght = Integer.parseInt(dlgPrav.getTxtWidth().getText());
				int newHeight = Integer.parseInt(dlgPrav.getTxtHeight().getText());
				Color edgeColor = dlgPrav.getPnlColor().getBackground();
				Color areaColor = dlgPrav.getPnlAreaColor().getBackground();
				
				/*pom.setGoreLevo(new Point(novoX,novoY));
				pom.setDuzinaStranice(novaDuzina);
				pom.setVisina(novaVisina);
				pom.setBoja(edgeColor);
				pom.setAreaColorsnjosti(areaColor);*/
				pom.setSelected(true);
				Rectangle newRec = new Rectangle(new Point(newX, newY), newLenght, newHeight, edgeColor, areaColor);
				
				CmdUpdateRectangle temp = new CmdUpdateRectangle(pom, newRec, this);
				temp.execute();
				countUndo=0;
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObserverUndoRedo();
				checkExecute();
				notifyAllObservers();
				
				frame.getView().repaint();
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgPrav.getTxtX().setText("");
				dlgPrav.getTxtY().setText("");
				dlgPrav.getTxtWidth().setText("");
				dlgPrav.getTxtHeight().setText("");
				dlgPrav.getPnlColor().setBackground(Color.WHITE);
				dlgPrav.getPnlAreaColor().setBackground(Color.WHITE);
			}
		}
		else if(current instanceof Circle){
			DlgCircle dlgKrug = new DlgCircle();
			Circle helper = (Circle) current;
			dlgKrug.getTxtX().setText(Integer.toString(helper.getCenter().getX()));
			dlgKrug.getTxtY().setText(Integer.toString(helper.getCenter().getY()));
			dlgKrug.getTxtR().setText(Integer.toString(helper.getR()));
			dlgKrug.getPnlColor().setBackground(helper.getColor());
			dlgKrug.getPnlAreaColor().setBackground(helper.getAreaColor());
			try{
				dlgKrug.setVisible(true);
				int newX = Integer.parseInt(dlgKrug.getTxtX().getText());
				int newY = Integer.parseInt(dlgKrug.getTxtY().getText());
				int newR = Integer.parseInt(dlgKrug.getTxtR().getText());
				Color edgeColor = dlgKrug.getPnlColor().getBackground();
				Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
				
				/*pom.setCentar(new Point(novoX,novoY));
				pom.setR(novoR);
				pom.setBoja(edgeColor);
				pom.setAreaColorsnjosti(areaColor);*/
				helper.setSelected(true);
				Circle circle = new Circle(new Point(newX, newY), newR, edgeColor, areaColor);
			
				CmdUpdateCircle temp = new CmdUpdateCircle(helper, circle, this);
				temp.execute();
				countUndo=0;
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				for (int i = 0; i < model.getShapes().size(); i++) {
					if (model.getShapes().get(i).isSelected()) {
						model.getShapes().get(i).setSelected(false);
					}
				}
				notifyAllObservers();
				checkExecute();
				notifyAllObserverUndoRedo();
				frame.getView().repaint();
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgKrug.getTxtX().setText("");
				dlgKrug.getTxtY().setText("");
				dlgKrug.getTxtR().setText("");
				dlgKrug.getPnlColor().setBackground(Color.BLACK);
				dlgKrug.getPnlAreaColor().setBackground(Color.WHITE);
			}
		}
		else if(current instanceof HexagonAdapter){
			DlgHexagon dlgKrug = new DlgHexagon();
			HexagonAdapter helper = (HexagonAdapter) current;
			dlgKrug.getTxtX().setText(Integer.toString(helper.getX()));
			dlgKrug.getTxtY().setText(Integer.toString(helper.getY()));
			dlgKrug.getTxtR().setText(Integer.toString(helper.getR()));
			dlgKrug.getPnlColor().setBackground(helper.getColor());
			dlgKrug.getPnlAreaColor().setBackground(helper.getAreaColor());
			try{
				dlgKrug.setVisible(true);
				int newX = Integer.parseInt(dlgKrug.getTxtX().getText());
				int newY = Integer.parseInt(dlgKrug.getTxtY().getText());
				int newR = Integer.parseInt(dlgKrug.getTxtR().getText());
				Color edgeColor = dlgKrug.getPnlColor().getBackground();
				Color areaColor = dlgKrug.getPnlAreaColor().getBackground();
				
				/*pom.setCentar(new Point(novoX,novoY));
				pom.setR(novoR);
				pom.setBoja(edgeColor);
				pom.setAreaColorsnjosti(areaColor);*/
				helper.setSelected(true);
				HexagonAdapter hex = new HexagonAdapter(newX, newY, newR, edgeColor, areaColor);
				CmdUpdateHexagon temp = new CmdUpdateHexagon(helper, hex, this);
				temp.execute();
				countUndo=0;
				cmdList.add(temp);
				cmdList.setPosition(cmdList.getCommands().size()-1);
				cmdList.newCommand(true);
				selectedShapes.remove(current);
				notifyAllObservers();
				notifyAllObserverUndoRedo();
				checkExecute();
				frame.getView().repaint();
				
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
				dlgKrug.getTxtX().setText("");
				dlgKrug.getTxtY().setText("");
				dlgKrug.getTxtR().setText("");
				dlgKrug.getPnlColor().setBackground(Color.BLACK);
				dlgKrug.getPnlAreaColor().setBackground(Color.WHITE);
			}
		}
	}
	
	
	
	
	
	public void openDrawing(){
		cmdList.setCommandHistory("");
		frame.getTxtA().setText("");
		openCounter = 0;
		model.getShapes().clear();
		cmdList.getCommands().clear();
		cmdList.getUndo().clear();
		selectedShapes.remove(current);
		countUndo=0;
		notifyAllObserverUndoRedo();
		ImportManager importer = new ImportManager(new ImportDrawingFromFile());
		String path="";
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("binary files (*.bin)", "bin");
		jfc.setFileFilter(filter);
		jfc.setAcceptAllFileFilterUsed(false);


		int returnValue = jfc.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION)
		{
			File selectedFile = jfc.getSelectedFile();
			System.out.println( selectedFile.getAbsolutePath());
			path = jfc.getSelectedFile().getAbsolutePath();
			
			ArrayList<Object> objects = (ArrayList<Object>)importer.importData(path);
			for (Shape s : (ArrayList<Shape>) objects.get(0)) {
				model.add(s);
				openCounter++;
			}
			frame.getBtnExecute().setEnabled(true);
		}
		frame.getView().repaint();	
			
	
	}
	
	


	
	
	public void saveDrawing(){
		
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(model.getShapes());
		
		manager = new ExportManager(new SerializeShapesToFile());
		saveLog = new ExportManager(new SaveLog());
		jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setAcceptAllFileFilterUsed(false);
		
		int returnValue = jfc.showSaveDialog(null);
		
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			String path = selectedFile.getAbsolutePath()+".bin";
			String logPath = selectedFile.getAbsolutePath()+".txt";
			
			
			if(path !=null)
			{
				manager.exportData(objects, path);
				saveLog.exportData(cmdList.getCommandHistory(), logPath);
			}
		
		}	
	}
	
	public void importLog() throws IOException {
		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("text files (*.txt)", "txt");
		fileChooser.setFileFilter(filter);
		//fileChooser.setCurrentDirectory(null);
		if(fileChooser.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();
			FileReader fileReader = (FileReader)importer.importData(filePath);
			System.out.println(fileReader);
			bufferLog = new BufferedReader(fileReader);
			this.model.getShapes().clear();
			cmdList.getCommands().clear();
			cmdList.setCommandHistory("");
			frame.getTxtA().setText("");
			checkEnable();
			checkExecute();
			}
		
	}
	
	public void executeCommand(){
		if(bufferLog==null) return;
		String commandString="";
		try{
			if((commandString = bufferLog.readLine())!= null){
				String typeOfCommand = commandString.split(";")[0];
				if (!typeOfCommand.equals("[Undo]")) {
					Command cmd = CommandParser.createCommandFromString(commandString, model,cmdList,this,selectedShapes);
					cmd.execute();
					cmdList.setCommandHistory(cmdList.getCommandHistory() + "\n" + cmd.toString());
					cmdList.logCommands();
					System.out.println(helper);
				}else{
					Command cmd = CommandParser.createCommandFromString(commandString, model,cmdList,this,selectedShapes);
					cmd.unexecute();
					cmdList.setCommandHistory(cmdList.getCommandHistory() + "\n" + cmd.toString());
					cmdList.logCommands();
				}
				
			}
			checkEnable();
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	

	public CommandList getCmdList() {
		return cmdList;
	}

	public void setCmdList(CommandList cmdList) {
		this.cmdList = cmdList;
	}

	public CmdUpdatePoint getTemp() {
		return temp;
	}

	public void setTemp(CmdUpdatePoint temp) {
		this.temp = temp;
	}

	public Shape getCurrent() {
		return current;
	}

	public void setCurrent(Shape current) {
		this.current = current;
	}

	public DrawingModel getModel() {
		return model;
	}

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public void checkEnable() {
		if (helper == model.getShapes().size()) {
			frame.getTglbtnModify().setEnabled(false);
			frame.getTglbtnDelete().setEnabled(false);
			frame.getTglbtnBringToBack().setEnabled(false);
			frame.getTglbtnBringToFront().setEnabled(false);
			frame.getTglbtnDelete().setEnabled(false);
			frame.getTglbtnToFront().setEnabled(false);
			frame.getTglbtnSelect().setEnabled(false);
			frame.getTglbtnToBack().setEnabled(false);
			frame.getBtnUndo().setEnabled(false);
			frame.getBtnRedo().setEnabled(false);
		}
	}
	
	public void checkExecute() {
		if (helper != model.getShapes().size()) {
			frame.getBtnExecute().setEnabled(false);
		}
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
		
		
	}
	
	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
		
	}

	@Override
	public void notifyAllObservers() {
		counter = 0;
			for(int i=0; i<model.getShapes().size(); i++)
			{
				if(model.getShapes().get(i).isSelected())
				{
					counter++;
				}
			}
			for (Observer observer: observers) {
				observer.update(counter);
			}
	}
	public void notifyAllObserverUndoRedo(){
		int switcher = 0;
		if (model.getShapes().size() == 0&& cmdList.getCommands().size() > 0) {
			
			switcher = 0;
		}
		else if (model.getShapes().size() == 0) {
			switcher = 2;
		}
		else if (model.getShapes().size() > 0 && model.getShapes().size() == openCounter  && 
				cmdList.getCommands().size() == 1) {
			
			switcher = 3;
		}
		else if (model.getShapes().size() > 0 && model.getShapes().size() == openCounter && 
				cmdList.getCommands().size() - cmdList.getUndo().size() > 1) {
			
			switcher = 4;
		}
		else if (model.getShapes().size() > 0 && model.getShapes().size() == openCounter&& 
				cmdList.getCommands().size() - cmdList.getUndo().size() > 1 && cmdList.getUndo().size() == 0) {
			switcher = 5;
		}
		else if (model.getShapes().size() > 0) {
			switcher = 1;
		}
		for (Observer observer: observers) {
			observer.updateUndoRedo(switcher, countUndo);
		}
	}

	@Override
	public void addObserverUndoRedo(Observer observer) {
		observersUndoRedo.add(observer);
		
	}

	@Override
	public void removeObserverUndoRedo(Observer observer) {
		// TODO Auto-generated method stub
		observersUndoRedo.remove(observer);
		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getCountCom() {
		return countCom;
	}

	public void setCountCom(int countCom) {
		this.countCom = countCom;
	}

	public int getCountUndo() {
		return countUndo;
	}

	public void setCountUndo(int countUndo) {
		this.countUndo = countUndo;
	}

	public int getPos2() {
		return pos2;
	}

	public void setPos2(int pos2) {
		this.pos2 = pos2;
	}

	public int getHelper() {
		return helper;
	}

	public void setHelper(int helper) {
		this.helper = helper;
	}

	

}
