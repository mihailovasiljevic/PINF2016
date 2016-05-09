package form;

import java.awt.Component;
import java.awt.Dimension;
import java.text.Format;
import java.util.Formatter;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import button.actions.MenuBarButtonAction;
import button.actions.PickDateAction;
import button.actions.ZoomButtonAction;
import database.ColumnDescription;
import database.TableDescription;
import main.MainFrame;
import main.MyMenuBar;
import net.miginfocom.swing.MigLayout;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JTextField> textFields = new Vector<JTextField>();
	private Vector<ColumnDescription> columnDescription=new Vector<ColumnDescription>();
	private Vector<JButton> zoomBtns = new Vector<JButton>();
	private Vector<ButtonGroup> btnGroups = new Vector<ButtonGroup>();
	

	public DataPanel(TableDescription description) {
		
		this.setLayout(new MigLayout("gapx 15px"));
		
		
		columnDescription = description.getColumnsDescriptions();
		int size=MyMenuBar.tDescriptions.size();
		System.out.print(size);
		
		
		for(int i=0; i<columnDescription.size(); i++){
			String lblText = columnDescription.get(i).getLabel()+
					"["+columnDescription.get(i).getLength()+"]:";
		
			if(!columnDescription.get(i).isNullable())
				lblText+="*";
			
			JLabel labela = new JLabel (lblText);
			this.add(labela);
			//DA LI BI DUZINE POLJA TREBALO DA SE RAZLIKUJU?
			int fieldLength=10;
			//if(columnDescription.get(i).getLength()>20)
			//	fieldLength=20;
			//Ispadne skrnavo...
			JTextField textField;
			
			//POSTOJE LI JOS NEKI DATUMSKI TIPOVI?
			if(columnDescription.get(i).getType().equalsIgnoreCase("datetime"))
			{
				textField = new JTextField(fieldLength-3);
				textField.setName(columnDescription.get(i).getCode());
				textFields.add(textField);
				JButton datePickBtn = new JButton ("...");
				datePickBtn.addActionListener(new PickDateAction(textField));
				
			          
				
				
				if (columnDescription.get(i).getTableParent() != null)
				{
					String m=columnDescription.get(i).getTableParent();
			
					//primeceno da Sluzba ima dva strana kljuca,treba ispraviti
					JButton zoomBtn = new JButton("...");
					for(int k=0;k<MyMenuBar.tDescriptions.size();k++){
						if(MyMenuBar.tDescriptions.get(k).getCode().contains(m)){
							TableDescription table_zoom=MyMenuBar.tDescriptions.get(k);
							zoomBtn.addActionListener(new ZoomButtonAction(table_zoom,textField,table_zoom.getColumnsDescriptions().get(k)));
						}
	
					}
					
					zoomBtns.add(zoomBtn);
					this.add(textField,"split 2");
					this.add(datePickBtn,"w 22!, h 22!");
					this.add(zoomBtn,"wrap");
			}
				else {
					this.add(textField,"split 2");
					this.add(datePickBtn,"wrap, w 22!, h 22!");
				}
			}
				
			
			else{
			
				if(columnDescription.get(i).getType().equalsIgnoreCase("bit"))
				{
					ButtonGroup btnGroup = new ButtonGroup();
					JRadioButton rBtnTrue = new JRadioButton();
					rBtnTrue.setText("Da");
					JRadioButton rBtnFalse = new JRadioButton();
					rBtnFalse.setText("Ne");
					rBtnTrue.setName(columnDescription.get(i).getCode());
					rBtnFalse.setName(columnDescription.get(i).getCode());
					btnGroup.add(rBtnTrue);
					btnGroup.add(rBtnFalse);
					btnGroups.add(btnGroup);
					this.add(rBtnTrue);
					this.add(rBtnFalse, "wrap");
					
				}
				
				else{
				
					textField = new JTextField(fieldLength);
					textField.setName(columnDescription.get(i).getCode());
					textFields.add(textField);
					
					if (columnDescription.get(i).getTableParent() != null)
					{
						String m=columnDescription.get(i).getTableParent();
				
						//primeceno da Sluzba ima dva strana kljuca,treba ispraviti
						JButton zoomBtn = new JButton("...");
						for(int k=0;k<MyMenuBar.tDescriptions.size();k++){
							if(MyMenuBar.tDescriptions.get(k).getCode().contains(m)){
								TableDescription table_zoom=MyMenuBar.tDescriptions.get(k);
								zoomBtn.addActionListener(new ZoomButtonAction(table_zoom,textField,table_zoom.getColumnsDescriptions().get(k)));
							}
		
						}
						
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
	}
	
	public JTextField getField(String code) {
		for(int i = 0; i < textFields.size(); i++) {
			if(textFields.get(i).getName().equals(code)) {
				return textFields.get(i);
			}
		}
		return null;
	}
	
	public Vector<JTextField> getTextFields() {
		return textFields;
	}
	public void setTextFields(Vector<JTextField> textFields) {
		this.textFields = textFields;
	}
	
	public Vector<ColumnDescription> getColumnDescription() {
		return columnDescription;
	}

	public void setColumnDescription(Vector<ColumnDescription> columnDescription) {
		this.columnDescription = columnDescription;
	}
	
	public Vector<ButtonGroup> getBtnGroups() {
		return btnGroups;
	}

	public void setBtnGroups(Vector<ButtonGroup> btnGroups) {
		this.btnGroups = btnGroups;
	}
}
