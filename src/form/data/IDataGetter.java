package form.data;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;

public interface IDataGetter {
	HashMap<String, String> getData(JPanel dataPanel);

	Component getComponent(Component comp, @SuppressWarnings("rawtypes") Class c);
}
