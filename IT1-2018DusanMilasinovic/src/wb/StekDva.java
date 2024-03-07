package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;


import geometry.RectangleDraw;
import geometry.Point;


public class StekDva {

	private JFrame frame;
	private JButton btnDodajNaStek, btnIzbrisiSaSteka;	
	private Stack <RectangleDraw> stek= new Stack<RectangleDraw>();
	private DlgRecDraw dlgkDva = new DlgRecDraw();	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StekDva window = new StekDva();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public StekDva() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Tree.hash"));
		frame.setName("Stek-Dusan Milasinovic IT1/2018");
		frame.setBounds(100, 100, 500, 225);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{77, 0, 177, 0, 0};
		gridBagLayout.rowHeights = new int[]{29, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		btnDodajNaStek = new JButton("Dodaj na stek");
		btnDodajNaStek.setForeground(new Color(0, 153, 51));
		btnDodajNaStek.setSize(100, 300);
		btnDodajNaStek.setFont(new Font("DialogInput", Font.BOLD, 15));
		btnDodajNaStek.setBackground(UIManager.getColor("activeCaption"));
		btnDodajNaStek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dlgkDva.setVisible(true);
					try{
						RectangleDraw kvadrat = new RectangleDraw();
						kvadrat.setUpperLeft(new Point(dlgkDva.getX(),dlgkDva.getY()));
						kvadrat.setA(dlgkDva.getA());
						kvadrat.setStrColor(dlgkDva.getColor());
						kvadrat.setStrAreaColor(dlgkDva.getBojaUnutra());
						JOptionPane.showMessageDialog(null, "Dodali ste kvadrat:"+kvadrat);
						stek.push(kvadrat);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, "Niste uneli odgovarajuće vrednosti.");
					}	
			}
		});
		GridBagConstraints gbc_btnDodajNaStek = new GridBagConstraints();
		gbc_btnDodajNaStek.anchor = GridBagConstraints.NORTH;
		gbc_btnDodajNaStek.insets = new Insets(0, 0, 0, 5);
		gbc_btnDodajNaStek.gridx = 1;
		gbc_btnDodajNaStek.gridy = 1;
		frame.getContentPane().add(btnDodajNaStek, gbc_btnDodajNaStek);
		
		btnIzbrisiSaSteka = new JButton("Izbri\u0161i sa steka");
		btnIzbrisiSaSteka.setForeground(new Color(0, 153, 51));
		btnIzbrisiSaSteka.setFont(new Font("DialogInput", Font.BOLD, 15));
		btnIzbrisiSaSteka.setBackground(UIManager.getColor("activeCaption"));
		btnIzbrisiSaSteka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stek.isEmpty())
					JOptionPane.showMessageDialog(null, "Stek je prazan.");
				else{
					dlgkDva.getTxtX().setText(Integer.toString(stek.peek().getUpperLeft().getX()));
					dlgkDva.getTxtY().setText(Integer.toString(stek.peek().getUpperLeft().getY()));
					dlgkDva.getTxtA().setText(Integer.toString(stek.peek().getA()));
					dlgkDva.getCbxColor().setSelectedItem(stek.peek().getStrColor());
					dlgkDva.getCbxColor().setSelectedItem(stek.peek().getStrAreaColor());			
					dlgkDva.getOkButton().setVisible(false);
					dlgkDva.setVisible(true);
					stek.pop();
				}
			}
		});
		GridBagConstraints gbc_btnIzbrisiSaSteka = new GridBagConstraints();
		gbc_btnIzbrisiSaSteka.insets = new Insets(0, 0, 0, 5);
		gbc_btnIzbrisiSaSteka.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnIzbrisiSaSteka.gridx = 2;
		gbc_btnIzbrisiSaSteka.gridy = 1;
		frame.getContentPane().add(btnIzbrisiSaSteka, gbc_btnIzbrisiSaSteka);
	}
}
