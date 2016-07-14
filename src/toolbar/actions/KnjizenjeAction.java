package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import database.DataBase;
import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;
import states.UpdateState;

public class KnjizenjeAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5744503642562068934L;
	private Form form;
	private int storno;
	public KnjizenjeAction(Form form, int storno) {
		this.form = form;
		this.storno = storno;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		IDataGetter dataGetter = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = dataGetter.getData(form.getDataPanel());
		int prometniDokumentId = Integer.parseInt(formattedData.get("PROM_DOK_ID"));
		System.out.println("Pokusava da knjizi + " + prometniDokumentId);
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call proknjizi_prometni_dokument(?, ?)}");
									    
				proc.setInt(1, prometniDokumentId);	
				proc.setInt(2, storno);
				proc.execute();
				DataBase.getConnection().commit();
				//int index = form.getTable().getSelectedRow();


				ArrayList<String> columnValues = new ArrayList<>();
				
				for(int i = 0; i < form.getTable().getModel().getRowCount(); i++){
					int id = -1;
					try{
						id = Integer.parseInt((String) form.getTable().getModel().getValueAt(i, 0));
						if(id == prometniDokumentId){
							try{
								(form).refresh(i);
							}catch(Exception ex){}
							form.getTable().setColumnSelectionInterval(i, i);
						}
						//MainFrame.getInstance().getContext().getState().sync(MainFrame.getInstance().getContext(), form);
					}catch(Exception ex){
						
					}
				}

				
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(form,
					    e.getMessage());
			}
	}

}
