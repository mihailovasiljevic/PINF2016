package states;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import main.MainFrame;
import table.MyTableModel;
import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;

public class SearchState  extends AState{

	@Override
	public void doAction(Context context, Form form) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().getContext().setState(new UpdateState());
		
		IDataGetter data = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = data.getData(form.getDataPanel());
		
		try{
			MyTableModel mtm = (MyTableModel)form.getTable().getModel();
			mtm.search(formattedData);
			
			
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);	

		}
		
		System.out.println(form.getDataPanel().getSearchIndices().size());
		for(int i=0; i<form.getDataPanel().getSearchIndices().size();i++)
		{
			form.getDataPanel().getTextFields().get(form.getDataPanel().getSearchIndices().get(i)).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getZoomBtnIndices().size();i++)
		{
			form.getDataPanel().getZoomBtns().get(form.getDataPanel().getZoomBtnIndices().get(i)).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getBtnPickIndices().size();i++)
		{
			form.getDataPanel().getBtnPicks().get(form.getDataPanel().getBtnPickIndices().get(i)).setVisible(false);
		}
		form.getDataPanel().getSearchIndices().clear();
		form.getDataPanel().getZoomBtnIndices().clear();
		form.getDataPanel().getBtnPickIndices().clear();
	}
}
