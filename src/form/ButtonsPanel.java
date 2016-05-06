package form;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import toolbar.actions.CommitAction;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	private JButton btnCommit,  btnRollback;
	public ButtonsPanel(Form form){
		btnCommit = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
		btnCommit.addActionListener(new CommitAction((JDialog) form));
		
		btnRollback = new JButton(new ImageIcon(getClass().getResource("/slike/remove.gif")));
		btnRollback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		this.setLayout(new MigLayout("wrap"));
		this.add(btnCommit);
		this.add(btnRollback);
		
	}
}
