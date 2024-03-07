package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.RectangleDraw;
import geometry.Shape;
import java.awt.SystemColor;


public class Draw extends JFrame{

	private JFrame frame;
	private int x,y;
	private Drawing pnlCrtanje = new Drawing();
	private final ButtonGroup grupaDugmica = new ButtonGroup();
	private Shape trenutni;
	private int brojac;
	private Point prvaTackaLinije;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draw window = new Draw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Draw() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Crtanje-Dusan Milasinovic IT1/2018");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlOpcije = new JPanel();
		pnlOpcije.setBackground(UIManager.getColor("Table.gridColor"));
		frame.getContentPane().add(pnlOpcije, BorderLayout.WEST);
		GridBagLayout gbl_pnlOpcije = new GridBagLayout();
		gbl_pnlOpcije.columnWidths = new int[]{0, 0};
		gbl_pnlOpcije.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlOpcije.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlOpcije.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlOpcije.setLayout(gbl_pnlOpcije);
		
		JLabel lblOblici = new JLabel("Oblici:");
		lblOblici.setForeground(new Color(51, 51, 0));
		lblOblici.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblOblici.setBackground(new Color(102, 102, 102));
		GridBagConstraints gbc_lblOblici = new GridBagConstraints();
		gbc_lblOblici.insets = new Insets(0, 0, 5, 0);
		gbc_lblOblici.gridx = 0;
		gbc_lblOblici.gridy = 0;
		pnlOpcije.add(lblOblici, gbc_lblOblici);
		
