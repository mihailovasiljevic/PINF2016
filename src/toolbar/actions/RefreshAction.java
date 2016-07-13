package toolbar.actions;

import form.Form;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import main.MainFrame;
import main.MyMenuBar;
import states.InsertState;
import states.SearchState;
import states.State;
import table.MyTable;
import table.MyTableModel;

public class RefreshAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	//kada se napravi genericka forma, staviti tu klasu umesto JDialog
	private JDialog standardForm;

	public RefreshAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(standardForm instanceof Form){
			try {
				((Form) standardForm).refresh(0);
			} catch (Exception e) {
				System.out.println("Tabela je najverovatnije prazna a pokusavate da uradite refresh.");
			}			
		}
			
	}
		
		
}
