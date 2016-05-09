package toolbar.actions;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JTextField;

import table.MyTableModel;

import com.sun.jmx.snmp.Timestamp;

import database.ColumnDescription;
import form.Form;

public class PickupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public PickupAction(JDialog standardForm) {
		this.standardForm = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/*
		if(standardForm instanceof Form) {
			Form form = ((Form)standardForm);
			MyTableModel model = form.getTable().getModel();
			Vector datas = model.getDataVector();
			String code = form.getCode();
			System.out.println(code);
			System.out.println(form.getDataPanel().getTextFields().size());
			for(int i = 0; i < form.getDataPanel().getTextFields().size(); i++) {
				System.out.println("ulaz");
				String name = form.getDataPanel().getTextFields().get(i).getName();
				System.out.println("hej");
				System.out.println("n " + code);
				if(code.equals(form.getDataPanel().getTextFields().get(i).getName())) {
					Object obj = datas.get(i);
					JTextField field = form.getDataPanel().getTextFields().get(i);
					String value = "";
					if(obj instanceof String) {
						value = (String)obj;
					}
					if(obj instanceof Timestamp) {
					
					}
					field.setText(value);
				}
			}		
			*/
			
		if(standardForm instanceof Form) {
			Form form = ((Form)standardForm);
			MyTableModel model = form.getTable().getModel();
			Vector<ColumnDescription> colDescs = form.getDataPanel().getColumnDescription();
			JTextField textField = form.getField();
			Vector datas = model.getDataVector();
			String code = form.getCode();
			System.out.println(code);
			
			for(int i=0; i<colDescs.size(); i++){
				if(colDescs.get(i).getCode().equalsIgnoreCase(code))
				{
					
					Object obj = ((Vector)datas.get(form.getTable().getSelectedRow())).get(i);
					String value = "";
					System.out.println(datas.get(i).toString());
						if(obj instanceof String) {
							value = (String)obj;
						}
						if(obj instanceof Timestamp) {
							
						}
						System.out.println(value);
						textField.setText(value);
				}
		
			}
			
			standardForm.dispose();
			
		
	}
		
}
}
