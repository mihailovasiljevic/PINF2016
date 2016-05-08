package table;

import java.sql.PreparedStatement;
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
import main.SortUtils;

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

	public void deleteRow(int index) throws SQLException {
		checkRow(index);
		// assumption: first column in list of column is always primary key or
		// semantic unique identifier!
		String query = "DELETE FROM " + tdescription.getCode() + " WHERE "
				+ tdescription.getColumnsDescriptions().get(0).getCode() + " = ?";
		PreparedStatement stmt = DataBase.getConnection().prepareStatement(query);
		String _id = (String) getValueAt(index, 0);
		// Deleting from the database
		int rowsAffected = stmt.executeUpdate();
		stmt.close();
		DataBase.getConnection().commit();
		// Delete from the table model only if deletion from the database was
		// successful.
		if (rowsAffected > 0) {
			removeRow(index);
			fireTableDataChanged();
		}

	}

	public int insertRow(LinkedHashMap<String, String> data) throws SQLException {
		int retVal = 0;
		String query = makeInsertQuery();
		return retVal;
	}
	
	private String makeInsertQuery(){
		return null;
	}

	public void updateRow(int index, LinkedHashMap<String, String> data) throws SQLException {

	}

	private static final int CUSTOM_ERROR_CODE = 50000;
	private static final String ERROR_RECORD_WAS_CHANGED = "Slog je promenjen od strane drugog korisnika. Molim vas, pogledajte njegovu trenutnu vrednost";
	private static final String ERROR_RECORD_WAS_DELETED = "Slog je obrisan od strane drugog korisnika";

	private void checkRow(int index) throws SQLException {
		DataBase.getConnection().setTransactionIsolation(DataBase.getConnection().TRANSACTION_REPEATABLE_READ);
		// assumption: first column in list of column is always primary key or
		// semantic unique identifier!
		String sql = query + " WHERE " + tdescription.getColumnsDescriptions().get(0).getCode() + " = ?";
		PreparedStatement selectStmt = DataBase.getConnection().prepareStatement(sql);
		String _id = (String) getValueAt(index, 0);
		selectStmt.setString(1, _id);

		ResultSet rset = selectStmt.executeQuery();
		String[] newValues = new String[tdescription.getColumnsDescriptions().size()];
		Boolean exists = false;
		String errorMsg = "";

		while (rset.next()) {
			for (int i = 0; i < tdescription.getColumnsDescriptions().size(); i++) {
				newValues[i] = rset.getString(tdescription.getColumnsDescriptions().get(i).getCode());
			}
			exists = true;
		}
		if (!exists) {
			removeRow(index);
			fireTableDataChanged();
			errorMsg = ERROR_RECORD_WAS_DELETED;
		} else if (!identicalValues(newValues, index)) {
			for (int i = 0; i < newValues.length; i++) {
				setValueAt(newValues[i], index, i);
			}
		}
		rset.close();
		selectStmt.close();
		DataBase.getConnection().setTransactionIsolation(DataBase.getConnection().TRANSACTION_READ_COMMITTED);
		if (errorMsg != "") {
			DataBase.getConnection().commit();
			throw new SQLException(errorMsg, "", CUSTOM_ERROR_CODE);
		}
	}

	private Boolean identicalValues(String[] newValues, int index) {
		boolean retVal = true;
		for (int i = 0; i < newValues.length; i++) {
			if ((SortUtils.getLatCyrCollator().compare(newValues[i], ((String) getValueAt(index, i)).trim()) != 0)) {
				retVal = false;
				break;
			}
		}
		return retVal;
	}

	private int sortedInsert(LinkedHashMap<String, String> data) {
		return -1;
	}

}