		JToggleButton tglbtnTacka = new JToggleButton("Tacka");
		tglbtnTacka.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnTacka.setBackground(UIManager.getColor("activeCaption"));
		tglbtnTacka.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnTacka);
		GridBagConstraints gbc_tglbtnTacka = new GridBagConstraints();
		gbc_tglbtnTacka.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnTacka.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnTacka.gridx = 0;
		gbc_tglbtnTacka.gridy = 1;
		pnlOpcije.add(tglbtnTacka, gbc_tglbtnTacka);
		
		JToggleButton tglbtnLinija = new JToggleButton("Linija");
		tglbtnLinija.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnLinija.setBackground(UIManager.getColor("activeCaption"));
		tglbtnLinija.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnLinija);
		GridBagConstraints gbc_tglbtnLinija = new GridBagConstraints();
		gbc_tglbtnLinija.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLinija.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLinija.gridx = 0;
		gbc_tglbtnLinija.gridy = 2;
		pnlOpcije.add(tglbtnLinija, gbc_tglbtnLinija);
		
		JToggleButton tglbtnKvadrat = new JToggleButton("Kvadrat");
		tglbtnKvadrat.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnKvadrat.setBackground(UIManager.getColor("activeCaption"));
		tglbtnKvadrat.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnKvadrat);
		GridBagConstraints gbc_tglbtnKvadrat = new GridBagConstraints();
		gbc_tglbtnKvadrat.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKvadrat.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKvadrat.gridx = 0;
		gbc_tglbtnKvadrat.gridy = 3;
		pnlOpcije.add(tglbtnKvadrat, gbc_tglbtnKvadrat);
		
		JToggleButton tglbtnPravougaonik = new JToggleButton("Pravougaonik");
		tglbtnPravougaonik.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnPravougaonik.setBackground(UIManager.getColor("activeCaption"));
		tglbtnPravougaonik.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnPravougaonik);
		GridBagConstraints gbc_tglbtnPravougaonik = new GridBagConstraints();
		gbc_tglbtnPravougaonik.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPravougaonik.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPravougaonik.gridx = 0;
		gbc_tglbtnPravougaonik.gridy = 4;
		pnlOpcije.add(tglbtnPravougaonik, gbc_tglbtnPravougaonik);
		
		JToggleButton tglbtnKrug = new JToggleButton("Krug");
		tglbtnKrug.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnKrug.setBackground(UIManager.getColor("activeCaption"));
		tglbtnKrug.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnKrug);
		GridBagConstraints gbc_tglbtnKrug = new GridBagConstraints();
		gbc_tglbtnKrug.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKrug.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKrug.gridx = 0;
		gbc_tglbtnKrug.gridy = 5;
		pnlOpcije.add(tglbtnKrug, gbc_tglbtnKrug);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 6;
		pnlOpcije.add(separator, gbc_separator);
		
		JToggleButton tglbtnBrisanje = new JToggleButton("Brisanje");
		tglbtnBrisanje.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnBrisanje.setBackground(UIManager.getColor("activeCaption"));
		tglbtnBrisanje.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnBrisanje);
		tglbtnBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(trenutni!=null){
					Object[] daLiSteSigurni = {"Da","Ne"};
					int izbor=JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Brisanje oblika", JOptionPane.WARNING_MESSAGE,JOptionPane.PLAIN_MESSAGE, null, daLiSteSigurni, null);
					if (izbor==JOptionPane.OK_OPTION) {
						pnlCrtanje.izbrisi(trenutni);
						trenutni=null;
					}
				}
			}
		});
		
		JToggleButton tglbtnModifikacija = new JToggleButton("Modifikacija");
		tglbtnModifikacija.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnModifikacija.setBackground(UIManager.getColor("activeCaption"));
		tglbtnModifikacija.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnModifikacija);
		tglbtnModifikacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (trenutni instanceof Point) {
					DlgPoint dlgT = new DlgPoint();
					Point pom = (Point) trenutni;
					dlgT.getTxtX().setText(Integer.toString(pom.getX()));
					dlgT.getTxtY().setText(Integer.toString(pom.getY()));
					dlgT.getPnlColor().setBackground(pom.getColor());
					try{
						dlgT.setVisible(true);
						
						int novoX = Integer.parseInt(dlgT.getTxtX().getText());
						int novoY = Integer.parseInt(dlgT.getTxtY().getText());
						Color novaBoja= dlgT.getPnlColor().getBackground();
						
						pom.setX(novoX);
						pom.setY(novoY);
						pom.setColor(novaBoja);
						pom.setSelected(false);
						
						pnlCrtanje.repaint();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrenosti.");
						dlgT.getTxtX().setText("");
						dlgT.getTxtY().setText("");
						dlgT.getPnlColor().setBackground(Color.BLACK);
					}				
				}
				else if (trenutni instanceof Line) {
					DlgLine dlgL = new DlgLine();
					Line pom = (Line) trenutni;
					dlgL.getTxtXBegin().setText(Integer.toString(pom.getpBegin().getX()));
					dlgL.getTxtYBegin().setText(Integer.toString(pom.getpBegin().getY()));
					dlgL.getTxtXEnd().setText(Integer.toString(pom.getpEnd().getX()));
					dlgL.getTxtYEnd().setText(Integer.toString(pom.getpEnd().getY()));
					dlgL.getPnlColor().setBackground(pom.getColor());
					try{
						dlgL.setVisible(true);
						
						int novoXPoc = Integer.parseInt(dlgL.getTxtXBegin().getText());
						int novoYPoc = Integer.parseInt(dlgL.getTxtYBegin().getText());
						int novoXKraj = Integer.parseInt(dlgL.getTxtXEnd().getText());
						int novoYKraj = Integer.parseInt(dlgL.getTxtYEnd().getText());
						Color boja = dlgL.getPnlColor().getBackground();
						
						pom.setpBegin(new Point(novoXPoc,novoYPoc));
						pom.setpEnd(new Point(novoXKraj, novoYKraj));
						pom.setColor(boja);
						pom.setSelected(false);
						
						pnlCrtanje.repaint();
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
				else if(trenutni instanceof RectangleDraw && !(trenutni instanceof Rectangle)){
					DlgRecDrawDrawing dlgK = new DlgRecDrawDrawing();
					RectangleDraw pom = (RectangleDraw) trenutni;
					dlgK.getTxtX().setText(Integer.toString(pom.getUpperLeft().getX()));
					dlgK.getTxtY().setText(Integer.toString(pom.getUpperLeft().getY()));
					dlgK.getTxtA().setText(Integer.toString(pom.getA()));
					dlgK.getPnlColor().setBackground(pom.getColor());
					dlgK.getPnlAreaColor().setBackground(pom.getAreaColor());
					
					try{
					dlgK.setVisible(true);
					
					int novoX = Integer.parseInt(dlgK.getTxtX().getText());
					int novoY = Integer.parseInt(dlgK.getTxtY().getText());
					int novaDuzina = Integer.parseInt(dlgK.getTxtA().getText());
					Color bojaIvice = dlgK.getPnlColor().getBackground();
					Color bojaUnutra = dlgK.getPnlAreaColor().getBackground();
					
					pom.setUpperLeft(new Point(novoX,novoY));
					pom.setA(novaDuzina);
					pom.setColor(bojaIvice);
					pom.setAreaColor(bojaUnutra);
					pom.setSelected(false);
					
					pnlCrtanje.repaint();
		
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli dobre vrednosti.");
						dlgK.getTxtX().setText("");
						dlgK.getTxtY().setText("");
						dlgK.getTxtA().setText("");
						dlgK.getPnlColor().setBackground(Color.WHITE);
						dlgK.getPnlAreaColor().setBackground(Color.WHITE);
					}
				}
				else if(trenutni instanceof Rectangle){
					DlgRectangle dlgPrav = new DlgRectangle();
					Rectangle pom = (Rectangle) trenutni;
					dlgPrav.getTxtX().setText(Integer.toString(pom.getUpperLeft().getX()));
					dlgPrav.getTxtY().setText(Integer.toString(pom.getUpperLeft().getY()));
					dlgPrav.getTxtWidth().setText(Integer.toString(pom.getA()));
					dlgPrav.getTxtHeight().setText(Integer.toString(pom.getHeight()));
					dlgPrav.getPnlColor().setBackground(pom.getColor());
					dlgPrav.getPnlAreaColor().setBackground(pom.getAreaColor());
					try{
						dlgPrav.setVisible(true);
						int novoX = Integer.parseInt(dlgPrav.getTxtX().getText());
						int novoY = Integer.parseInt(dlgPrav.getTxtY().getText());
						int novaDuzina = Integer.parseInt(dlgPrav.getTxtWidth().getText());
						int novaVisina = Integer.parseInt(dlgPrav.getTxtHeight().getText());
						Color bojaIvice = dlgPrav.getPnlColor().getBackground();
						Color bojaUnutra = dlgPrav.getPnlAreaColor().getBackground();
						
						pom.setUpperLeft(new Point(novoX,novoY));
						pom.setA(novaDuzina);
						pom.setHeight(novaVisina);
						pom.setColor(bojaIvice);
						pom.setAreaColor(bojaUnutra);
						pom.setSelected(false);
						
						pnlCrtanje.repaint();
						
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
				else if(trenutni instanceof Circle ){
					DlgCircle dlgKrug = new DlgCircle();
					Circle pom = (Circle) trenutni;
					dlgKrug.getTxtX().setText(Integer.toString(pom.getCenter().getX()));
					dlgKrug.getTxtY().setText(Integer.toString(pom.getCenter().getY()));
					dlgKrug.getTxtR().setText(Integer.toString(pom.getR()));
					dlgKrug.getPnlColor().setBackground(pom.getColor());
					dlgKrug.getPnlAreaColor().setBackground(pom.getAreaColor());
					try{
						dlgKrug.setVisible(true);
						int novoX = Integer.parseInt(dlgKrug.getTxtX().getText());
						int novoY = Integer.parseInt(dlgKrug.getTxtY().getText());
						int novoR = Integer.parseInt(dlgKrug.getTxtR().getText());
						Color bojaIvice = dlgKrug.getPnlColor().getBackground();
						Color bojaUnutra = dlgKrug.getPnlAreaColor().getBackground();
						
						pom.setCenter(new Point(novoX,novoY));
						pom.setR(novoR);
						pom.setColor(bojaIvice);
						pom.setAreaColor(bojaUnutra);
						pom.setSelected(false);
						
						pnlCrtanje.repaint();
						
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
		});
		
		JToggleButton tglbtnDonut = new JToggleButton("    Donut    ");
		tglbtnDonut.setForeground(new Color(0, 153, 51));
		tglbtnDonut.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnKrug.setBackground(UIManager.getColor("activeCaption"));
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 0);
		grupaDugmica.add(tglbtnDonut);
		gbc_tglbtnDonut.gridx = 0;
		gbc_tglbtnDonut.gridy = 7;
		pnlOpcije.add(tglbtnDonut, gbc_tglbtnDonut);
		
		
		/*JToggleButton tglbtnKrug = new JToggleButton("Krug");
		tglbtnKrug.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnKrug.setBackground(UIManager.getColor("activeCaption"));
		tglbtnKrug.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnKrug);
		GridBagConstraints gbc_tglbtnKrug = new GridBagConstraints();
		gbc_tglbtnKrug.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKrug.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKrug.gridx = 0;
		gbc_tglbtnKrug.gridy = 5;
		pnlOpcije.add(tglbtnKrug, gbc_tglbtnKrug);*/
		
		
		
		
		
		
		JLabel lblOpcije = new JLabel("Opcije:");
		lblOpcije.setForeground(new Color(51, 51, 0));
		lblOpcije.setBackground(new Color(102, 102, 102));
		lblOpcije.setFont(new Font("DialogInput", Font.BOLD, 18));
		GridBagConstraints gbc_lblOpcije = new GridBagConstraints();
		gbc_lblOpcije.insets = new Insets(0, 0, 5, 0);
		gbc_lblOpcije.gridx = 0;
		gbc_lblOpcije.gridy = 8;
		pnlOpcije.add(lblOpcije, gbc_lblOpcije);
		
		JToggleButton tglbtnSelektovanje = new JToggleButton("Selektovanje");
		tglbtnSelektovanje.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnSelektovanje.setBackground(UIManager.getColor("activeCaption"));
		tglbtnSelektovanje.setForeground(new Color(0, 153, 51));
		grupaDugmica.add(tglbtnSelektovanje);
		GridBagConstraints gbc_tglbtnSelektovanje = new GridBagConstraints();
		gbc_tglbtnSelektovanje.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelektovanje.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelektovanje.gridx = 0;
		gbc_tglbtnSelektovanje.gridy = 9;
		pnlOpcije.add(tglbtnSelektovanje, gbc_tglbtnSelektovanje);
		GridBagConstraints gbc_tglbtnModifikacija = new GridBagConstraints();
		gbc_tglbtnModifikacija.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnModifikacija.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnModifikacija.gridx = 0;
		gbc_tglbtnModifikacija.gridy = 10;
		pnlOpcije.add(tglbtnModifikacija, gbc_tglbtnModifikacija);
		GridBagConstraints gbc_tglbtnBrisanje = new GridBagConstraints();
		gbc_tglbtnBrisanje.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnBrisanje.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnBrisanje.gridx = 0;
		gbc_tglbtnBrisanje.gridy = 11;
		pnlOpcije.add(tglbtnBrisanje, gbc_tglbtnBrisanje);
		
		
		pnlCrtanje.setBackground(UIManager.getColor("TextArea.disabledBackground"));
		pnlCrtanje.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				x=e.getX();
				y=e.getY();
				if(tglbtnTacka.isSelected()){
					try{
						Color bojaIvice = JColorChooser.showDialog(null, "Izaberite boju tacke:", Color.BLACK);		
						if (bojaIvice != null){
							Point t1 = new Point(x,y,bojaIvice);						
							pnlCrtanje.crtaj(t1);
						}					
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
					}
				}
				else if (tglbtnLinija.isSelected()) {
					try{
						if (brojac %2 ==0) {
							 prvaTackaLinije = new Point(x,y);
						}
						else{
							Color bojaIvice=JColorChooser.showDialog(null, "Izaberite boju linije:",Color.black);
							if (bojaIvice != null){
								Line l1 = new Line(prvaTackaLinije,new Point(x,y),bojaIvice);
								pnlCrtanje.crtaj(l1);
							}	
						}
							brojac++;						
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednsoti.");
					}
				}
				else if(tglbtnKvadrat.isSelected()){
					DlgRecDrawDrawing dlgK = new DlgRecDrawDrawing();
					dlgK.getTxtX().setText(Integer.toString(x));
					dlgK.getTxtX().setEditable(false);
					dlgK.getTxtY().setText(Integer.toString(y));
					dlgK.getTxtY().setEditable(false);
					try{
						dlgK.setVisible(true);
						int duzinaStranice = Integer.parseInt(dlgK.getTxtA().getText());
						Color bojaIvice = dlgK.getPnlColor().getBackground();
						Color bojaUnutra = dlgK.getPnlAreaColor().getBackground();
						
						RectangleDraw k1 = new RectangleDraw(new Point(x,y),duzinaStranice,bojaIvice,bojaUnutra);
						pnlCrtanje.crtaj(k1);
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli dobre vrednosti.");
						dlgK.getTxtX().setText("");
						dlgK.getTxtY().setText("");
						dlgK.getTxtA().setText("");
						dlgK.getPnlColor().setBackground(Color.BLACK);
						dlgK.getPnlAreaColor().setBackground(Color.WHITE);
					}
				}
				else if(tglbtnPravougaonik.isSelected()){
					DlgRectangle dlgPrav = new DlgRectangle();
					dlgPrav.getTxtX().setText(Integer.toString(x));
					dlgPrav.getTxtY().setText(Integer.toString(y));
					dlgPrav.getTxtX().setEditable(false);
					dlgPrav.getTxtY().setEditable(false);
					try{
						dlgPrav.setVisible(true);
						int sirina = Integer.parseInt(dlgPrav.getTxtWidth().getText());
						int visina = Integer.parseInt(dlgPrav.getTxtHeight().getText());
						Color bojaIvice = dlgPrav.getPnlColor().getBackground();
						Color bojaUnutra = dlgPrav.getPnlAreaColor().getBackground();
						
						Rectangle p1 = new Rectangle(new Point(x,y), sirina, visina, bojaIvice, bojaUnutra);
						pnlCrtanje.crtaj(p1);
						
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
				else if(tglbtnKrug.isSelected()||tglbtnDonut.isSelected()){
					DlgCircle dlgKrug = new DlgCircle();
					dlgKrug.getTxtX().setText(Integer.toString(x));
					dlgKrug.getTxtY().setText(Integer.toString(y));
					dlgKrug.getTxtX().setEditable(false);
					dlgKrug.getTxtY().setEditable(false);
					try{
						dlgKrug.setVisible(true);
						int r = Integer.parseInt(dlgKrug.getTxtR().getText());
						Color bojaIvice = dlgKrug.getPnlColor().getBackground();
						Color bojaUnutra = dlgKrug.getPnlAreaColor().getBackground();
						
						Circle kr1 = new Circle(new Point(x,y),r,bojaIvice,bojaUnutra);
						pnlCrtanje.crtaj(kr1);
						
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuce vrednosti.");
						dlgKrug.getTxtX().setText("");
						dlgKrug.getTxtY().setText("");
						dlgKrug.getTxtR().setText("");
						dlgKrug.getPnlColor().setBackground(Color.BLACK);
						dlgKrug.getPnlAreaColor().setBackground(Color.WHITE);
						}
				}
				else if(tglbtnSelektovanje.isSelected()){
					trenutni=pnlCrtanje.selektuj(x, y);
				}
			}
		});
		frame.getContentPane().add(pnlCrtanje, BorderLayout.CENTER);
	}
}
