package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import command.CommandList;
import geometry.Point;
import geometry.Shape;
import observer.Observer;
import observer.Subject;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class DrawingFrame extends JFrame implements Observer {
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();	
	private JPanel pnlOptions = new JPanel();
	private JLabel lblShapes = new JLabel("Shapes:");
	private JToggleButton tglbtnPoint = new JToggleButton("Point");
	private JToggleButton tglbtnLine = new JToggleButton("Line");
	private JToggleButton tglbtnDonut_1 = new JToggleButton("Donut");
	private JToggleButton tglbtnRectangle = new JToggleButton("Rectnagle");
	private JToggleButton tglbtnCircle = new JToggleButton("Circle");
	
	private JSeparator separator = new JSeparator();
	private JLabel lblOptions = new JLabel("Oprtons:");
	private JToggleButton tglbtnSelect = new JToggleButton("Select");
	private JToggleButton tglbtnModify = new JToggleButton("Modify");
	private JToggleButton tglbtnDelete = new JToggleButton("Delete");
	private JButton btnExecute_1;
	private JScrollPane scrollPane = new JScrollPane();
	private JTextArea txtA = new JTextArea();
	
	private JButton btnUndo = new JButton("UNDO");
	private JButton btnRedo = new JButton("REDO");
	//private int countUndo = 0;
	
	private JButton btnImportLog = new JButton("Import Log");
	private JButton btnExecute = new JButton("Execute");
	
	private Color color;
	private Color areaColor;
	
	private JPanel pnlInsideColor = new JPanel();
	
	private JToggleButton tglbtnBringToFront = new JToggleButton("Bring To Front");
	private JToggleButton tglbtnBringToBack = new JToggleButton("Bring To Back");
	private JToggleButton tglbtnToBack = new JToggleButton("To Back");
	private JToggleButton tglbtnToFront = new JToggleButton("To Front");
	
	private JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	
	private CommandList cmdList = new CommandList();
	
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	
	public DrawingView getView() {
		return view;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	
	
	public DrawingFrame() {
		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(Color.WHITE);
		setTitle("Crtanje-Dusan Milasinovic IT1/2018");
		setBounds(5, 5, 1200, 830);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.openDrawing();
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveDrawing();
			}
		});
		mnFile.add(mntmSave);
		initialize();
	}
	
	private void initialize() {
		pnlOptions = new JPanel();
		pnlOptions.setBackground(UIManager.getColor("Table.gridColor"));
		getContentPane().add(pnlOptions, BorderLayout.WEST);
		GridBagLayout gbl_pnlOpcije = new GridBagLayout();
		gbl_pnlOpcije.columnWidths = new int[]{0, 0};
		gbl_pnlOpcije.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlOpcije.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlOpcije.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlOptions.setLayout(gbl_pnlOpcije);
		
		lblShapes = new JLabel("Oblici:");
		lblShapes.setForeground(new Color(51, 51, 0));
		lblShapes.setFont(new Font("DialogInput", Font.BOLD, 18));
		lblShapes.setBackground(new Color(102, 102, 102));
		GridBagConstraints gbc_lblOblici = new GridBagConstraints();
		gbc_lblOblici.insets = new Insets(0, 0, 5, 0);
		gbc_lblOblici.gridx = 0;
		gbc_lblOblici.gridy = 0;
		pnlOptions.add(lblShapes, gbc_lblOblici);
		
		tglbtnLine = new JToggleButton("Linija");
		tglbtnLine.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnLine.setBackground(UIManager.getColor("activeCaption"));
		tglbtnLine.setForeground(new Color(0, 153, 51));
		buttonGroup.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLinija = new GridBagConstraints();
		gbc_tglbtnLinija.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLinija.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnLinija.gridx = 0;
		gbc_tglbtnLinija.gridy = 3;
		pnlOptions.add(tglbtnLine, gbc_tglbtnLinija);
		
		tglbtnRectangle = new JToggleButton("Pravougaonik");
		tglbtnRectangle.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnRectangle.setBackground(UIManager.getColor("activeCaption"));
		tglbtnRectangle.setForeground(new Color(0, 153, 51));
		buttonGroup.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnPravougaonik = new GridBagConstraints();
		gbc_tglbtnPravougaonik.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPravougaonik.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnPravougaonik.gridx = 0;
		gbc_tglbtnPravougaonik.gridy = 4;
		pnlOptions.add(tglbtnRectangle, gbc_tglbtnPravougaonik);
		
		tglbtnHexagon = new JToggleButton("Sestougao");
		tglbtnHexagon.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnHexagon.setForeground(new Color(34, 139, 34));
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.fill = GridBagConstraints.BOTH;
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 5;
		pnlOptions.add(tglbtnHexagon, gbc_tglbtnHexagon);
		buttonGroup.add(tglbtnHexagon);
		
		tglbtnCircle = new JToggleButton("Krug");
		tglbtnCircle.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnCircle.setBackground(UIManager.getColor("activeCaption"));
		tglbtnCircle.setForeground(new Color(0, 153, 51));
		buttonGroup.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnKrug = new GridBagConstraints();
		gbc_tglbtnKrug.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnKrug.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnKrug.gridx = 0;
		gbc_tglbtnKrug.gridy = 6;
		pnlOptions.add(tglbtnCircle, gbc_tglbtnKrug);
		
		tglbtnDonut_1 = new JToggleButton("    Donut    ");
		tglbtnDonut_1.setForeground(new Color(0, 153, 51));
		tglbtnDonut_1.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnDonut_1.setBackground(UIManager.getColor("activeCaption"));
		GridBagConstraints gbc_tglbtnDonut_1 = new GridBagConstraints();
		gbc_tglbtnDonut_1.insets = new Insets(0, 0, 5, 0);
		buttonGroup.add(tglbtnDonut_1);
		gbc_tglbtnDonut_1.gridx = 0;
		gbc_tglbtnDonut_1.gridy = 7;
		pnlOptions.add(tglbtnDonut_1, gbc_tglbtnDonut_1);
		
		
		
		btnRedo = new JButton("REDO");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
		
		btnUndo = new JButton("UNDO");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		
		tglbtnToBack = new JToggleButton("To Back");
		tglbtnToBack.setEnabled(false);
		tglbtnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
		tglbtnToFront = new JToggleButton("To Front");
		tglbtnToFront.setEnabled(false);
		tglbtnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		tglbtnBringToBack = new JToggleButton("Bring To Back");
		tglbtnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		tglbtnBringToFront = new JToggleButton("Bring To Front");
		tglbtnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.bringToFront();
			}
		});
		
		btnExecute_1 = new JButton("Execute");
		btnExecute_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.executeCommand();
			}
		});
		
		JButton btnImportLog_1 = new JButton("Import Log");
		btnImportLog_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.importLog();
					btnExecute_1.setEnabled(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		tglbtnPoint = new JToggleButton("Tacka");
		tglbtnPoint.setFont(new Font("DialogInput", Font.BOLD, 16));
		tglbtnPoint.setBackground(UIManager.getColor("activeCaption"));
		tglbtnPoint.setForeground(new Color(0, 153, 51));
		buttonGroup.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnTacka = new GridBagConstraints();
		gbc_tglbtnTacka.gridheight = 2;
		gbc_tglbtnTacka.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnTacka.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnTacka.gridx = 0;
		gbc_tglbtnTacka.gridy = 8;
		pnlOptions.add(tglbtnPoint, gbc_tglbtnTacka);
		
		
		
		
		lblOptions = new JLabel("Opcije:");
		lblOptions.setForeground(new Color(51, 51, 0));
		lblOptions.setBackground(new Color(102, 102, 102));
		lblOptions.setFont(new Font("DialogInput", Font.BOLD, 18));
		GridBagConstraints gbc_lblOpcije = new GridBagConstraints();
		gbc_lblOpcije.insets = new Insets(0, 0, 5, 0);
		gbc_lblOpcije.gridx = 0;
		gbc_lblOpcije.gridy = 10;
		pnlOptions.add(lblOptions, gbc_lblOpcije);
		
		tglbtnSelect = new JToggleButton("Selektovanje");
		tglbtnSelect.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnSelect.setBackground(UIManager.getColor("activeCaption"));
		tglbtnSelect.setForeground(new Color(0, 153, 51));
		buttonGroup.add(tglbtnSelect);
		GridBagConstraints gbc_tglbtnSelektovanje = new GridBagConstraints();
		gbc_tglbtnSelektovanje.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnSelektovanje.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnSelektovanje.gridx = 0;
		gbc_tglbtnSelektovanje.gridy = 11;
		pnlOptions.add(tglbtnSelect, gbc_tglbtnSelektovanje);
		
		tglbtnModify = new JToggleButton("Modifikacija");
		tglbtnModify.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnModify.setBackground(UIManager.getColor("activeCaption"));
		tglbtnModify.setForeground(new Color(0, 153, 51));
		tglbtnModify.setEnabled(false);
		buttonGroup.add(tglbtnModify);
		GridBagConstraints gbc_tglbtnModifikacija = new GridBagConstraints();
		gbc_tglbtnModifikacija.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnModifikacija.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnModifikacija.gridx = 0;
		gbc_tglbtnModifikacija.gridy = 12;
		pnlOptions.add(tglbtnModify, gbc_tglbtnModifikacija);
		tglbtnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.modify();
				}
			});
		
		tglbtnDelete = new JToggleButton("Brisanje");
		tglbtnDelete.setFont(new Font("DialogInput", Font.BOLD, 18));
		tglbtnDelete.setBackground(UIManager.getColor("activeCaption"));
		tglbtnDelete.setForeground(new Color(0, 153, 51));
		tglbtnDelete.setEnabled(false);
		buttonGroup.add(tglbtnDelete);
		GridBagConstraints gbc_tglbtnBrisanje = new GridBagConstraints();
		gbc_tglbtnBrisanje.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnBrisanje.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnBrisanje.gridx = 0;
		gbc_tglbtnBrisanje.gridy = 13;
		pnlOptions.add(tglbtnDelete, gbc_tglbtnBrisanje);
		
				
				tglbtnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.delete();
						}
					});
		GridBagConstraints gbc_btnImportLog_1 = new GridBagConstraints();
		gbc_btnImportLog_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnImportLog_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnImportLog_1.gridx = 0;
		gbc_btnImportLog_1.gridy = 15;
		pnlOptions.add(btnImportLog_1, gbc_btnImportLog_1);
		btnExecute_1.setEnabled(false);
		GridBagConstraints gbc_btnExecute_1 = new GridBagConstraints();
		gbc_btnExecute_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExecute_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnExecute_1.gridx = 0;
		gbc_btnExecute_1.gridy = 16;
		pnlOptions.add(btnExecute_1, gbc_btnExecute_1);
		
		
		GridBagConstraints gbc_tglbtnBringToFront = new GridBagConstraints();
		gbc_tglbtnBringToFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnBringToFront.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnBringToFront.gridx = 0;
		gbc_tglbtnBringToFront.gridy = 17;
		pnlOptions.add(tglbtnBringToFront, gbc_tglbtnBringToFront);
		buttonGroup.add(tglbtnBringToFront);
		tglbtnBringToFront.setEnabled(false);
		GridBagConstraints gbc_tglbtnBringToBack = new GridBagConstraints();
		gbc_tglbtnBringToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnBringToBack.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnBringToBack.gridx = 0;
		gbc_tglbtnBringToBack.gridy = 18;
		pnlOptions.add(tglbtnBringToBack, gbc_tglbtnBringToBack);
		buttonGroup.add(tglbtnBringToBack);
		tglbtnBringToBack.setEnabled(false);
		GridBagConstraints gbc_tglbtnToFront = new GridBagConstraints();
		gbc_tglbtnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnToFront.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnToFront.gridx = 0;
		gbc_tglbtnToFront.gridy = 19;
		pnlOptions.add(tglbtnToFront, gbc_tglbtnToFront);
		buttonGroup.add(tglbtnToFront);
		GridBagConstraints gbc_tglbtnToBack = new GridBagConstraints();
		gbc_tglbtnToBack.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnToBack.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnToBack.gridx = 0;
		gbc_tglbtnToBack.gridy = 20;
		pnlOptions.add(tglbtnToBack, gbc_tglbtnToBack);
		buttonGroup.add(tglbtnToBack);
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUndo.insets = new Insets(0, 0, 5, 0);
		gbc_btnUndo.gridx = 0;
		gbc_btnUndo.gridy = 22;
		pnlOptions.add(btnUndo, gbc_btnUndo);
		GridBagConstraints gbc_btnRedo = new GridBagConstraints();
		gbc_btnRedo.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRedo.insets = new Insets(0, 0, 5, 0);
		gbc_btnRedo.gridx = 0;
		gbc_btnRedo.gridy = 23;
		pnlOptions.add(btnRedo, gbc_btnRedo);
		JPanel pnlColor = new JPanel();
		getContentPane().add(pnlColor, BorderLayout.EAST);
		pnlColor.setPreferredSize(new Dimension(50, 50));
		
		JPanel pnlInsideColor_1 = new JPanel();
		pnlInsideColor_1.setPreferredSize(new Dimension(100, 100));
		pnlInsideColor_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				color = JColorChooser.showDialog(null,"Izaberite boju ivice:", pnlInsideColor_1.getBackground());
				pnlInsideColor_1.setBackground(color);
			}
		});
		
		JPanel pnlAreaColor = new JPanel();
		pnlAreaColor.setMaximumSize(pnlColor.getPreferredSize());
		pnlAreaColor.setPreferredSize(new Dimension(100, 100));
		pnlAreaColor.setBackground(SystemColor.activeCaption);
		pnlAreaColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				areaColor = JColorChooser.showDialog(null,"Izaberite boju ivice:", pnlAreaColor.getBackground());
				pnlAreaColor.setBackground(areaColor);
			}
		});
		pnlInsideColor_1.setFont(new Font("DialogInput", Font.BOLD, 18));
		pnlInsideColor_1.setBackground(SystemColor.activeCaption);
		pnlInsideColor_1.setForeground(new Color(0, 153, 51));
		
		JLabel lblUnutra = new JLabel("Unutra:");
		
		JLabel lblLinija = new JLabel(" Linija:");
		
		JLabel lblBoje = new JLabel("Boje");
		GroupLayout gl_pnlColor = new GroupLayout(pnlColor);
		gl_pnlColor.setHorizontalGroup(
			gl_pnlColor.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlColor.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBoje)
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(gl_pnlColor.createSequentialGroup()
					.addComponent(pnlAreaColor, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_pnlColor.createSequentialGroup()
					.addComponent(pnlInsideColor_1, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(Alignment.LEADING, gl_pnlColor.createSequentialGroup()
					.addComponent(lblLinija)
					.addContainerGap())
				.addGroup(gl_pnlColor.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblUnutra)
					.addContainerGap())
		);
		gl_pnlColor.setVerticalGroup(
			gl_pnlColor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlColor.createSequentialGroup()
					.addComponent(pnlInsideColor_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLinija)
					.addGap(193)
					.addComponent(lblBoje)
					.addGap(232)
					.addComponent(lblUnutra)
					.addGap(18)
					.addComponent(pnlAreaColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pnlColor.setLayout(gl_pnlColor);
		
		JPanel panel = new JPanel();
		//pnlColor.add(panel, BorderLayout.CENTER);
		panel.setPreferredSize(new Dimension(50, 50));
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblArea = new JLabel("   EDGE");
		panel.add(lblArea, BorderLayout.NORTH);
		
		JLabel lblEdge = new JLabel(" COLOR");
		panel.add(lblEdge, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblColor = new JLabel(" COLOR");
		panel_1.add(lblColor, BorderLayout.NORTH);
		
		JLabel lblColor_1 = new JLabel("   AREA");
		panel_1.add(lblColor_1, BorderLayout.SOUTH);
		
		JPanel pnlLog = new JPanel();
		getContentPane().add(pnlLog, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlLog= new GridBagLayout();
		
		gbl_pnlLog.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlLog.rowWeights = new double[]{0.0};
		pnlLog.setLayout(gbl_pnlLog);
		txtA = new JTextArea(4,107);
		txtA.setBackground(SystemColor.inactiveCaptionBorder);
		txtA.setLineWrap(true);
		
		scrollPane = new JScrollPane(txtA);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		pnlLog.add(scrollPane, gbc_scrollPane);
		
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				controller.paint(e);
			}
		});
		
	}

	public JPanel getPnlOptions() {
		return pnlOptions;
	}

	public void setPnlOptions(JPanel pnlOpcije) {
		this.pnlOptions = pnlOpcije;
	}

	public JLabel getLblShapes() {
		return lblShapes;
	}

	public void setLblShapes(JLabel lblOblici) {
		this.lblShapes = lblOblici;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnTacka) {
		this.tglbtnPoint = tglbtnTacka;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLinija) {
		this.tglbtnLine = tglbtnLinija;
	}

	

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnPravougaonik) {
		this.tglbtnRectangle = tglbtnPravougaonik;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnKrug) {
		this.tglbtnCircle = tglbtnKrug;
	}
	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut_1;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut_1) {
		this.tglbtnDonut_1 = tglbtnDonut_1;
	}

	public JSeparator getSeparator() {
		return separator;
	}

	public void setSeparator(JSeparator separator) {
		this.separator = separator;
	}

	public JLabel getLblOptions() {
		return lblOptions;
	}

	public void setLblOptions(JLabel lblOpcije) {
		this.lblOptions = lblOpcije;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnSelect(JToggleButton tglbtnSelektovanje) {
		this.tglbtnSelect = tglbtnSelektovanje;
	}

	public JToggleButton getTglbtnModify() {
		return tglbtnModify;
	}

	public void setTglbtnModify(JToggleButton tglbtnModifikacija) {
		this.tglbtnModify = tglbtnModifikacija;
	}

	public JToggleButton getTglbtnDelete() {
		return tglbtnDelete;
	}

	public void setTglbtnDelete(JToggleButton tglbtnBrisanje) {
		this.tglbtnDelete = tglbtnBrisanje;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public DrawingController getController() {
		return controller;
	}

	public void setView(DrawingView view) {
		this.view = view;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	@Override
	public void update(int counter) {
		// TODO Auto-generated method stub
		if(counter==1)
		{
			tglbtnModify.setEnabled(true);
			tglbtnDelete.setEnabled(true);
			//System.out.println(controller.getModel().getShapes().indexOf(controller.getTrenutni()));
			/*if (controller.getModel().getShapes().indexOf(controller.getTrenutni()) != controller.getModel().getShapes().size()-1 &&
					controller.getModel().getShapes().indexOf(controller.getTrenutni()) != 0) {
				tglbtnBringToFront.setEnabled(true);
				tglbtnBringToBack.setEnabled(true);
			}*/
			for (int i = 0; i < controller.getModel().getShapes().size(); i++) {
				if (controller.getModel().getShapes().get(i).isSelected()) {
					if (i != controller.getModel().getShapes().size()-1 && i != 0) {
						
						tglbtnBringToFront.setEnabled(true);
						tglbtnBringToBack.setEnabled(true);
						tglbtnToFront.setEnabled(true);
						tglbtnToBack.setEnabled(true);
					}
					else if (i != 0 && i == controller.getModel().getShapes().size()-1) {
						tglbtnBringToFront.setEnabled(false);
						tglbtnBringToBack.setEnabled(true);
						tglbtnToBack.setEnabled(true);
						tglbtnToFront.setEnabled(false);
					}
					else if (i == 0 && i != controller.getModel().getShapes().size()-1) {
						tglbtnBringToFront.setEnabled(true);
						tglbtnBringToBack.setEnabled(false);
						tglbtnToFront.setEnabled(true);
						tglbtnToBack.setEnabled(false);
					}
				}
			}
			
		}
		else if(counter > 1){
			tglbtnDelete.setEnabled(true);
			tglbtnModify.setEnabled(false);
			tglbtnBringToBack.setEnabled(false);
			tglbtnBringToFront.setEnabled(false);
			tglbtnToBack.setEnabled(false);
			tglbtnToFront.setEnabled(false);
		}
		else
		{
			tglbtnModify.setEnabled(false);
			tglbtnDelete.setEnabled(false);
			tglbtnBringToBack.setEnabled(false);
			tglbtnBringToFront.setEnabled(false);
			tglbtnToBack.setEnabled(false);
			tglbtnToFront.setEnabled(false);
			
		}
		
		}
	
	public void updateUndoRedo(int switcher, int countUndo){
		if (switcher == 2 && countUndo == 0) {
			btnUndo.setEnabled(false);
			btnRedo.setEnabled(false);
		}
		else if (switcher == 1 && countUndo == 0) {
			btnUndo.setEnabled(true);
			btnRedo.setEnabled(false);
		}
		else if (switcher == 1 && countUndo > 0) {
			btnUndo.setEnabled(true);
			btnRedo.setEnabled(true);
		}
		else if (switcher == 0 && countUndo > 0) {
			btnUndo.setEnabled(false);
			btnRedo.setEnabled(true);
		}
		else if (switcher == 3 && countUndo == 0) {
			btnUndo.setEnabled(true);
			btnRedo.setEnabled(false);
		}
		else if (switcher == 5 && countUndo  == 0) {
			btnUndo.setEnabled(false);
			btnRedo.setEnabled(true);
		}
		else if (switcher == 4 && countUndo > 0) {
			btnUndo.setEnabled(true);
			btnRedo.setEnabled(true);
		}
		
		/*if (countCom == 0 && countUndo == 0) {
			btnUndo.setEnabled(false);
			btnRedo.setEnabled(false);
		}*/
	}

	/*public int getCountUndo() {
		return countUndo;
	}

	public void setCountUndo(int countUndo) {
		this.countUndo = countUndo;
	}
*/
	public JPanel getPnlInsideColor() {
		return pnlInsideColor;
	}

	public void setPnlInsideColor(JPanel pnlInsideColor) {
		this.pnlInsideColor = pnlInsideColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	public JTextArea getTxtA() {
		return txtA;
	}

	public void setTxtA(JTextArea txtA) {
		this.txtA = txtA;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnImportLog() {
		return btnImportLog;
	}

	public void setBtnImportLog(JButton btnImportLog) {
		this.btnImportLog = btnImportLog;
	}

	public JToggleButton getTglbtnBringToFront() {
		return tglbtnBringToFront;
	}

	public void setTglbtnBringToFront(JToggleButton tglbtnBringToFront) {
		this.tglbtnBringToFront = tglbtnBringToFront;
	}

	public JToggleButton getTglbtnBringToBack() {
		return tglbtnBringToBack;
	}

	public void setTglbtnBringToBack(JToggleButton tglbtnBringToBack) {
		this.tglbtnBringToBack = tglbtnBringToBack;
	}

	public JToggleButton getTglbtnToBack() {
		return tglbtnToBack;
	}

	public void setTglbtnToBack(JToggleButton tglbtnToBack) {
		this.tglbtnToBack = tglbtnToBack;
	}

	public JToggleButton getTglbtnToFront() {
		return tglbtnToFront;
	}

	public void setTglbtnToFront(JToggleButton tglbtnToFront) {
		this.tglbtnToFront = tglbtnToFront;
	}

	

	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}
}
