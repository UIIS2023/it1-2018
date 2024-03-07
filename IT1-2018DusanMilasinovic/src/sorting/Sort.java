package sorting;

import stack.DlgAdd;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Sort extends JFrame {

	private DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle>();
	private ArrayList<Rectangle> list=new ArrayList<Rectangle>();
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sort frame = new Sort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sort() {
		setTitle("Milasinovic Dusan IT-2018");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(Color.PINK);
		
		JScrollPane scrRectangle = new JScrollPane();
		
		JList listRectangle = new JList();
		scrRectangle.setViewportView(listRectangle);
		listRectangle.setModel(dlm);
		
		JButton btnAdd = new JButton("Add");
		scrRectangle.setRowHeaderView(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DlgAdd add=new DlgAdd();
				add.setConfirm(false);
				add.setVisible(true);
				
				if(add.getConfirm())
				{
					try {
						int x= Integer.parseInt(add.getTxtX().getText());
						int y= Integer.parseInt(add.getTxtY().getText());
						int width= Integer.parseInt(add.getTxtWidth().getText());
						int height= Integer.parseInt(add.getTxtHeight().getText());
						
					Rectangle r=new Rectangle(new Point(x,y),width,height);
					dlm.add(0,r);
					} catch(NumberFormatException exception) {
						JOptionPane.showMessageDialog(null, "You have not entered a number");
					}
				}
			}
		});
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle r;
				for (int i = 0; i < dlm.getSize(); i++) {
					list.add(dlm.getElementAt(i));
				}
				for(int i=list.size();i>0;i--)
				{
					for (int j = 0; j < i-1; j++) {
						if((list.get(j)).compareTo(list.get(j+1))>0)
						{
							r=list.get(j);
							list.set(j,list.get(j+1));
							list.set(j+1, r);
						}
					}
				}
				
				dlm.removeAllElements();
				for(int i = 0; i<list.size(); i++)
				{
					dlm.addElement(list.get(i));
				}
				
			list.clear();
			}
		});
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addComponent(scrRectangle, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
				.addGroup(Alignment.TRAILING, gl_pnlCenter.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(btnSort, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_pnlCenter.setVerticalGroup(
			gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup()
					.addComponent(scrRectangle, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnSort))
		);
		
		JLabel lblSortingRectangles = new JLabel("Sorting Rectangles");
		scrRectangle.setColumnHeaderView(lblSortingRectangles);
		pnlCenter.setLayout(gl_pnlCenter);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlCenter, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnExit, Alignment.TRAILING)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pnlCenter, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnExit))
		);
		contentPane.setLayout(gl_contentPane);
		}

}
