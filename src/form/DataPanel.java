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
			JLabel labela = new JLabel (columnDescription.get(i).getCode()+":");
			this.add(labela);
			//DA LI BI DUZINE POLJA TREBALO DA SE RAZLIKUJU?
			JTextField textField = new JTextField(10);
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

}
