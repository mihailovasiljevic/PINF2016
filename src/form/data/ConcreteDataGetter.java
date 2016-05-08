package form.data;

import java.awt.Component;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
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
				if (txtField.getName() != null) {
					data.put(txtField.getName(), txtField.getText());
				}
			}
		}

		if (data.size() == 0)
			return null;

		return data;
	}

}
