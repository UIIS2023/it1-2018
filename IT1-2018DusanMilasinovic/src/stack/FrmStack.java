package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import geometry.Point;
import geometry.Rectangle;

import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

public class FrmStack extends JFrame {
	private DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	
	private JPanel contentPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FrmStack() {	
		setTitle("Milasinovic Dusan IT1-2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(Color.PINK);
		contentPanel.add(pnlCenter, BorderLayout.CENTER);
		
		JScrollPane scrlRectangle = new JScrollPane();
		
		JList listRectangle = new JList();
		scrlRectangle.setViewportView(listRectangle);
		listRectangle.setModel(dlm);
		
		JButton btnDodaj = new JButton("Add");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAdd dlgAdd=new DlgAdd();
				dlgAdd.setVisible(true);
				if(dlgAdd.getConfirm())
				{
					try {
						int x = Integer.parseInt(dlgAdd.getTxtX().getText());
						int y = Integer.parseInt(dlgAdd.getTxtY().getText());
						int sirina = Integer.parseInt(dlgAdd.getTxtWidth().getText());
						int visina = Integer.parseInt(dlgAdd.getTxtHeight().getText());
						
				Rectangle r=new Rectangle(new Point(x,y),sirina,visina);
				
						dlm.add(0,r);
						
						}
						catch(Exception NumberFormatException)
						{
							JOptionPane.showMessageDialog(null,"You must insert a number!");
						}			
				}
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlm.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "List is empty");
				}
				else
				{
					DlgAdd dlgAdd = new DlgAdd();
					Point p=dlm.getElementAt(0).getUpperLeftPoint();
					int width=dlm.getElementAt(0).getHeight();
					int  height = dlm.getElementAt(0).getHeight();
					
					dlgAdd.getTxtX().setText(Integer.toString(p.getX())); 
					dlgAdd.getTxtY().setText(Integer.toString(p.getY()));
					dlgAdd.getTxtWidth().setText(Integer.toString(width));
					dlgAdd.getTxtHeight().setText(Integer.toString(height));
					dlgAdd.setVisible(true);
					if(dlgAdd.getConfirm())
					{
						dlm.removeElementAt(0);
					}				
				}
			}
		});
		
		JLabel lblStack = new JLabel("Stack");
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDodaj, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrlRectangle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblStack, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addContainerGap(294, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addGap(31))
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGap(58)
					.addComponent(btnDodaj)
					.addGap(31)
					.addComponent(btnDelete))
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addGroup(gl_pnlCenter.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrlRectangle, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStack, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addContainerGap())
		);
		pnlCenter.setLayout(gl_pnlCenter);
	}
}
