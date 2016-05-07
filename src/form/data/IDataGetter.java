package form.data;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;
/**
 * 
 * @author Mihailo Vasiljevic RA4/2012
 * This interface is collection of methods that are going to be used for extracting data from the forms.
 * It is consisted of methods that are same for all classes that are going to implement this interface.
 * General recommendation is to be implemented by Abstract class first. 
 */
public interface IDataGetter {
	/**
	 * This method is intended to be used as a main method for data extraction from the form itself.
	 * It takes panel that is consisted of form components and returns pairs COLUMN_NAME:COLUMN_VALUE.
	 * The implementation may vary. For implementation details @see {@link ConcreteDataGetter}. 
	 * @param dataPanel
	 * @return pairs COLUMN_NAME:COLUMN_VALUE.
	 */
	HashMap<String, String> getData(JPanel dataPanel);
	
	/**
	 * This method is intended to be used as a component finder. 
	 * Finding is based on in which component it should be done and what component is somebody looking for.
	 * For implementation details @see {@link DataGetter}
	 * @param comp
	 * @param c
	 * @return component that is somebody looked for.
	 */
	Component getComponent(Component comp, @SuppressWarnings("rawtypes") Class c);
}
