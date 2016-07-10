package toolbar.actions;

import form.Form;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import main.MyMenuBar;
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
			System.out.print("ref");
			try {
				((Form) standardForm).refresh(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Tabela je najverovatnije prazna a pokusavate da uradite refresh.");
			}
			
		}
					
				
			
	}
		
		
}
