package wb;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.INTERNAL;

import geometry.Point;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRecDraw extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtA;
	private JComboBox cbxColor;
	private JComboBox cbxAreaColor;
	private boolean modal;
	private JButton okButton;
	private JButton cancelButton;
	
	private int x,y;
	private int a;
	private String color;
	private String areaColor;

	
	
	public static void main(String[] args) {
		try {
			DlgRecDraw dialog = new DlgRecDraw();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRecDraw() {
		setModal(true);
		setResizable(false);
		setTitle("Rec");
		setBounds(100, 100, 322, 216);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X koordinata tacke gore levo:");
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
			JLabel lblY = new JLabel("Y koordinata take gore levo:");
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
			JLabel lblA = new JLabel("Duzina visine:");
			GridBagConstraints gbc_lblA = new GridBagConstraints();
			gbc_lblA.anchor = GridBagConstraints.WEST;
			gbc_lblA.insets = new Insets(0, 0, 5, 5);
			gbc_lblA.gridx = 0;
			gbc_lblA.gridy = 2;
			contentPanel.add(lblA, gbc_lblA);
		}
		{
			txtA = new JTextField();
			GridBagConstraints gbc_txtA = new GridBagConstraints();
			gbc_txtA.insets = new Insets(0, 0, 5, 0);
			gbc_txtA.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtA.gridx = 1;
			gbc_txtA.gridy = 2;
			contentPanel.add(txtA, gbc_txtA);
			txtA.setColumns(10);
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
			cbxColor = new JComboBox();
			cbxColor.setSelectedItem("Crna");
			cbxColor.setModel(new DefaultComboBoxModel(new String[] {"Bela", "Crna", "Plava", "Zelena", "Zuta", "Pink", "Crvena"}));
			cbxColor.setSelectedIndex(1);
			GridBagConstraints gbc_cbxColor = new GridBagConstraints();
			gbc_cbxColor.insets = new Insets(0, 0, 5, 0);
			gbc_cbxColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxColor.gridx = 1;
			gbc_cbxColor.gridy = 3;
			contentPanel.add(cbxColor, gbc_cbxColor);
		}
		{
			JLabel lblColor = new JLabel("Boja unutrasnosti:");
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.anchor = GridBagConstraints.WEST;
			gbc_lblColor.insets = new Insets(0, 0, 0, 5);
			gbc_lblColor.gridx = 0;
			gbc_lblColor.gridy = 4;
			contentPanel.add(lblColor, gbc_lblColor);
		}
		{
			cbxAreaColor = new JComboBox();
			cbxAreaColor.setSelectedItem("Bela");
			cbxAreaColor.setModel(new DefaultComboBoxModel(new String[] {"Bela", "Crna", "Plava", "Zelena", "Crvena", "Zuta"}));
			cbxAreaColor.setSelectedIndex(1);
			GridBagConstraints gbc_cbxAreaColor = new GridBagConstraints();
			gbc_cbxAreaColor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxAreaColor.gridx = 1;
			gbc_cbxAreaColor.gridy = 4;
			contentPanel.add(cbxAreaColor, gbc_cbxAreaColor);
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
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
						a = Integer.parseInt(txtA.getText());
						color = getCbxColor().getSelectedItem().toString();
						areaColor = getCbxAreaColor().getSelectedItem().toString();
						
						setVisible(false);
						modal=true;
						
						txtY.setText("");
						txtA.setText("");
						cbxColor.setSelectedItem("Crna");
						cbxAreaColor.setSelectedItem("Bela");
						txtX.setText("");
						}
						catch(Exception ex){
							JOptionPane.showMessageDialog(null, "Uneti parametri za kvadrat nisu dobri");
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
						txtY.setText("");
						txtA.setText("");
						cbxColor.setSelectedItem("Crna");
						cbxAreaColor.setSelectedItem("Crna");
						txtX.setText("");
					}
				});
				cancelButton.setActionCommand("Izadji");
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

	public JTextField getTxtA() {
		return txtA;
	}

	public JComboBox getCbxColor() {
		return cbxColor;
	}

	public JComboBox getCbxAreaColor() {
		return cbxAreaColor;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public void setTxtA(JTextField txtA) {
		this.txtA = txtA;
	}

	public void setCbxColor(JComboBox cbxColor) {
		this.cbxColor = cbxColor;
	}

	public void setCbxAreaColor(JComboBox cbxAreaColor) {
		this.cbxAreaColor = cbxAreaColor;
	}
	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	public boolean isModal() {
		return modal;
	}

	public void setModal(boolean modal) {
		this.modal = modal;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getA() {
		return a;
	}

	public String getColor() {
		return color;
	}

	public String getBojaUnutra() {
		return areaColor;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setA(int duzina) {
		this.a = duzina;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setBojaUnutra(String areaColor) {
		this.areaColor = areaColor;
	}
	


}
