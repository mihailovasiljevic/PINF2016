package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractAction;

import database.DataBase;
import form.Form;

public class OpenYear extends AbstractAction {

	private Form form;
	
	public OpenYear(Form form) {
		this.form = form;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String POSL_GOD_GOD = form.getDataPanel().getField("POSL_GOD_GOD").getText();
		String POSL_GOD_DAT_ID = form.getDataPanel().getField("POSL_GOD_DAT_ID").getText();
		String POSL_SIS_ID = form.getDataPanel().getField("POSL_SIS_ID").getText();
		String POSL_GOD_ID = form.getDataPanel().getField("POSL_GOD_ID").getText();
		
		System.out.println("poslovna " + POSL_GOD_ID + " " + POSL_GOD_DAT_ID + " " + POSL_SIS_ID + " " + POSL_GOD_GOD );
		
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call otvaranje_poslovne_godine(?,?,?,?)}");
				proc.setInt(1, Integer.parseInt(POSL_SIS_ID));
				proc.setString(2, POSL_GOD_GOD);
				
				
				String month = POSL_GOD_DAT_ID.split("-")[0];
				String day = POSL_GOD_DAT_ID.split("-")[1];
				String year = POSL_GOD_DAT_ID.split("-")[2];
				
				Date date = new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(day));
				
				proc.setDate(3, date);
				proc.registerOutParameter(4, java.sql.Types.INTEGER);
				proc.execute();
				//proc.getString(4);
				DataBase.getConnection().commit();

			} catch (SQLException ee) {
				ee.printStackTrace();
				return;
			}
		
		Vector adding = new Vector();
		
		//form.getTable().getModel().addRow(rowData);
		
	}

}