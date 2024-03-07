package wb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DlgHexagon extends JDialog{
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private boolean modal;
	private JButton okButton;
	private JButton cancelButton;
	private boolean check=false;
	
	private JPanel pnlColor, pnlAreaColor;
	
	private int x,y;
	private int txtXN,txtYN,txtLenghtN;
	private String cbxColorN,cbxAreaColorN;
	
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgHexagon() {
		setModal(true);
		setLocation(800, 600);
		setResizable(false);
		setTitle("Krug");
		setBounds(100, 100, 363, 239);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X koordinata ta\u010Dke gore levo:");
			GridBagConstraints gbc_lblX = new GridBagConstraints();
			gbc_lblX.anchor = GridBagConstraints.WEST;
			gbc_lblX.insets = new Insets(0, 0, 5, 5);
			gbc_lblX.gridx = 0;
			gbc_lblX.gridy = 0;
			contentPanel.add(lblX, gbc_lblX);
		}
		{
			txtX = new JTextField();
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 1;
			gbc_txtX.gridy = 0;
			contentPanel.add(txtX, gbc_txtX);
			txtX.setColumns(10);
		}
		{
			JLabel lblY = new JLabel("Y koordinata ta\u010Dke gore levo:");
			GridBagConstraints gbc_lblY = new GridBagConstraints();
			gbc_lblY.anchor = GridBagConstraints.WEST;
			gbc_lblY.insets = new Insets(0, 0, 5, 5);
			gbc_lblY.gridx = 0;
			gbc_lblY.gridy = 1;
			contentPanel.add(lblY, gbc_lblY);
		}
		{
			txtY = new JTextField();
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 1;
			gbc_txtY.gridy = 1;
			contentPanel.add(txtY, gbc_txtY);
			txtY.setColumns(10);
		}
		{
			JLabel lblA = new JLabel("Polupre\u010Dnik:");
			GridBagConstraints gbc_lblA = new GridBagConstraints();
			gbc_lblA.anchor = GridBagConstraints.WEST;
			gbc_lblA.insets = new Insets(0, 0, 5, 5);
			gbc_lblA.gridx = 0;
			gbc_lblA.gridy = 2;
			contentPanel.add(lblA, gbc_lblA);
		}
		{
			txtR = new JTextField();
			GridBagConstraints gbc_txtDuzinaStranice = new GridBagConstraints();
			gbc_txtDuzinaStranice.insets = new Insets(0, 0, 5, 0);
			gbc_txtDuzinaStranice.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDuzinaStranice.gridx = 1;
			gbc_txtDuzinaStranice.gridy = 2;
			contentPanel.add(txtR, gbc_txtDuzinaStranice);
			txtR.setColumns(10);
		}
		{
			JLabel lblColor = new JLabel("Boja ivice:");
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.anchor = GridBagConstraints.WEST;
			gbc_lblColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblColor.gridx = 0;
			gbc_lblColor.gridy = 3;
			contentPanel.add(lblColor, gbc_lblColor);
		}
		{
			pnlColor = new JPanel();
			pnlColor.setBackground(Color.BLACK);
			pnlColor.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Color boja = JColorChooser.showDialog(null,"Izaberite boju ivice:", pnlColor.getBackground());
					pnlColor.setBackground(boja);
				}
			});
			GridBagConstraints gbc_pnlColor = new GridBagConstraints();
			gbc_pnlColor.insets = new Insets(0, 0, 5, 0);
			gbc_pnlColor.fill = GridBagConstraints.BOTH;
			gbc_pnlColor.gridx = 1;
			gbc_pnlColor.gridy = 3;
			contentPanel.add(pnlColor, gbc_pnlColor);
		}
		{
			JLabel lblAreaColor = new JLabel("Boja unutrasnosti:");
			GridBagConstraints gbc_lblAreaColor = new GridBagConstraints();
			gbc_lblAreaColor.anchor = GridBagConstraints.WEST;
			gbc_lblAreaColor.insets = new Insets(0, 0, 0, 5);
			gbc_lblAreaColor.gridx = 0;
			gbc_lblAreaColor.gridy = 4;
			contentPanel.add(lblAreaColor, gbc_lblAreaColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try{		
						setVisible(false);
						check=true;
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, "Uneti parametri za sestougao nisu dobri");
						}
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Izadji");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Izadji");
				buttonPane.add(cancelButton);
				
			}
		}
		{
			pnlAreaColor = new JPanel();
			pnlAreaColor.setBackground(Color.WHITE);
			pnlAreaColor.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Color areaColor = JColorChooser.showDialog(null, "Izaberite boju unutrašnjosti:", pnlAreaColor.getBackground());
					pnlAreaColor.setBackground(areaColor);
				}
			});
			GridBagConstraints gbc_pnlAreaColor = new GridBagConstraints();
			gbc_pnlAreaColor.fill = GridBagConstraints.BOTH;
			gbc_pnlAreaColor.gridx = 1;
			gbc_pnlAreaColor.gridy = 4;
			contentPanel.add(pnlAreaColor, gbc_pnlAreaColor);
		}
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public JTextField getTxtR() {
		return txtR;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public void setTxtR(JTextField txtA) {
		this.txtR = txtA;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	public int getTxtXN() {
		return txtXN;
	}

	public int getTxtYN() {
		return txtYN;
	}

	public int getTxtLenghtN() {
		return txtLenghtN;
	}

	public void setTxtXN(int txtXStr) {
		this.txtXN = txtXN;
	}

	public void setTxtYStr(int txtYN) {
		this.txtYN = txtYN;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public void setTxtDuzinaStr(int txtLenghtN) {
		this.txtLenghtN = txtLenghtN;
	}

	public JPanel getPnlColor() {
		return pnlColor;
	}

	public JPanel getPnlAreaColor() {
		return pnlAreaColor;
	}

	public void setPnlColor(JPanel pnlColor) {
		this.pnlColor = pnlColor;
	}

	public void setPnlAreaColor(JPanel pnlAreaColor) {
		this.pnlAreaColor = pnlAreaColor;
	}	
}
