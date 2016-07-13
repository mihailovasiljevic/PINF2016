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
		
		disableButtons(form);
		
		IDataGetter data = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = data.getData(form.getDataPanel());
		
		try{
			MyTableModel mtm = (MyTableModel)form.getTable().getModel();
			mtm.search(formattedData,form.getDataPanel().getAddedTextFields());
			
			
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);	

		}

		for(int i=0; i<form.getDataPanel().getAddedTextFields().size();i++)
		{
			form.getDataPanel().getAddedTextFields().get(i).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getAddedZoomBtns().size();i++)
		{
			form.getDataPanel().getAddedZoomBtns().get(i).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getAddedPickBtns().size();i++)
		{
			form.getDataPanel().getAddedPickBtns().get(i).setVisible(false);
		}
	}
}
