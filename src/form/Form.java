package form;

import java.awt.Window;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MyToolBar;
import net.miginfocom.swing.MigLayout;
import table.MyTable;
import database.TableDescription;

public class Form extends JDialog {

	public static TableDescription description;
	private DataPanel dataPanel;
	private MyTable table;
	private StatusBar statusBar;
	private JTextField field;
	private String code;
	private FormState state;
	
	public Form(Form parent, TableDescription tdescription, JTextField field, String code) {
		super(parent,tdescription.getLabel());
		this.setModal(true);
		this.description = tdescription;
		this.field = field;
		this.code = code;
		this.init(tdescription);
		this.setLocationRelativeTo(parent);
	}
	
	public Form(Window parent, TableDescription tdescription) {
		super(parent,tdescription.getLabel());
		this.setModal(true);
		this.description = tdescription;
		this.init(tdescription);
		this.setLocationRelativeTo(parent);
	}
	
	public Form() {
		super();
	}
	
	private void init(TableDescription tdescription) {
		
		int width = 500 + (tdescription.getColumnsDescriptions().size()-2)*50;
		
		setSize(width, 400);
		setLayout(new MigLayout("fill"));
		this.add(new MyToolBar(this),"dock north");
		this.table = new MyTable(this);

		this.add(new TablePane(this.table),"grow, wrap");
		
		this.dataPanel=new DataPanel(description);
		this.statusBar = new StatusBar();
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(dataPanel);
		bottomPanel.add(new ButtonsPanel(this),"dock east");
		
		add(bottomPanel, "grow, wrap");

		this.state = FormState.Izmena;
		
		add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getLabel());

		
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

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public FormState getState() {
		return state;
	}

	public void setState(FormState state) {
		this.state = state;
	}
	
	

}
