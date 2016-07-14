package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.Calendar;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import main.MainFrame;
import states.InsertState;
import database.DataBase;
import form.Form;

public class CloseYear extends AbstractAction {

	private Form form;
	
	public CloseYear(Form form) {
		this.form = form;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MainFrame.getInstance().getContext().getState() instanceof InsertState) {
			
		} else {
			JOptionPane.showMessageDialog(null, "Nije moguce otvaranje poslovne godine ", "GRESKA", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int selected = form.getTable().getSelectedRow();
		String sel = (String) form.getTable().getModel().getValueAt(selected, 0);
		
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call zatvaranje_poslovne_godine(?,?)}");
			proc.setInt(1, Integer.parseInt(sel));
			proc.registerOutParameter(2, java.sql.Types.INTEGER);
			proc.executeUpdate();
			
			Integer val = proc.getInt(2);
			if(val == 1) {
				JOptionPane.showMessageDialog(null, "NIje moguce zatvaranje poslovne godine", "GRESKA", JOptionPane.ERROR_MESSAGE);			
			} else {
				int sela = form.getTable().getSelectedRow();
				if(sela != -1) {
					Calendar cal = Calendar.getInstance();
					Integer day = cal.get(Calendar.DAY_OF_MONTH);
					Integer month = cal.get(Calendar.MONTH) + 1;
					Integer year = cal.get(Calendar.YEAR);
					String date = year.toString() + "-" + month.toString() + "-" + day.toString();
					form.getTable().getModel().setValueAt(date, sela, 4);
					form.getTable().getModel().setValueAt("1", sela, 5);
					form.getButtonsPanel().getCloseYear().setEnabled(false);
				}
				DataBase.getConnection().commit();
			}
		} catch(SQLException ee) {
			ee.printStackTrace();
		}
		
		
	}
	
}
