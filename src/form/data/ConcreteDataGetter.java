package form.data;

import java.awt.Component;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import form.DataPanel;

public class ConcreteDataGetter extends ADataGetter {

	private LinkedHashMap<String, String> data;

	public ConcreteDataGetter() {
		data = new LinkedHashMap<>();
	}

	public ConcreteDataGetter(LinkedHashMap<String, String> data) {
		super();
		this.data = data;
	}

	public HashMap<String, String> getData() {
		return data;
	}

	public void setData(LinkedHashMap<String, String> data) {
		this.data = data;
	}

	@Override
	public LinkedHashMap<String, String> getData(JPanel dataPanel) {

		DataPanel panel = (DataPanel)dataPanel;
		for(int i=0; i<panel.getColumnDescription().size();i++)
		for (JTextField txtField : panel.getTextFields()) {
			
			if(panel.getColumnDescription().get(i).getCode().equals(txtField.getName())){
				if(txtField.getText().trim().equals("") && txtField.getName() != null){
					data.put(txtField.getName(),null);
				}
				if (!txtField.getText().trim().equals("") && txtField.getName() != null) {
					data.put(txtField.getName(), txtField.getText());
					
				break;
				}
		}
				
		for(ButtonGroup bg : panel.getBtnGroups()){
			
			
			Enumeration<AbstractButton> btns = bg.getElements();
			
			boolean selected = false;
			boolean poklapanjeSaKolonom = false;
			String name = "";
			String value = "";
			while(btns.hasMoreElements())
			{	
				JRadioButton btn = (JRadioButton) btns.nextElement(); 
				name=btn.getName();
				value=btn.getText();
				if(name.equals(panel.getColumnDescription().get(i).getCode()))
					poklapanjeSaKolonom = true;
				if(btn.isSelected())
				{
					selected = true;
				}
			}
			
			if(poklapanjeSaKolonom){
			if(selected)
				if(value.equalsIgnoreCase("da"))
					data.put(name, "true");
				else data.put(name, "false");
				
			}
			break;
		}
		}

		if (data.size() == 0)
			return null;

		return data;
	}

}
