package form;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.Description;
import net.miginfocom.swing.MigLayout;

public class DataPanel extends JPanel {
	
	private Vector<String> columns = new Vector<String>();
	private Vector<JTextField> textFields = new Vector<JTextField>();
	public DataPanel(Description description) {
		
		this.setLayout(new MigLayout("gapx 15px"));
		
		Vector<String> foreignKeyColumns = new Vector <String>();
		columns = description.getColumns();
		foreignKeyColumns = description.getForeignColumns();
		
		for(int i=0; i<columns.size(); i++){
			JLabel labela = new JLabel (columns.get(i)+":");
			this.add(labela);
			//DA LI BI DUZINE POLJA TREBALO DA SE RAZLIKUJU?
			JTextField textField = new JTextField(10);
			textFields.add(textField);
			this.add(textField,"wrap");
		
		}
		
		for(int i=0; i<foreignKeyColumns.size(); i++){
			System.out.println(foreignKeyColumns.get(i));
			
			}
		
	}

}
