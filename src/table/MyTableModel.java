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
	private String[] colNames;
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

	}

	public MyTableModel(Object[] colNames, int rowCount, String tableName) {
		super(colNames, rowCount);
		this.colNames = new String[colNames.length];
		initColNames(colNames);

		this.tableName = tableName;
		this.query = "SELECT * FROM " + tableName;
	}

	private void initColNames(Object[] colNames) {
		for (int i = 0; i < colNames.length; i++) {
			this.colNames[i] = (String) colNames[i];
		}
	}

	// otvaranje upita
	public void open() throws SQLException {
		fillData(tableName);
	}

	private void fillData(String tableName) throws SQLException {
		String[] colValues = new String[colNames.length];
		setRowCount(0);

		Statement stmt = DataBase.getConnection().createStatement();
		ResultSet rset = stmt.executeQuery(query);

		while (rset.next()) {
			for (int i = 0; i < colNames.length; i++) {
				colValues[i] = rset.getString(colNames[i]);
			}
			addRow(prepareRow(colValues));
		}

		rset.close();
		stmt.close();
		fireTableDataChanged();

		/*
		 * IQueryCreator createQuery = new ConcreteQueryCreator();
		 * LinkedHashMap<String, String> data = createQuery.read(tableName,
		 * whereCond, orderBy);
		 * 
		 * for (String dat : data.keySet()) {
		 * 
		 * String[][] row = new String[1][data.size()]; int i = 0; for (String
		 * datum : data.keySet()) { row[0][i] = data.get(datum); i++; }
		 * addRow(row); }
		 */
	}

	private String[][] prepareRow(String[] colValues) {
		String[][] row = new String[1][colValues.length];
		for (int i = 0; i < colValues.length; i++) {
			row[0][i] = colValues[i];
		}
		return row;
	}

}
