package drawing;

import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgCircle extends JDialog {
	
	private boolean confirm;
	private JTextField txtRadius;
	private JTextField txtX;
	private JTextField txtY;
	public boolean colorConfirmation;
	Color fill;
	
	
	
	
	
	public DlgCircle() {
		getContentPane().setBackground(Color.PINK);
		setTitle("Milasinovic Dusan IT1-2018");
		setModal(true);
		setBounds(100, 100, 450, 300);
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		
		JLabel lblRadiusOfCircle = new JLabel("Insert radius of circle:");
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirm=true;
				dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				dispose();
			}
		});
		
		JLabel lblXCoordinateOf = new JLabel("X coordinate of start point");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		JLabel lblYCoordinateOf = new JLabel("Y coordinate of start point");
		
		txtY = new JTextField();
		txtY.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorConfirmation=true;
				fill=JColorChooser.showDialog(null, "Choose a color", fill);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(74))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblXCoordinateOf)
							.addGap(18)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYCoordinateOf)
								.addComponent(lblRadiusOfCircle))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(112, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(152)
					.addComponent(btnColor)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinateOf)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblYCoordinateOf)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRadiusOfCircle))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnColor)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	public boolean isColorConfirmation() {
		return colorConfirmation;
	}
	public void setColorConfirmation(boolean colorConfirmation) {
		this.colorConfirmation = colorConfirmation;
	}
	public Color getFill() {
		return fill;
	}
	public void setFill(Color fill) {
		this.fill = fill;
	}
	public JTextField getTxtX() {
		return txtX;
	}
	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);
	}
	public JTextField getTxtY() {
		return txtY;
	}
	public void setTxtY(String txtY) {
		this.txtY.setText(txtY); 
	}
	public JTextField getTxtRadius() {
		return txtRadius;
	}
	public void setTxtRadius(JTextField textField) {
		this.txtRadius = textField;
	}
	public boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirmation) {
		this.confirm = confirmation;
	}
	public void Lock()
	{
		txtX.setEditable(false);
		txtY.setEditable(false);
	}
	
}
