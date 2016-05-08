package table;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import form.Form;
import form.FormState;

public class TableSelection implements ListSelectionListener {

	Form form;
	int i = 0;
	
	public TableSelection(Form form) {
		this.form = form;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		Vector<String> values;
		if(!e.getValueIsAdjusting() && form.getState() == FormState.Izmena) {
			for(int i = 0; i < form.getTable().getColumnCount(); i++) {
				Object val = form.getTable().getModel().getValueAt(form.getTable().getSelectedRow(), i);
				String value = val.toString();
				Vector<JTextField> fields = form.getDataPanel().getTextFields();
				fields.get(i).setText(value);
			}
		}

	}

}
