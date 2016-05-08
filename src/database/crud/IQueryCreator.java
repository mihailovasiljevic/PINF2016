package database.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public interface IQueryCreator {
	LinkedHashMap<String, String> read(String tableName, String whereCond, String orderByCond) throws SQLException;

	int create(HashMap<String, String> data) throws SQLException;

	int update(HashMap<String, String> data, int index) throws SQLException;

	int delete(int index) throws SQLException;

	void checkRow(HashMap<String, String> data, String tableName, HashMap<String, String> whereCond, String orderByCond,
			int index) throws SQLException;
	
	String whereStatementMaker(LinkedHashMap<String, Object> whereCond);
	ArrayList<String> getColumnNames(String tableName) throws SQLException;
	ArrayList<String> getColumnTypes(String tableName) throws SQLException;
	ArrayList<Integer> getColumnSizes(String tableName) throws SQLException;
}
