package form;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.ColumnDescription;
import database.TableDescription;
import net.miginfocom.swing.MigLayout;

public class DataPanel extends JPanel {
	
	private Vector<JTextField> textFields = new Vector<JTextField>();
	
	private Vector<JButton> zoomBtns = new Vector<JButton>();
	public DataPanel(TableDescription description) {
		
		this.setLayout(new MigLayout("gapx 15px"));
		
		Vector<ColumnDescription> columnDescription = new Vector <ColumnDescription>();
		columnDescription = description.getColumnsDescriptions();
		
		
		for(int i=0; i<columnDescription.size(); i++){
			String lblText = columnDescription.get(i).getLabel()+":"+
					"["+columnDescription.get(i).getLength()+"]";
		
			if(!columnDescription.get(i).isNullable())
				lblText+="*";
			
			JLabel labela = new JLabel (lblText);
			this.add(labela);
			//DA LI BI DUZINE POLJA TREBALO DA SE RAZLIKUJU?
			int fieldLength=10;
			//if(columnDescription.get(i).getLength()>20)
			//	fieldLength=20;
			//Ispadne skrnavo...
			JTextField textField = new JTextField(fieldLength);
			textField.setName(columnDescription.get(i).getCode());
			textFields.add(textField);
			
			if (columnDescription.get(i).getTableParent() != null)
			{
				JButton zoomBtn = new JButton("...");
				zoomBtns.add(zoomBtn);
				this.add(textField);
				this.add(zoomBtn,"wrap");
			}
			
			else {
				this.add(textField,"wrap");
			}
		
		}
	}
	
	public Vector<JTextField> getTextFields() {
		return textFields;
	}
	public void setTextFields(Vector<JTextField> textFields) {
		this.textFields = textFields;
	}

}
