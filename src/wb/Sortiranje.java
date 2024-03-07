package wb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import geometry.RectangleDraw;
import geometry.Point;


import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;

public class Sortiranje {
	
	private DlgRecDraw dlgkDva = new DlgRecDraw();
	private JFrame frame;
	private ArrayList <RectangleDraw> lista= new ArrayList<RectangleDraw>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sortiranje window = new Sortiranje();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sortiranje() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setName("Sortiranje-Dusan Milasinovic IT1/2018");
		frame.setBounds(100, 100, 782, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setFont(new Font("DialogInput", Font.BOLD, 13));
		btnDodaj.setForeground(new Color(34, 139, 34));
		btnDodaj.setBackground(new Color(220, 220, 220));
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgkDva.getOkButton().setVisible(true);
				dlgkDva.setVisible(true);
					try{
						RectangleDraw kvadrat = new RectangleDraw();
						kvadrat.setUpperLeft(new Point(dlgkDva.getX(),dlgkDva.getY()));
						kvadrat.setA(dlgkDva.getA());
						kvadrat.setStrColor(dlgkDva.getColor());
						kvadrat.setStrAreaColor(dlgkDva.getBojaUnutra());
						lista.add(kvadrat);
					}catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Niste uneli dogovarajuće vrednosti.");
					}
			}
		});
		
		JButton btnSortiraj = new JButton("Sortiraj");
		btnSortiraj.setFont(new Font("DialogInput", Font.BOLD, 13));
		btnSortiraj.setForeground(new Color(34, 139, 34));
		btnSortiraj.setBackground(new Color(220, 220, 220));
		
		GridBagConstraints gbc_btnSortiraj = new GridBagConstraints();
		gbc_btnSortiraj.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSortiraj.anchor = GridBagConstraints.NORTH;
		gbc_btnSortiraj.insets = new Insets(0, 0, 5, 5);
		gbc_btnSortiraj.gridx = 1;
		gbc_btnSortiraj.gridy = 0;
		panel.add(btnSortiraj, gbc_btnSortiraj);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);
		
		JTextArea txtA = new JTextArea();
		txtA.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(txtA);
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDodaj.anchor = GridBagConstraints.NORTH;
		gbc_btnDodaj.insets = new Insets(0, 0, 5, 5);
		gbc_btnDodaj.gridx = 0;
		gbc_btnDodaj.gridy = 0;
		panel.add(btnDodaj, gbc_btnDodaj);
		
	
		btnSortiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(lista);
				Iterator it = lista.iterator();
				String ispis = "";
				while(it.hasNext()) {
				ispis = ispis+" "+it.next()+"\n";
				}
				JOptionPane.showMessageDialog(null, ispis);
				txtA.setText(ispis);
			}
		});
		
	}

}
