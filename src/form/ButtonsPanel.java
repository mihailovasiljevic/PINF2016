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
import toolbar.actions.RollbackAction;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	private JButton btnCommit,  btnRollback;
	public ButtonsPanel(Form form, boolean isPrometni){
		btnCommit = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
		btnCommit.setToolTipText("Commit");
		btnCommit.addActionListener(new CommitAction((JDialog) form));
		
		btnRollback = new JButton(new ImageIcon(getClass().getResource("/slike/remove.gif")));
		btnRollback.setToolTipText("Poništi");
		this.setLayout(new MigLayout("wrap"));
		this.add(btnCommit);
		this.add(btnRollback);
		btnRollback.addActionListener(new RollbackAction((JDialog) form));
		if(isPrometni){
			JButton uknjizi = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
			uknjizi.setToolTipText("Uknjizi");
			uknjizi.addActionListener(new CommitAction((JDialog) form));		
			
			JButton storniraj = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
			storniraj.setToolTipText("Storniraj");
			storniraj.addActionListener(new CommitAction((JDialog) form));	
			this.add(uknjizi);
			this.add(storniraj);
		}

		
	}
}
