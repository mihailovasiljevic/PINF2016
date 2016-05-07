package database.crud;

import java.sql.SQLException;
import java.util.HashMap;

public interface IQueryCreator {
	int read(String tableName, String whereStmt, String orderBy) throws SQLException;
	int create(HashMap<String, String> data) throws SQLException;
	int update(HashMap<String, String> data, int index) throws SQLException;
	int delete(int index) throws SQLException;
	
	void checkRow(int index) throws SQLException;
}
