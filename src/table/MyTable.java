package table;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import database.TableDescription;

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

		this.setFillsViewportHeight(true);
		this.getTableHeader().setReorderingAllowed(false);
		// this.setCellSelectionEnabled(false);

	}

	public void addInTable(Vector rowData) {
		model.addRow(rowData);
	}

}
