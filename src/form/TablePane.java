package form;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.TableDescription;
import table.MyTable;
import table.MyTableModel;
import table.TableSelection;



public class TablePane extends JScrollPane {
	
	private JTable tblGrid = new JTable();

	public TablePane(JTable table) {

		this.tblGrid = table;
		this.setViewportView(tblGrid);

		
	}

}
