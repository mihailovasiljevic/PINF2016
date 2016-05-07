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

	public static TableDescription description;
	private DataPanel dataPanel;
	private MyTable table;
	private StatusBar statusBar;
	
	
	public Form(Window parent, TableDescription tdescription) {
		super(parent,tdescription.getLabel());
		
		setLayout(new MigLayout("fill"));
		this.setModal(true);
		this.description = tdescription;
		this.dataPanel=new DataPanel(description);
		this.statusBar = new StatusBar();
		this.init(tdescription);
		this.setLocationRelativeTo(parent);
		
	}
	
	public Form(){
		super();
	}
	
	private void init(TableDescription tdescription) {
		
		int width = 500 + (tdescription.getColumnsDescriptions().size()-2)*50;
		
		setSize(width, 400);
		this.add(new MyToolBar(this),"dock north");
		this.table = new MyTable(this.description);

		this.add(new TablePane(this.table),"grow, wrap");
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(dataPanel);
		bottomPanel.add(new ButtonsPanel(this),"dock east");
		
		add(bottomPanel, "grow, wrap");
		add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getCode());
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


	public DataPanel getDataPanel() {
		return dataPanel;
	}

	public void setDataPanel(DataPanel dataPanel) {
		this.dataPanel = dataPanel;
	}
	
	public MyTable getTable() {
		return table;
	}

	public void setTable(MyTable table) {
		this.table = table;

	}

}
