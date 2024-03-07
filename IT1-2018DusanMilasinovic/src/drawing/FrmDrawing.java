package drawing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import geometry.Shape;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class FrmDrawing extends JFrame {

	int option=0;
	private JPanel contentPane;
	public DefaultListModel <Shape>  dlm=new DefaultListModel<Shape>();




	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FrmDrawing() {
		setBackground(Color.WHITE);
		setTitle("Milasinovic Dusan IT1-2018");
		PnlDrawing pnlDrawing = new PnlDrawing(this);
		pnlDrawing.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ButtonGroup group = new ButtonGroup();

		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.PINK);
		contentPane.add(pnlNorth, BorderLayout.NORTH);

		JLabel lblNaslov = new JLabel("Drawing");
		lblNaslov.setFont(new Font("Arial", Font.BOLD, 20));
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_pnlNorth = new GroupLayout(pnlNorth);
		gl_pnlNorth.setHorizontalGroup(
				gl_pnlNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlNorth.createSequentialGroup()
						.addGap(82)
						.addComponent(lblNaslov, GroupLayout.PREFERRED_SIZE, 409, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(281, Short.MAX_VALUE))
				);
		gl_pnlNorth.setVerticalGroup(
				gl_pnlNorth.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnlNorth.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNaslov))
				);
		pnlNorth.setLayout(gl_pnlNorth);

		JPanel pnlCentar = new JPanel();
		contentPane.add(pnlCentar, BorderLayout.CENTER);

		pnlDrawing.setSize(new Dimension(20,40));
		pnlDrawing.setPreferredSize(new Dimension(200,400));
		contentPane.add(pnlDrawing);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.PINK);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);




		JToggleButton tglSelect = new JToggleButton("Select");
		pnlSouth.add(tglSelect);
		group.add(tglSelect);
		tglSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				option = 6;
			}
		});

		JToggleButton tglModify = new JToggleButton("Modify");
		pnlSouth.add(tglModify);
		tglModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				option = 0;
				pnlDrawing.modifyShape();
			}
		});
		group.add(tglModify);


		JToggleButton tglDelete = new JToggleButton("Delete");
		pnlSouth.add(tglDelete);
		tglDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = 0;
				pnlDrawing.delete();
			}
		});
		group.add(tglDelete);

		JPanel pnlEast = new JPanel();
		pnlEast.setBackground(Color.PINK);
		contentPane.add(pnlEast, BorderLayout.EAST);
		pnlEast.setLayout(new GridLayout(0, 2, 0, 0));

		JRadioButton rdbPoint = new JRadioButton("Point");
		rdbPoint.setBackground(Color.PINK);
		rdbPoint.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlEast.add(rdbPoint);
		group.add(rdbPoint);

		JRadioButton rdbLine = new JRadioButton("Line");
		rdbLine.setBackground(Color.PINK);
		rdbLine.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlEast.add(rdbLine);
		group.add(rdbLine);

		JRadioButton rdbDonut = new JRadioButton("Donut");
		rdbDonut.setBackground(Color.PINK);
		rdbDonut.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlEast.add(rdbDonut);
		group.add(rdbDonut);
		rdbDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = 5;
			}
		});

		JRadioButton rdbCircle = new JRadioButton("Circle");
		rdbCircle.setBackground(Color.PINK);
		rdbCircle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlEast.add(rdbCircle);
		rdbCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = 3;
			}
		});
		group.add(rdbCircle);

		JRadioButton rdbRectangle = new JRadioButton("Rectangle");
		rdbRectangle.setBackground(Color.PINK);
		rdbRectangle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pnlEast.add(rdbRectangle);
		group.add(rdbRectangle);
		rdbRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = 4;
			}
		});
		rdbLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				option = 2;
			}
		});
		rdbPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				option = 1;
			}
		});
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public DefaultListModel<Shape> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<Shape> dlm) {
		this.dlm = dlm;
	}
}