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


}
