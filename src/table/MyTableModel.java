package table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.table.DefaultTableModel;

import database.DataBase;
import database.TableDescription;
import database.crud.ConcreteQueryCreator;
import database.crud.IQueryCreator;
import javafx.scene.chart.PieChart.Data;

public class MyTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4186581278768828397L;
	private String tableName;
	private String whereCond;
	private String orderBy;
	private TableDescription tdescription;
	private String query;

	public MyTableModel(TableDescription tdescription) {
		
		if (tdescription == null) {
			System.out.println("null");
		}

		if (tdescription.getColumnsDescriptions() == null) {
			System.out.println("null");
		}
		for (int i = 0; i < tdescription.getColumnsDescriptions().size(); i++) {

			this.addColumn(tdescription.getColumnsDescriptions().get(i).getLabel());

		}
		this.tdescription = tdescription;
		this.tableName = tdescription.getCode();
		this.query = "SELECT * FROM " + tableName;
	}

	// otvaranje upita
	public void open() throws SQLException {
		fillData(tableName);
	}

	private void fillData(String tableName) throws SQLException {
		String[] colValues = new String[tdescription.getColumnsDescriptions().size()];
		setRowCount(0);

		Statement stmt = DataBase.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			for (int i = 0; i < tdescription.getColumnsDescriptions().size(); i++) {
				colValues[i] = rset.getString(tdescription.getColumnsDescriptions().get(i).getCode());
			}
			addRow(prepareRow(colValues));
		}

		rset.close();
		stmt.close();
		fireTableDataChanged();

	}

	private String[] prepareRow(String[] colValues) {
		String[] row = new String[colValues.length];
		for (int i = 0; i < colValues.length; i++) {
			row[i] = colValues[i];
		}
		return row;
	}

}
