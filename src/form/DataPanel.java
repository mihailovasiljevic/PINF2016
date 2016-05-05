package form;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import button.actions.MenuBarButtonAction;
import button.actions.ZoomButtonAction;
import database.ColumnDescription;
import database.TableDescription;
import main.MyMenuBar;
import net.miginfocom.swing.MigLayout;

public class DataPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JTextField> textFields = new Vector<JTextField>();
	private Vector<JButton> zoomBtns = new Vector<JButton>();
	@SuppressWarnings({ "static-access", "unused" })
	public DataPanel(TableDescription description) {
		
		this.setLayout(new MigLayout("gapx 15px"));
		
		Vector<ColumnDescription> columnDescription = new Vector <ColumnDescription>();
		columnDescription = description.getColumnsDescriptions();
		MyMenuBar mbar=new MyMenuBar();
		int size=mbar.gettDescriptions().size();
		System.out.print(size);
		
		
		for(int i=0; i<columnDescription.size(); i++){
			JLabel labela = new JLabel (columnDescription.get(i).getLabel()+":");
			this.add(labela);
			//DA LI BI DUZINE POLJA TREBALO DA SE RAZLIKUJU?
			JTextField textField = new JTextField(10);
			textFields.add(textField);
			
			if (columnDescription.get(i).getTableParent() != null)
			{
				String m=columnDescription.get(i).getTableParent();
				System.out.print(m);
				System.out.print("aa  "+description.getCode());
		
				JButton zoomBtn = new JButton("...");
				for(int k=0;k<mbar.gettDescriptions().size();k++){
					if(mbar.gettDescriptions().get(k).getCode().contains(m)){
						TableDescription table_zoom=mbar.gettDescriptions().get(k);
						System.out.print(table_zoom.getCode());
						zoomBtn.addActionListener(new ZoomButtonAction(table_zoom));
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
