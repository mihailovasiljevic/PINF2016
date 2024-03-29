package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import database.DataBase;
import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;

public class StorniranjeAction extends AbstractAction{
	private Form form;
	public StorniranjeAction(Form form) {
		this.form = form;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		IDataGetter dataGetter = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = dataGetter.getData(form.getDataPanel());
		int prometniDokumentId = Integer.parseInt(formattedData.get("PROM_DOK_ID"));
		System.out.println("Pokusava da stornira + " + prometniDokumentId);
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call storniraj_prometni_dokument(?)}");
									    
				proc.setInt(1, prometniDokumentId);											      
				proc.execute();
				DataBase.getConnection().commit();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(form,
					    e.getMessage());
			}
	}

}
