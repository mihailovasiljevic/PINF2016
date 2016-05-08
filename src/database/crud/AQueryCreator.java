package database.crud;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import database.DataBase;

public abstract class AQueryCreator implements IQueryCreator {

	@Override
	public void checkRow(HashMap<String, String> data, String tableName, HashMap<String, String> whereCond,
			String orderByCond, int index) throws SQLException {
		String basicQuery = "SELECT * FROM " + tableName;
		String where = " WHERE";

		// DataBase.getConnection().setTransactionIsolation(DataBase.getConnection().prepareStatement(sql));
	}

	@Override
	public String whereStatementMaker(LinkedHashMap<String, Object> whereCond) {
		for (String key : whereCond.keySet()) {
			// if(whereCond.get(key) instanceof Double)
		}
		return null;
	}

	@Override
	public ArrayList<String> getColumnNames(String tableName) throws SQLException {

		ArrayList<String> columnNames = new ArrayList<>();
		DatabaseMetaData dbMetaData = DataBase.getConnection().getMetaData();
		ResultSet rset = dbMetaData.getColumns(null, null, tableName, null);

		while (rset.next()) {
			String columnName = rset.getString("COLUMN_NAME");
			columnNames.add(columnName);
		}
		rset.close();
		return columnNames;
	}

	@Override
	public ArrayList<String> getColumnTypes(String tableName) throws SQLException {
		ArrayList<String> columnTypes = new ArrayList<>();
		DatabaseMetaData dbMetaData = DataBase.getConnection().getMetaData();
		ResultSet rset = dbMetaData.getColumns(null, null, tableName, null);

		while (rset.next()) {
			String columnType = rset.getString("TYPE_NAME");
			columnTypes.add(columnType);
		}
		rset.close();
		return columnTypes;
	}

	@Override
	public ArrayList<Integer> getColumnSizes(String tableName) throws SQLException {
		ArrayList<Integer> columnSizes = new ArrayList<>();
		DatabaseMetaData dbMetaData = DataBase.getConnection().getMetaData();
		ResultSet rset = dbMetaData.getColumns(null, null, tableName, null);

		while (rset.next()) {
			Integer columnSize = Integer.parseInt(rset.getString("COLUMN_SIZE"));
			columnSizes.add(columnSize);
		}
		rset.close();
		return columnSizes;
	}

}
