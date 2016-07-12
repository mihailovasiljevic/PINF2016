package form.data;

import java.awt.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

		for (Component component : dataPanel.getComponents()) {
			if (component instanceof JTextField) {

				JTextField txtField = (JTextField) component;
				if(txtField.getText().trim().equals("") && txtField.getName() != null){
					data.put(txtField.getName(),null);
				}
				if (!txtField.getText().trim().equals("") && txtField.getName() != null) {
					data.put(txtField.getName(), txtField.getText());
				}
			}else if(component instanceof JRadioButton){
				JRadioButton rb = (JRadioButton)component;
				if(rb.isSelected()){
					if(rb.getText().equalsIgnoreCase("da"))
						data.put(rb.getName(), "true");
					else
						data.put(rb.getName(), "false");
				}
			}
		}

		if (data.size() == 0)
			return null;

		return data;
	}

}
