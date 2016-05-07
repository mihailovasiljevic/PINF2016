package table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import database.TableDescription;
import form.Form;

public class MyTable extends JTable {

	private MyTableModel model;
	
	public MyTable(Form form) {
		
		this.model = new MyTableModel(form.getDescription());
		this.setModel(this.model);
		
		 //Dozvoljeno selektovanje redova
		 this.setRowSelectionAllowed(true);
		 //Ali ne i selektovanje kolona 
		 this.setColumnSelectionAllowed(false);
		 //Dozvoljeno selektovanje samo jednog reda u jedinici vremena 
		 this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 
		 this.setFillsViewportHeight(true);
		 this.getTableHeader().setReorderingAllowed(false);
		 //this.setCellSelectionEnabled(false);
		 this.setDefaultRenderer(Object.class, new MyTableRenderer(form));
		
		 
	}
	
	public void addInTable(Vector rowData) {
		model.addRow(rowData);
	}
	
}
