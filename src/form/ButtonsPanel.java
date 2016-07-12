package form;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.apache.commons.collections.map.HashedMap;

import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;
import net.miginfocom.swing.MigLayout;
import toolbar.actions.CommitAction;
import toolbar.actions.KnjizenjeAction;
import toolbar.actions.RollbackAction;
import toolbar.actions.StorniranjeAction;
import util.json.JSONModel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	private JButton btnCommit,  btnRollback;
	public ButtonsPanel(Form form, boolean isPrometni){
		ArrayList<JSONModel> jsonModels = MainFrame.getInstance().getJsonModels();
		ArrayList<JButton> buttons = new ArrayList<>();
		
		btnCommit = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
		btnCommit.setToolTipText("Commit");
		btnCommit.addActionListener(new CommitAction((JDialog) form));
		
		btnRollback = new JButton(new ImageIcon(getClass().getResource("/slike/remove.gif")));
		btnRollback.setToolTipText("Rollback");
		this.setLayout(new MigLayout("wrap"));
		this.add(btnCommit);
		this.add(btnRollback);
		
		btnRollback.addActionListener(new RollbackAction((JDialog) form));
		buttons.add(btnCommit);
		buttons.add(btnRollback);
		
		/**
		 * Disable dugmice koji nisu u funkciju za odgovarajucu formu
		 */
		System.out.println("ime tabele: "+form.getDescription().getCode());
		
		for(JSONModel model : jsonModels){
			if(form.getDescription().getCode().equalsIgnoreCase(model.getTableName())){
				for(String s : model.getFormItems()){
					for(JButton but : buttons){
						if(s.equalsIgnoreCase(but.getToolTipText())){
							but.setEnabled(false);
						}
					}
				}
				break;
			}
		}
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
