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

public class KnjizenjeNivelacijaAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5744503642562068934L;
	private Form form;
	public KnjizenjeNivelacijaAction(Form form) {
		this.form = form;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		IDataGetter dataGetter = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = dataGetter.getData(form.getDataPanel());
		int robnaKarticaID = Integer.parseInt(formattedData.get("ROB_KART_ID"));
		System.out.println("Pokusava da nivelise + " + robnaKarticaID);
		try {
			CallableStatement proc = DataBase.getConnection().prepareCall("{ call nivelacija(?)}");
									    
				proc.setInt(1, robnaKarticaID);											      
				proc.execute();
				DataBase.getConnection().commit();
				
				for(int i = 0; i < form.getTable().getModel().getRowCount(); i++){
					int id = -1;
					try{
						id = Integer.parseInt((String) form.getTable().getModel().getValueAt(i, 0));
						if(id == robnaKarticaID){
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
