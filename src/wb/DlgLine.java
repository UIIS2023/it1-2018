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
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXBegin;
	private JTextField txtYBegin;
	private JTextField txtXEnd;
	private JTextField txtYEnd;
	private boolean check;
	private JPanel pnlColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setModal(true);
		setTitle("Linija");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXBegin = new JLabel("X koordinata po\u010Detne ta\u010Dke:");
			GridBagConstraints gbc_lblXBegin = new GridBagConstraints();
			gbc_lblXBegin.insets = new Insets(0, 0, 5, 5);
			gbc_lblXBegin.gridx = 0;
			gbc_lblXBegin.gridy = 0;
			contentPanel.add(lblXBegin, gbc_lblXBegin);
		}
		{
			txtXBegin = new JTextField();
			GridBagConstraints gbc_txtXBegin = new GridBagConstraints();
			gbc_txtXBegin.insets = new Insets(0, 0, 5, 0);
			gbc_txtXBegin.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXBegin.gridx = 2;
			gbc_txtXBegin.gridy = 0;
			contentPanel.add(txtXBegin, gbc_txtXBegin);
			txtXBegin.setColumns(10);
		}
		{
			JLabel lblYBegin = new JLabel("Y koordinata po\u010Detne ta\u010Dke:");
			GridBagConstraints gbc_lblYBegin = new GridBagConstraints();
			gbc_lblYBegin.insets = new Insets(0, 0, 5, 5);
			gbc_lblYBegin.gridx = 0;
			gbc_lblYBegin.gridy = 1;
			contentPanel.add(lblYBegin, gbc_lblYBegin);
		}
		{
			txtYBegin = new JTextField();
			GridBagConstraints gbc_txtYBegin = new GridBagConstraints();
			gbc_txtYBegin.insets = new Insets(0, 0, 5, 0);
			gbc_txtYBegin.anchor = GridBagConstraints.NORTH;
			gbc_txtYBegin.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYBegin.gridx = 2;
			gbc_txtYBegin.gridy = 1;
			contentPanel.add(txtYBegin, gbc_txtYBegin);
			txtYBegin.setColumns(10);
		}
		{
			JLabel lblXEnd = new JLabel("X koordinata krajnje ta\u010Dke:");
			GridBagConstraints gbc_lblXKoordinataKrajnje = new GridBagConstraints();
			gbc_lblXKoordinataKrajnje.insets = new Insets(0, 0, 5, 5);
			gbc_lblXKoordinataKrajnje.gridx = 0;
			gbc_lblXKoordinataKrajnje.gridy = 2;
			contentPanel.add(lblXEnd, gbc_lblXKoordinataKrajnje);
		}
		{
			txtXEnd = new JTextField();
			GridBagConstraints gbc_txtXKraj = new GridBagConstraints();
			gbc_txtXKraj.insets = new Insets(0, 0, 5, 0);
			gbc_txtXKraj.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtXKraj.gridx = 2;
			gbc_txtXKraj.gridy = 2;
			contentPanel.add(txtXEnd, gbc_txtXKraj);
			txtXEnd.setColumns(10);
		}
		{
			JLabel lblYEnd = new JLabel("Y koordinata krajnje ta\u010Dke:");
			GridBagConstraints gbc_lblYEnd = new GridBagConstraints();
			gbc_lblYEnd.insets = new Insets(0, 0, 5, 5);
			gbc_lblYEnd.gridx = 0;
			gbc_lblYEnd.gridy = 3;
			contentPanel.add(lblYEnd, gbc_lblYEnd);
		}
		{
			txtYEnd = new JTextField();
			GridBagConstraints gbc_txtYEnd = new GridBagConstraints();
			gbc_txtYEnd.insets = new Insets(0, 0, 5, 0);
			gbc_txtYEnd.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtYEnd.gridx = 2;
			gbc_txtYEnd.gridy = 3;
			contentPanel.add(txtYEnd, gbc_txtYEnd);
			txtYEnd.setColumns(10);
		}
		{
			JLabel lblColor = new JLabel("Boja:");
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.anchor = GridBagConstraints.BELOW_BASELINE;
			gbc_lblColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblColor.gridx = 0;
			gbc_lblColor.gridy = 4;
			contentPanel.add(lblColor, gbc_lblColor);
		}
		
		{
			pnlColor = new JPanel();
			pnlColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Color color = JColorChooser.showDialog(null, "Izaberite boju linije:", Color.BLACK);
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
			JLabel lblEmpty = new JLabel("     ");
			GridBagConstraints gbc_lblEmpty = new GridBagConstraints();
			gbc_lblEmpty.anchor = GridBagConstraints.SOUTHWEST;
			gbc_lblEmpty.insets = new Insets(0, 0, 0, 5);
			gbc_lblEmpty.gridx = 0;
			gbc_lblEmpty.gridy = 6;
			contentPanel.add(lblEmpty, gbc_lblEmpty);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{		
							
							setVisible(false);
							check=true;
							
							}
							catch(Exception greska){
								JOptionPane.showMessageDialog(null, "Niste uneli dobre parametre");
							}
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

	public JTextField getTxtXBegin() {
		return txtXBegin;
	}

	public JTextField getTxtYBegin() {
		return txtYBegin;
	}

	public JTextField getTxtXEnd() {
		return txtXEnd;
	}

	public JTextField getTxtYEnd() {
		return txtYEnd;
	}

	public void setTxtXBegin(JTextField txtXBegin) {
		this.txtXBegin = txtXBegin;
	}

	public void setTxtYBegin(JTextField txtYBegin) {
		this.txtYBegin = txtYBegin;
	}

	public void setTxtXEnd(JTextField txtXEnd) {
		this.txtXEnd = txtXEnd;
	}

	public void setTxtYEnd(JTextField txtYEnd) {
		this.txtYEnd = txtYEnd;
	}

	public boolean isCheck() {
		return check;
	}

	public JPanel getPnlColor() {
		return pnlColor;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setPnlColor(JPanel pnlColor) {
		this.pnlColor = pnlColor;
	}
	

}
