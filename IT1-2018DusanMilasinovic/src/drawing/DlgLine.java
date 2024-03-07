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

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartX;
	private JTextField txtEndX;
	private JTextField txtStartY;
	private JTextField txtEndY;
	

	public boolean confirmation;
	public boolean colorConfirmation;
	Color fill;
	

	
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DlgLine() {
		setTitle("Milasinovic Dusan IT1-2018");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartX = new JLabel("Start point X:");
			GridBagConstraints gbc_lblStartX = new GridBagConstraints();
			gbc_lblStartX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartX.gridx = 1;
			gbc_lblStartX.gridy = 1;
			contentPanel.add(lblStartX, gbc_lblStartX);
		}
		{
			txtStartX = new JTextField();
			GridBagConstraints gbc_txtStartX = new GridBagConstraints();
			gbc_txtStartX.insets = new Insets(0, 0, 5, 5);
			gbc_txtStartX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartX.gridx = 4;
			gbc_txtStartX.gridy = 1;
			contentPanel.add(txtStartX, gbc_txtStartX);
			txtStartX.setColumns(10);
		}
		{
			JLabel lblStartY = new JLabel("Start point Y:");
			GridBagConstraints gbc_lblStartY = new GridBagConstraints();
			gbc_lblStartY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartY.gridx = 1;
			gbc_lblStartY.gridy = 2;
			contentPanel.add(lblStartY, gbc_lblStartY);
		}
		{
			txtStartY = new JTextField();
			GridBagConstraints gbc_txtStartY = new GridBagConstraints();
			gbc_txtStartY.insets = new Insets(0, 0, 5, 5);
			gbc_txtStartY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartY.gridx = 4;
			gbc_txtStartY.gridy = 2;
			contentPanel.add(txtStartY, gbc_txtStartY);
			txtStartY.setColumns(10);
		}
		{
			JLabel lblEndX = new JLabel("End point X:");
			GridBagConstraints gbc_lblEndX = new GridBagConstraints();
			gbc_lblEndX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndX.gridx = 1;
			gbc_lblEndX.gridy = 3;
			contentPanel.add(lblEndX, gbc_lblEndX);
		}
		{
			txtEndX = new JTextField();
			GridBagConstraints gbc_txtEndX = new GridBagConstraints();
			gbc_txtEndX.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndX.gridx = 4;
			gbc_txtEndX.gridy = 3;
			contentPanel.add(txtEndX, gbc_txtEndX);
			txtEndX.setColumns(10);
		}
		{
			{
				JLabel lblEndY = new JLabel("Start point Y:");
				GridBagConstraints gbc_lblEndY = new GridBagConstraints();
				gbc_lblEndY.insets = new Insets(0, 0, 5, 5);
				gbc_lblEndY.gridx = 1;
				gbc_lblEndY.gridy = 4;
				contentPanel.add(lblEndY, gbc_lblEndY);
			}
		}
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorConfirmation=true;
				fill=JColorChooser.showDialog(null, "Choose a color", fill);
			}
		});
		{
			txtEndY = new JTextField();
			GridBagConstraints gbc_txtEndY = new GridBagConstraints();
			gbc_txtEndY.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndY.gridx = 4;
			gbc_txtEndY.gridy = 4;
			contentPanel.add(txtEndY, gbc_txtEndY);
			txtEndY.setColumns(10);
		}
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor.gridx = 4;
		gbc_btnColor.gridy = 6;
		contentPanel.add(btnColor, gbc_btnColor);
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
	public JTextField getTxtStartX() {
		return txtStartX;
	}

	public void setTxtStartX(JTextField txtStartX) {
		this.txtStartX = txtStartX;
	}

	public JTextField getTxtEndX() {
		return txtEndX;
	}

	public void setTxtEndX(JTextField txtEndX) {
		this.txtEndX = txtEndX;
	}

	public JTextField getTxtStartY() {
		return txtStartY;
	}

	public void setTxtStartY(JTextField txtStartY) {
		this.txtStartY = txtStartY;
	}

	public JTextField getTxtEndY() {
		return txtEndY;
	}

	public void setTxtEndY(JTextField txtEndY) {
		this.txtEndY = txtEndY;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
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

}
