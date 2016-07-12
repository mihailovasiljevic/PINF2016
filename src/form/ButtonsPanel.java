package form;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.commons.collections.map.HashedMap;

import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import net.miginfocom.swing.MigLayout;
import toolbar.actions.CommitAction;
import toolbar.actions.KnjizenjeAction;
import toolbar.actions.RollbackAction;
import toolbar.actions.StorniranjeAction;

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
			JButton uknjizi = new JButton(new ImageIcon(getClass().getResource("/slike/knjizenje.png")));
			uknjizi.setText("knjizenje");
			uknjizi.setToolTipText("Uknjizi");
			

			
			uknjizi.addActionListener(new KnjizenjeAction(form));		
			
			JButton storniraj = new JButton(new ImageIcon(getClass().getResource("/slike/storniranje.png")));
			storniraj.setToolTipText("Storniraj");
			storniraj.setText("storniranje");
			storniraj.addActionListener(new StorniranjeAction(form));	
			
			JButton poNivelaciji = new JButton(new ImageIcon(getClass().getResource("/slike/nivelacija.png")));
			poNivelaciji.setText("knjizenje po nivelaciji");
			poNivelaciji.setToolTipText("Uknjizi po nivelaciji");
			poNivelaciji.addActionListener(new CommitAction((JDialog) form));		
				
			
			this.add(uknjizi);
			this.add(storniraj);
			this.add(poNivelaciji);

		}

		
	}
}
