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



public class TablePane extends JScrollPane {
	
	private JTable tblGrid = new JTable();

	public TablePane(TableDescription tdescription) {
		// TODO Auto-generated constructor stub
		//tblGrid = new JTable();
		MyTable tblGrid = new MyTable(tdescription); 
		this.setViewportView(tblGrid);
		
		 //MyTableModel tableModel = new MyTableModel();
		      //tblGrid.setModel(tableModel);

		      //tableModel.open(); 

			  tblGrid.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			          if (e.getValueIsAdjusting())
						 return;
			        }
			     });
		
		
	}

}
