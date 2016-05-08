package database.crud;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import database.DataBase;

public class ConcreteQueryCreator extends AQueryCreator {

	@Override
	public LinkedHashMap<String, String> read(String tableName, String whereCond, String orderByCond) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM ?" + whereCond + orderByCond;
		ArrayList<String> columnValues = new ArrayList<>();
		ArrayList<String> columnNames = getColumnNames(tableName);
		LinkedHashMap<String, String> retVal = new LinkedHashMap<>();
		
		if (columnNames == null)
			return null;
		
		PreparedStatement stmt = DataBase.getConnection().prepareStatement(query);
		stmt.setString(1, tableName);

		ResultSet rs = stmt.executeQuery();
		
		int counter = 0;
		
		while(rs.next()){
			String columnValue = rs.getString(columnNames.get(counter));
			columnValues.add(columnValue);
			counter++;
		}
		
		for(int i = 0; i < columnNames.size(); i++){
			retVal.put(columnNames.get(i), columnValues.get(i));
		}
		
		rs.close();
		return retVal;
	}

	@Override
	public int create(HashMap<String, String> data) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HashMap<String, String> data, int index) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int index) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
