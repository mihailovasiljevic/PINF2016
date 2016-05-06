package form;

import java.awt.Window;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;

import main.MyToolBar;
import net.miginfocom.swing.MigLayout;
import table.MyTable;
import database.TableDescription;

public class Form extends JDialog {

	private TableDescription description;
	private MyTable table;
	
	public Form(Window parent, TableDescription tdescription) {
		super(parent,tdescription.getLabel());
		
		setLayout(new MigLayout("fill"));
		this.setModal(true);
		this.description = tdescription;
		
		this.init(tdescription);
		this.setLocationRelativeTo(parent);
	}
	
	private void init(TableDescription tdescription) {
		
		int width = 500 + (tdescription.getColumnsDescriptions().size()-2)*50;
		
		setSize(width, 400);
		this.add(new MyToolBar(this),"dock north");
		this.table = new MyTable(this.description);

		this.add(new TablePane(this.table),"grow, wrap");
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(new DataPanel(description));
		bottomPanel.add(new ButtonsPanel(),"dock east");
		
		add(bottomPanel, "grow, wrap");
		/*Vector vect = new Vector();
		vect.add("A"); vect.add("B");
		table.addInTable(vect);*/
		
	}


	
	//GETTERS AND SETTERS
	public TableDescription getDescription() {
		return description;
	}

	public void setDescription(TableDescription description) {
		this.description = description;
	}

	public MyTable getTable() {
		return table;
	}

	public void setTable(MyTable table) {
		this.table = table;
	}

}
