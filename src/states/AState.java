package states;

import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;

public abstract class AState implements State{

	@Override
	public void sync(Context context, Form form) {
		MainFrame.getInstance().getContext().setState(new UpdateState());
		int index = form.getTable().getSelectedRow();
		if (index < 0){
			clearAll(form);
			return;
		}
		ArrayList<String> columnValues = new ArrayList<>();
		
		for(int i = 0; i < form.getTable().getModel().getColumnCount(); i++){
			columnValues.add((String) form.getTable().getModel().getValueAt(index, i));	
		}
		
		fillAll(form, columnValues);
		setEditable(form, true);
		primaryKeyFieldEditable(form, false);
	}

	@Override
	public void setEditable(Form form, boolean isEditable) {
		for(JTextField txtField : form.getDataPanel().getTextFields()){
			txtField.setEditable(isEditable);
		}
		for(Component c : form.getDataPanel().getComponents()){
			if(c instanceof JRadioButton){
				JRadioButton rb = (JRadioButton)c;
				rb.setEnabled(isEditable);
			}
		}
		for(JButton btn : form.getDataPanel().getZoomBtns()){
			btn.setEnabled(isEditable);
		}
	}

	@Override
	public void clearAll(Form form) {
		for(JTextField txtField : form.getDataPanel().getTextFields()){
			txtField.setText("");
		}
	}

	@Override
	public void fillAll(Form form, ArrayList<String> values) {
		int i = 0;
		for(JTextField txtField : form.getDataPanel().getTextFields()){
			txtField.setText(values.get(i));
			i++;
		}	
		/*
		 * 		for(Component c : form.getDataPanel().getComponents()){
			if(c instanceof JTextField){
				((JTextField) c).setText(values.get(i));
			}else if (c instanceof JRadioButton){
				JRadioButton rb = (JRadioButton)c;
				if(Integer.parseInt(values.get(i)) == 1 && rb.getText().equalsIgnoreCase("da")){
					rb.setSelected(true);
				}else if(Integer.parseInt(values.get(i)) == 0 && rb.getText().equalsIgnoreCase("ne")){
					rb.setSelected(true);
				}
			}
			i++;
		}	
		 */
	}

	@Override
	public void primaryKeyFieldEditable(Form form, boolean isEditable) {
		form.getDataPanel().getTextFields().get(0).setEditable(isEditable);
	}
	
	

	
}
