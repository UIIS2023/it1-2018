package drawing;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DlgDelete extends JDialog {
	
	private boolean confirm;
	public void setConfirm(boolean c)
	{
		this.confirm=c;
	}
	public boolean getConfirm()
	{
		return this.confirm;
	}
	public DlgDelete() {
		getContentPane().setBackground(Color.PINK);
		setTitle("Milasinovic Dusan IT1-2018");
		setModal(true);
		setBounds(100, 100, 450, 300);

		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete it?");
		
		JButton btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirm(true);
				dispose();
			}
		});
		
		JButton btnNo = new JButton("No");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirm(false);
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(109)
					.addComponent(lblAreYouSure)
					.addContainerGap(123, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(btnYes)
					.addPreferredGap(ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
					.addComponent(btnNo)
					.addGap(57))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(76)
					.addComponent(lblAreYouSure)
					.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnYes)
						.addComponent(btnNo))
					.addGap(64))
		);
		getContentPane().setLayout(groupLayout);
	}

}
