package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

public class DataBase {

	private static Connection conn;
	private static DatabaseMetaData dmeta;
	
	public static void open() throws ClassNotFoundException, SQLException {
		if (conn != null)
			return;
		ResourceBundle bundle =
				PropertyResourceBundle.getBundle("database.DataBase");
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String username = bundle.getString("username");  
		String password = bundle.getString("password");
		
		System.out.println(driver + " " + url + " " + username + " " + password);
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);
		
		dmeta = conn.getMetaData();
		
	}
	
	public static void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static Vector<String> getTableCodes() {
		
		String query = "SELECT * from INFORMATION_SCHEMA.TABLES;";
		Vector<String> result = new Vector<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			ResultSet rst = stmt.getResultSet();
			while(rst.next()) {
				System.out.println(rst.getString(3));
				result.add(rst.getString(3));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static Vector<ColumnDescription> getDescriptions(String tableCode) {
		
		Vector<ColumnDescription> result = new Vector<ColumnDescription>();
		String query = "SELECT * from INFORMATION_SCHEMA.COLUMNS;";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			ResultSet rst = stmt.getResultSet();
			while(rst.next()) {
				if(rst.getString(3).equalsIgnoreCase(tableCode)) {
					ColumnDescription desc = new ColumnDescription();
					desc.setCode(rst.getString(4));
					desc.setLabel(rst.getString(4)); //za sada je labela ustvari kod
					String yes_no = rst.getString(7);
					boolean nullable = false;
					if(yes_no.equalsIgnoreCase("YES")) {
						nullable = true;
					}
					desc.setNullable(nullable);
					desc.setType(rst.getString(8));
					if(rst.getString(9) != null) {
						desc.setLength(new Integer(rst.getString(9)));
					}
					result.add(desc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static boolean isPrimaryKey(String tableCode, String columnCode) {
		
		String query = "SELECT * from INFORMATION_SCHEMA.KEY_COLUMN_USAGE;";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			ResultSet rst = stmt.getResultSet();
			while(rst.next()) {
				if(rst.getString(6).equalsIgnoreCase(tableCode) 
						&& rst.getString(7).equalsIgnoreCase(columnCode)
						&& rst.getString(3).startsWith("PK")) {
					return true;		
				}
					
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean isForeignKey(String tableCode, String columnCode) {
		
		String query = "SELECT * from INFORMATION_SCHEMA.KEY_COLUMN_USAGE;";
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			ResultSet rst = stmt.getResultSet();
			while(rst.next()) {
				if(rst.getString(6).equalsIgnoreCase(tableCode) 
						&& rst.getString(7).equalsIgnoreCase(columnCode)
						&& rst.getString(3).startsWith("FK")) {
					return true;		
				}
					
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	

	
	/*public static Vector<String> getTableCodes() throws SQLException {

		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = null;
		String[] types            = {"TABLE"};

		ResultSet result = dmeta.getTables(
		    catalog, schemaPattern, tableNamePattern, types );
		
		Vector<String> tables = new Vector<String>();
		while(result.next()) {
		    String tableName = result.getString(3);
		    tables.add(tableName);
		    
		}
		return tables;
	}*/
	
	
	
	
	public static Vector<String> getColumnsForTable(String tableName) throws SQLException {
		
		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = tableName;
		String columnNamePattern = null;

		ResultSet result = dmeta.getColumns(
		    catalog, schemaPattern, tableNamePattern, columnNamePattern );
		
		Vector<String> columns = new Vector<String>();
		while(result.next()) {
		    String columnName = result.getString(4);
		    columns.add(columnName);
		    
		}
		return columns;
	}
	
	public static Vector<String> getForeignColumnsForTable(String tableName) {
		
		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = tableName;
	

		Vector<String> columns = new Vector<String>();
		ResultSet result;
		try {
			result = dmeta.getImportedKeys(catalog, schemaPattern, tableNamePattern);
			while(result.next()) {
			    String columnName = result.getString(4);
			    columns.add(columnName);
			    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		return columns;
	}
	
	public static HashMap<String,String> getImportedTables(String tableName) {
		
		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = tableName;
	
		HashMap<String,String> res = new HashMap<String,String>();
		Vector<String> columns = new Vector<String>();
		ResultSet result;
		try {
			result = dmeta.getImportedKeys(catalog, schemaPattern, tableNamePattern);
			while(result.next()) {
			    String columnName = result.getString(7);
			    columns.add(columnName);
			    System.out.println(result.getString(3) + " " + result.getString(4));
			    res.put(result.getString(4), result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DataBase.open();
		//Vector<String> res = DataBase.getTableCodes();
		//System.out.println(res.size());
		//Vector<ColumnDescription> desc = DataBase.getDescriptions("DRZAVA");
		//System.out.println(desc.size());
		
		
		
		DataBase.close();
	}
	
}
