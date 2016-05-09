package form;

import java.awt.Color;
import java.awt.Window;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MainFrame;
import main.MyToolBar;
import net.miginfocom.swing.MigLayout;
import states.Context;
import states.State;
import states.UpdateState;
import table.MyTable;
<<<<<<< HEAD
import table.TableSelection;
=======
import table.MyTableModel;
import database.ColumnDescription;
import database.DataBase;
>>>>>>> origin/master
import database.TableDescription;

public class Form extends JDialog {

	private TableDescription description;
	private DataPanel dataPanel;
	private MyTable table;
	private StatusBar statusBar;
	private JTextField field;
	private String code;
<<<<<<< HEAD
	//private FormState state;
=======
	private FormState state;
	private Form parentForm;
	private MyTableModel mytmod;
	private MyToolBar toolbar;
>>>>>>> origin/master
	
	public Form(Window parent, TableDescription tdescription, JTextField field, String code) {
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
		this.toolbar.disablePick();
		this.setLocationRelativeTo(parent);
	}

	public Form() {
		super();
	}

	private void init(TableDescription tdescription) {

		int width = 500 + (tdescription.getColumnsDescriptions().size()-2)*50;

		setSize(width, 400);
		setLayout(new MigLayout("fill"));
		this.toolbar = new MyToolBar(this);
		this.add(this.toolbar,"dock north");
		this.table = new MyTable(this.getDescription());

		this.add(new TablePane(this.table),"grow, wrap");

		this.dataPanel=new DataPanel(description,this);
		this.statusBar = new StatusBar();

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(dataPanel);
		bottomPanel.add(new ButtonsPanel(this),"dock east");

		add(bottomPanel, "grow, wrap");

<<<<<<< HEAD
	//	this.state = FormState.Izmena;
		
		add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getLabel());

		//select first row, that will be starting state
		try{
			this.table.addRowSelectionInterval(0, 0);
			Context context = MainFrame.getInstance().getContext();
			State updateState = new UpdateState();
			updateState.sync(context, this);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		this.table.getSelectionModel().addListSelectionListener(new TableSelection(this));
=======
		this.state = FormState.Izmena;

		add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getLabel());


>>>>>>> origin/master
	}

	public void nextFilter(String sifra){

		int br_redova= this.table.getRowCount();


		for(int i=0;i<br_redova;i++){
			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(description.getCode());
			for(int j = 0; j < cdescription.size(); j++) {
				String provera = (String) this.table.getValueAt(i, j);
				System.out.print("  PRV:"+provera);
				boolean primarni_kljuc=DataBase.isPrimaryKey(description.getCode(),cdescription.get(j).getCode());
				boolean strani_kljuc=DataBase.isForeignKey(description.getCode(),cdescription.get(j).getCode());

				if(strani_kljuc && primarni_kljuc==false){

					if(provera!=null){
						if(!provera.contains(sifra)){
							System.out.print("remove ");
							MyTableModel dtm = this.table.getModel();
							dtm.removeRow(i);
							i--;
							br_redova--;
							break;
						}
					}
				}

				int rowCount = this.table.getRowCount();

				if(rowCount>0){
					this.table.setRowSelectionInterval(0,0);
				}

			}
		}
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
/*
	public FormState getState() {
		return state;
	}

	public void setState(FormState state) {
		this.state = state;
	}
<<<<<<< HEAD
*/	
	
=======

	public void refresh() throws SQLException{

		MyTableModel tableModel = new MyTableModel(description);
		table.setModel(tableModel);

		try {
			tableModel.open();
		} catch (SQLException e) {
			e.printStackTrace();
		} 


	}



>>>>>>> origin/master

}
