package form.data;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

public interface IDataGetter {
	HashMap<String, String> getData(JPanel dataPanel);
	JComponent getComponent(JComponent comp);
}
