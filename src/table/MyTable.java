package table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import database.TableDescription;
import database.crud.ConcreteQueryCreator;
import database.crud.IQueryCreator;

public class MyTable extends JTable {

	private MyTableModel model;

	public MyTable(TableDescription tdescription) {

		IQueryCreator qc = new ConcreteQueryCreator();
		try {
			ArrayList<String> colNames = qc.getColumnNames(tdescription.getCode());
			if(colNames == null)
				return;
			
			this.model = new MyTableModel(colNames.toArray(), 0, tdescription.getCode());
			this.setModel(this.model);
			this.model.open();
			
			// Dozvoljeno selektovanje redova
			this.setRowSelectionAllowed(true);
			// Ali ne i selektovanje kolona
			this.setColumnSelectionAllowed(false);
			// Dozvoljeno selektovanje samo jednog reda u jedinici vremena
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			this.setFillsViewportHeight(true);
			this.getTableHeader().setReorderingAllowed(false);
			// this.setCellSelectionEnabled(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addInTable(Vector rowData) {
		model.addRow(rowData);
	}

}
