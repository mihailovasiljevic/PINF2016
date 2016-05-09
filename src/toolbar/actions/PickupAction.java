package toolbar.actions;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JTextField;

import table.MyTableModel;

import com.sun.jmx.snmp.Timestamp;

import form.Form;

public class PickupAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public PickupAction(JDialog standardForm) {
		this.standardForm = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
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
				System.out.println("n " + name);
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
			
			
		}
	}
}
