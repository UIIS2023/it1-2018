package wb;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JPanel pnlColor;
	private JPanel pnlAreaColor;
	private boolean check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setModal(true);
		setTitle("Rectangle");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X koordinata ta\u010Dke gore levo:");
			GridBagConstraints gbc_lblXKoordinataTake = new GridBagConstraints();
			gbc_lblXKoordinataTake.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinataTake.gridx = 0;
			gbc_lblXKoordinataTake.gridy = 0;
			contentPanel.add(lblX, gbc_lblXKoordinataTake);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 2;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblX_1 = new JLabel("X koordinata ta\u010Dke gore levo:");
			GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
			gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblX_1.gridx = 0;
			gbc_lblX_1.gridy = 1;
			contentPanel.add(lblX_1, gbc_lblX_1);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.anchor = GridBagConstraints.NORTH;
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 2;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Sirina:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 2;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 2;
			gbc_txtWidth.gridy = 2;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JLabel lblVisina = new JLabel("Visina:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 3;
			contentPanel.add(lblVisina, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 2;
			gbc_txtHeight.gridy = 3;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblColor = new JLabel("Boja ivice:");
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblColor.gridx = 0;
			gbc_lblColor.gridy = 4;
			contentPanel.add(lblColor, gbc_lblColor);
		}
		{
			pnlColor = new JPanel();
			pnlColor.setBackground(Color.BLACK);
			pnlColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Color color = JColorChooser.showDialog(null, "Izaberite boju ivice:", pnlColor.getBackground());
					pnlColor.setBackground(color);
				}
			});
			GridBagConstraints gbc_pnlColor = new GridBagConstraints();
			gbc_pnlColor.insets = new Insets(0, 0, 5, 0);
			gbc_pnlColor.fill = GridBagConstraints.BOTH;
			gbc_pnlColor.gridx = 2;
			gbc_pnlColor.gridy = 4;
			contentPanel.add(pnlColor, gbc_pnlColor);
		}
		{
			JLabel lblAreaColor = new JLabel("Boja unutra\u0161njosti:");
			GridBagConstraints gbc_lblAreaColor = new GridBagConstraints();
			gbc_lblAreaColor.insets = new Insets(0, 0, 0, 5);
			gbc_lblAreaColor.gridx = 0;
			gbc_lblAreaColor.gridy = 5;
			contentPanel.add(lblAreaColor, gbc_lblAreaColor);
		}
		{
			pnlAreaColor = new JPanel();
			pnlAreaColor.setBackground(Color.WHITE);
			pnlAreaColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Color areaColor = JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti:", pnlAreaColor.getBackground());
					pnlAreaColor.setBackground(areaColor);
				}
			});
			GridBagConstraints gbc_pnlAreaColor = new GridBagConstraints();
			gbc_pnlAreaColor.fill = GridBagConstraints.BOTH;
			gbc_pnlAreaColor.gridx = 2;
			gbc_pnlAreaColor.gridy = 5;
			contentPanel.add(pnlAreaColor, gbc_pnlAreaColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						check = true;
						setVisible(false);
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
						setVisible(false);
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

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public JPanel getPnlColor() {
		return pnlColor;
	}

	public JPanel getPnlAreaColor() {
		return pnlAreaColor;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public void setPnlColor(JPanel pnlColor) {
		this.pnlColor = pnlColor;
	}

	public void setPnlAreaColor(JPanel pnlAreaColor) {
		this.pnlAreaColor = pnlAreaColor;
	}
	

}
