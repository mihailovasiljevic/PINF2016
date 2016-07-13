package form;

import java.awt.Color;
import java.awt.Window;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.MainFrame;
import main.MyToolBar;
import net.miginfocom.swing.MigLayout;
import states.Context;
import states.State;
import states.UpdateState;
import table.MyTable;
import table.TableSelection;
import table.MyTableModel;
import database.ColumnDescription;
import database.DataBase;
import database.TableDescription;

public class Form extends JDialog {

	private TableDescription description;
	private DataPanel dataPanel;
	private MyTable table;
//	private StatusBar statusBar;
	private JTextField field;
	private String code;

	private static final int MODE_EDIT = 1;
	private static final int MODE_ADD = 2;
	private static final int MODE_SEARCH = 3;
	private static int mode;
	private static StatusBar statusBar;
	
	private ButtonsPanel buttonPanel;

	public static int getMode() {
		return mode;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
		statusBar.init();
	}

	private FormState state;
	private Form parentForm;
	private MyTableModel mytmod;
	private MyToolBar toolbar;

	private ButtonsPanel buttonsPanel;

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
		

		this.table = new MyTable(this.getDescription());

		this.add(new TablePane(this.table),"grow, wrap");

		this.dataPanel=new DataPanel(description,this);
		statusBar = new StatusBar();

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(dataPanel);
		boolean isPrometni = false;
		if(tdescription.getCode().equalsIgnoreCase("PROMETNI_DOKUMENT"))
			isPrometni = true;

		setButtonsPanel(new ButtonsPanel(this,isPrometni));
		bottomPanel.add(buttonsPanel, "dock east");

		add(bottomPanel, "grow, wrap");


		//	this.state = FormState.Izmena;

		/*add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getLabel());*/

		//select first row, that will be starting state
		try{
			this.table.addRowSelectionInterval(0, 0);
			Context context = MainFrame.getInstance().getContext();
			State updateState = new UpdateState();
			updateState.sync(context, this);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			Context context = MainFrame.getInstance().getContext();
			context.getState().setEditable(this, false);
		}
		this.toolbar = new MyToolBar(this);
		this.add(this.toolbar,"dock north");
		this.table.getSelectionModel().addListSelectionListener(new TableSelection(this));
		add(statusBar, "dock south");
		statusBar.getStatLab1().setText(description.getLabel());
		statusBar.getStatLab2().setText("Rezim za izmenu");

		if(this.getTitle().equalsIgnoreCase("poslovna godina")) {
			Enumeration<AbstractButton> enumeration = this.getDataPanel().getBtnGroups().get(0).getElements();
			while (enumeration.hasMoreElements()) {
			    while(enumeration.hasMoreElements()) {
			    	enumeration.nextElement().setEnabled(false);
			    }
			}
			int selected = this.getTable().getSelectedRow();
			if(selected != -1) {
				String val = (String) this.getTable().getModel().getValueAt(selected, 5);
				if(val.equals("1")) {
					this.getButtonsPanel().getCloseYear().setEnabled(false);
				}
			}
		}
		
	}

	public void nextFilter(String sifra,String column) throws SQLException{

		int br_redova= this.table.getRowCount();
		


		for(int i=0;i<br_redova;i++){
			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(description.getCode());
			for(int j = 0; j < cdescription.size(); j++) {
				String provera = (String) this.table.getValueAt(i, j);
				System.out.print("  PRV:"+provera);
				
				boolean strani_kluc=false;

				if(cdescription.get(j).getCode().contains(column))
				strani_kluc=true;


				if(strani_kluc){
					
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

	public void refresh(int index) throws SQLException{

		MyTableModel tableModel = (MyTableModel)table.getModel();
		table.setModel(tableModel);

		try {
			tableModel.open();
			MainFrame.getInstance().getContext().getState().sync(MainFrame.getInstance().getContext(), this);
			table.setRowSelectionInterval(index, index);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);			
		} 


	}

	public MyTableModel getMytmod() {
		return mytmod;
	}

	public void setMytmod(MyTableModel mytmod) {
		this.mytmod = mytmod;
	}

	public void setButtonPanel(ButtonsPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	
	public ButtonsPanel getButtonPanel() {
		return buttonPanel;
	}

	public ButtonsPanel getButtonsPanel() {
		return buttonsPanel;
	}

	public void setButtonsPanel(ButtonsPanel buttonsPanel) {
		this.buttonsPanel = buttonsPanel;
	}

}
