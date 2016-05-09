package states;

import java.awt.Component;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;
import table.MyTable;
import table.MyTableModel;

public class InsertState extends AState{

	@Override
	public void doAction(Context context, Form form) {
		
		MainFrame.getInstance().getContext().setState(new UpdateState());
		
		IDataGetter data = new ConcreteDataGetter();
		LinkedHashMap<String, String> formattedData = data.getData(form.getDataPanel());
		try{
			MyTableModel mtm = (MyTableModel)form.getTable().getModel();
			int index = mtm.insertRow(formattedData);
			form.getTable().setRowSelectionInterval(index, index);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);	

		}
	}
	
}
