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
import toolbar.actions.CloseYear;
import toolbar.actions.CommitAction;
import toolbar.actions.KnjizenjeAction;
import toolbar.actions.KnjizenjeNivelacijaAction;
import toolbar.actions.OpenYear;
import toolbar.actions.RollbackAction;
import toolbar.actions.StorniranjeAction;
import util.json.JSONModel;

@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	private JButton btnCommit,  btnRollback;
	private boolean btnsEnabled=true;
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
		JButton uknjizi = new JButton(new ImageIcon(getClass().getResource("/slike/knjizenje.png")));
		uknjizi.setText("knjizenje");
		uknjizi.setToolTipText("Knjizenje");
		

		
		uknjizi.addActionListener(new KnjizenjeAction(form, 0));		
		
		JButton storniraj = new JButton(new ImageIcon(getClass().getResource("/slike/storniranje.png")));
		storniraj.setToolTipText("STORNIRANJE");
		storniraj.setText("Storniranje");
		storniraj.addActionListener(new KnjizenjeAction(form, 1));	
		
		JButton poNivelaciji = new JButton(new ImageIcon(getClass().getResource("/slike/nivelacija.png")));
		poNivelaciji.setText("knjizenje po nivelaciji");
		poNivelaciji.setToolTipText("Nivelacija");
		poNivelaciji.addActionListener(new KnjizenjeNivelacijaAction(form));		

		JButton openYear = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
		openYear.setText("OTVARANJE");
		openYear.setToolTipText("Otvaranje");
		openYear.addActionListener(new OpenYear(form));	
		
		JButton closeYear = new JButton(new ImageIcon(getClass().getResource("/slike/commit.gif")));
		closeYear.setText("ZATVARANJE");
		closeYear.setToolTipText("Zatvaranje");
		closeYear.addActionListener(new CloseYear(form));	
		
		buttons.add(btnCommit);
		buttons.add(btnRollback);
		buttons.add(uknjizi);
		buttons.add(storniraj);
		buttons.add(poNivelaciji);
		buttons.add(openYear);
		buttons.add(closeYear);
		
		/**
		 * Disable dugmice koji nisu u funkciju za odgovarajucu formu
		 */
		System.out.println("ime tabele: "+form.getDescription().getCode());
		
		for(JSONModel model : jsonModels){
			if(form.getDescription().getCode().equalsIgnoreCase(model.getTableName())){
				btnsEnabled=false;
				for(String s : model.getFormItems()){
					for(JButton but : buttons){
						if(s.equalsIgnoreCase(but.getToolTipText())){
							but.setEnabled(false);
						}
					}
				}
				for(String s : model.getNewFormItems()){
					for(JButton but : buttons){
						if(s.equalsIgnoreCase(but.getToolTipText())){
							this.add(but);
						}
					}
				}
				break;
			}
		}

	}
	public JButton getBtnCommit() {
		return btnCommit;
	}
	public void setBtnCommit(JButton btnCommit) {
		this.btnCommit = btnCommit;
	}
	public JButton getBtnRollback() {
		return btnRollback;
	}
	public void setBtnRollback(JButton btnRollback) {
		this.btnRollback = btnRollback;
	}
	public boolean isBtnsEnabled() {
		return btnsEnabled;
	}
	public void setBtnsEnabled(boolean btnsEnabled) {
		this.btnsEnabled = btnsEnabled;
	}
	
	
	
}
