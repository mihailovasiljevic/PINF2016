package table;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.TableDescription;
import form.Form;
public class MyTable extends JTable {

	private MyTableModel model;

	public MyTable(TableDescription tdescription) {
		
		String[] colNames = new String[tdescription.getColumnsDescriptions().size()];
		for (int i = 0; i < tdescription.getColumnsDescriptions().size(); i++) {
			colNames[i] = tdescription.getColumnsDescriptions().get(i).getCode();
		}
		this.model = new MyTableModel(tdescription);
		this.setModel(this.model);

		try {
			this.model.open();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Dozvoljeno selektovanje redova
		this.setRowSelectionAllowed(true);
		// Ali ne i selektovanje kolona
		this.setColumnSelectionAllowed(false);
		// Dozvoljeno selektovanje samo jednog reda u jedinici vremena
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
					return;
			//	sync();
			}
		});

		this.setFillsViewportHeight(true);
		this.getTableHeader().setReorderingAllowed(false);
		// this.setCellSelectionEnabled(false);

	}

	public void addInTable(Vector rowData) {
		model.addRow(rowData);
	}

	public MyTableModel getModel() {
		return model;
	}

	/*public void setModel(MyTableModel model) {
		this.model = model;
	}*/
	
	
	
	
	
	
	
	
	

}
