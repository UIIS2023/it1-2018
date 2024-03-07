package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public Color getFill() {
		return fill;
	}


	public void setFill(Color fill) {
		this.fill = fill;
	}


	public boolean isColorConfirmation() {
		return colorConfirmation;
	}


	public void setColorConfirmation(boolean colorConfirmation) {
		this.colorConfirmation = colorConfirmation;
	}

	public boolean confirmation;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JTextField txtX;
	private JTextField txtY;
	
	
	public boolean colorConfirmation;
	Color fill;
	private JButton btnColor_1;

	
	
	
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DlgRectangle() {
		setTitle("Milasinovic Dusan IT1-2018");
		setModal(true);
		setBounds(100, 100, 427, 296);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblWidth = new JLabel("Width");
		}
		{
			txtWidth = new JTextField();
			txtWidth.setColumns(10);
		}
		{
			lblHeight = new JLabel("Height");
		}
		{
			txtHeight = new JTextField();
			txtHeight.setColumns(10);
		}
		JLabel lblX = new JLabel("X:");
		JLabel lblY = new JLabel("Y:");
		
		txtX = new JTextField();
		txtX.setColumns(10);
		
		txtY = new JTextField();
		txtY.setColumns(10);
		btnColor_1 = new JButton("Color");
		btnColor_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {		
			colorConfirmation=true;
			fill=JColorChooser.showDialog(null, "Choose a color", fill);				
				
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblX)
						.addComponent(lblHeight)
						.addComponent(lblWidth)
						.addComponent(lblY))
					.addGap(54)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtX)
						.addComponent(txtWidth)
						.addComponent(txtY)
						.addComponent(txtHeight))
					.addGap(18)
					.addComponent(btnColor_1)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblY))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWidth))
					.addGap(31)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(btnColor_1)
					.addGap(85))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.PINK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						confirmation=true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
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

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}
	
	public void Lock()
	{
		txtX.setEditable(false);
		txtY.setEditable(false);
	}

}
