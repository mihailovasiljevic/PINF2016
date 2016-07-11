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
	
	public MyTableModel(Object[] colNames, int rowCount) {
		super(colNames, rowCount);
	}

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


	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
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
		stmt.setString(1,_id);
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
		final String TYPE = "INSERT";
		String query = makeInsertQuery(data, tdescription.getCode(), TYPE);
		PreparedStatement stmt = DataBase.getConnection().prepareStatement(query);

		int i = 1;
		for (String key : data.keySet()) {
			stmt.setString(i, data.get(key));
			i++;
		}

		int rowsAffected = stmt.executeUpdate();
		stmt.close();
		// inserting into database
		DataBase.getConnection().commit();
		// if it is inserted into database make changes to tablemodel
		if (rowsAffected > 0) {
			retVal = sortedInsert(data);
			fireTableDataChanged();
		}
		return retVal;
	}

	private String makeInsertQuery(LinkedHashMap<String, String> data, String tableName, String type) {
		if (type.equals("INSERT")) {
			String query = "INSERT INTO " + tableName + " ( ";
			for (String key : data.keySet()) {
				query += key + ", ";
			}
			query = query.substring(0, query.length() - 2);
			query += " ) VALUES ( ";
			for (int i = 0; i < data.keySet().size(); i++) {
				query += "?, ";
			}
			query = query.substring(0, query.length() - 2);
			query += " );";
			return query;
		} else {
			String query = "UPDATE " + tableName + " SET ";
			boolean isFirst = true;
			String _id = "";
			for (String key : data.keySet()) {
				if (isFirst) {
					_id = key;
					isFirst = false;
					continue;
				}
				query += key + " = ?, ";
			}
			query = query.substring(0, query.length() - 2);
			query += " WHERE " + _id + " = ?";

			return query;
		}
	}

	private int sortedInsert(LinkedHashMap<String, String> data) {
		LinkedHashMap<String, String> dataCopy = new LinkedHashMap<>();
		dataCopy.putAll(data);
		String _id = dataCopy.keySet().iterator().next();
		int left = 0;
		int right = getRowCount() - 1;
		int mid = (left + right) / 2;
		while (left <= right) {
			mid = (left + right) / 2;
			String _aID = (String) getValueAt(mid, 0);
			if (SortUtils.getLatCyrCollator().compare(_id, _aID) > 0)
				left = mid + 1;
			else if (SortUtils.getLatCyrCollator().compare(_id, _aID) < 0)
				right = mid - 1;
			break;
		}
		String[] colValues = new String[data.size()];
		int i = 0;
		for (String key : data.keySet()) {
			colValues[i] = data.get(key);
			i++;
		}
		insertRow(left, prepareRow(colValues));
		return left;
	}

	public void updateRow(int index, LinkedHashMap<String, String> data) throws SQLException {
		checkRow(index);
		final String TYPE = "UPDATE";
		String query = makeInsertQuery(data, tdescription.getCode(), TYPE);
		PreparedStatement stmt = DataBase.getConnection().prepareStatement(query);

		boolean isFirst = true;
		int i = 1;
		String _id = "";
		for (String key : data.keySet()) {
			if (isFirst) {
				_id = data.get(key);
				isFirst = false;
				continue;
			}
			stmt.setString(i, data.get(key));
			i++;
		}
		stmt.setString(i, _id);
		int rowsAffected = stmt.executeUpdate();
		stmt.close();
		DataBase.getConnection().commit();
		// check if update successfuly passed
		if (rowsAffected > 0)
			fireTableDataChanged();
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
		try{
			String _id = (String) getValueAt(index, 0);
			selectStmt.setString(1, _id);
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Tabela je prazna nema s cim da se poredi.");
		}
		

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
			try{
				if ((SortUtils.getLatCyrCollator().compare(newValues[i], ((String) getValueAt(index, i)).trim()) != 0)) {
					retVal = false;
					break;
				}
			}catch (Exception e) {
				System.out.println("Null vrednost u koloni: " + e.getMessage());
			}
		}
		return retVal;
	}

	public TableDescription getTdescription() {
		return tdescription;
	}

	public void setTdescription(TableDescription tdescription) {
		this.tdescription = tdescription;
	}
	
	

	// method tester
	/*
	 * public static void main(String[] args) { LinkedHashMap<String, String>
	 * data = new LinkedHashMap<>(); data.put("DRZAVA", "SRB");
	 * data.put("NAZIV", "SRBIJA"); System.out.println(makeInsertQuery(data,
	 * "DRZAVA_TABLE", "UPDATE")); }
	 */
}
