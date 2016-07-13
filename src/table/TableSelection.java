package table;

import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import form.Form;
import form.FormState;
import main.MainFrame;
import states.UpdateState;

public class TableSelection implements ListSelectionListener {

	Form form;
	int i = 0;
	
	public TableSelection(Form form) {
		this.form = form;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		Vector<String> values;
		if(!e.getValueIsAdjusting() && (MainFrame.getInstance().getContext().getState() instanceof UpdateState)) {
			/*
			for(int i = 0; i < form.getTable().getColumnCount(); i++) {
				Object val = form.getTable().getModel().getValueAt(form.getTable().getSelectedRow(), i);
				String value = val.toString();
				Vector<JTextField> fields = form.getDataPanel().getTextFields();
				fields.get(i).setText(value);
			}
			*/
			MainFrame.getInstance().getContext().getState().sync(MainFrame.getInstance().getContext(), form);
		}
		System.out.println("here");
		if(form.getTitle().equalsIgnoreCase("Poslovna godina")) {
			int selected = form.getTable().getSelectedRow();
			if(selected != -1) {
				String zatvoreno = (String) form.getTable().getModel().getValueAt(selected, 5);
				if(zatvoreno.equals("1")) {
					form.getButtonPanel().getCloseYear().setEnabled(false);
				} else {
					form.getButtonPanel().getCloseYear().setEnabled(true);
				}
			}
		}

	}

}
