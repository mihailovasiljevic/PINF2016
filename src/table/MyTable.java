package table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import database.TableDescription;

public class MyTable extends JTable {

	public MyTable(TableDescription tdescription) {
		
		this.setModel(new MyTableModel(tdescription));
		
		 //Dozvoljeno selektovanje redova
		 this.setRowSelectionAllowed(true);
		 //Ali ne i selektovanje kolona 
		 this.setColumnSelectionAllowed(false);
		 //Dozvoljeno selektovanje samo jednog reda u jedinici vremena 
		 this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 this.setFillsViewportHeight(true);
		
	}
	
}
