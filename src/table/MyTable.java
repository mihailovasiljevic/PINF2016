package table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import database.TableDescription;

public class MyTable extends JTable {

	private MyTableModel model;
	
	public MyTable(TableDescription tdescription) {
		
		this.model = new MyTableModel(tdescription);
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
		 
	}
	
	public void addInTable(Vector rowData) {
		model.addRow(rowData);
	}
	
}
