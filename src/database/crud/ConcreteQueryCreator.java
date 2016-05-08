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
		LinkedHashMap<String, String> retVal = new LinkedHashMap<>();

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
