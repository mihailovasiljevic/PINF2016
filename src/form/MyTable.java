package form;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.MyTableModel;



public class MyTable extends JScrollPane {
	
	private JTable tblGrid = new JTable();

	public MyTable() {
		// TODO Auto-generated constructor stub
		tblGrid = new JTable();
		this.setViewportView(tblGrid);
		
		   MyTableModel tableModel = new MyTableModel();
		      tblGrid.setModel(tableModel);

		      //tableModel.open(); 

		      //Dozvoljeno selektovanje redova
		      tblGrid.setRowSelectionAllowed(true);
		      //Ali ne i selektovanje kolona 
		      tblGrid.setColumnSelectionAllowed(false);

		      //Dozvoljeno selektovanje samo jednog reda u jedinici vremena 
		      tblGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			  tblGrid.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			          if (e.getValueIsAdjusting())
						 return;
			          //sync();
			        }
			     });
		
		
	}

}
