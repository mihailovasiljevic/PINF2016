package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);
		
		dmeta = conn.getMetaData();
		
	}
	
	public static void close() {
		try {
			if (conn != null) //conn
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
			    res.put(result.getString(4), result.getString(3));
			    System.out.println(result.getString(4) + " " + result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static Vector<String> getExportedTables(String tableName) {
		
		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = tableName;
	
		Vector<String> columns = new Vector<String>();
		ResultSet result;
		try {
			result = dmeta.getExportedKeys(catalog, schemaPattern, tableNamePattern);
			while(result.next()) {
			    String columnName = result.getString(7);
			    if(!columns.contains(columnName)) {
			    	columns.add(columnName);
			    	System.out.println(columnName);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columns;
		
	}

	public static void writeForTables(String fileName) {
		
		Vector<String> tables = DataBase.getTableCodes();
		
		try {
			FileWriter fileWriter = new FileWriter("src\\database\\"+fileName, false);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			//printWriter.print("a test");
			for(int i = 0; i < tables.size(); i++) {
				printWriter.print(tables.get(i)+" = \n\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void writeForColumns(String fileName) {
		
		Vector<String> tables = DataBase.getTableCodes();
		Vector<String> columns = new Vector<String>();
		Vector<String> tab = new Vector<String>();
		Vector<ColumnDescription> cdesc;
		
		for(int i = 0; i < tables.size(); i++) {
			cdesc = DataBase.getDescriptions(tables.get(i));
			for(int j = 0; j < cdesc.size(); j++) {
				columns.add(cdesc.get(j).getCode());
				tab.add(tables.get(i));
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter("src\\database\\"+fileName, false);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for(int i = 0; i < columns.size(); i++) {
				printWriter.print(tab.get(i)+"."+columns.get(i)+" = \n\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static boolean isNullable(String tableCode, String columnCode) {
		
	
		String   catalog          = null;
		String   schemaPattern    = "dbo";
		String   tableNamePattern = tableCode;
		String 	 columnNamePattern = columnCode;
	
		ResultSet result;
		try {
			result = dmeta.getColumns(catalog, schemaPattern, tableNamePattern,columnNamePattern);
			while(result.next()) {
			    if(result.getString(18).equals("YES"))
			    {
			    	return true;
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	//OVO VRACA OCEKIVANE VREDNOSTI ZA CHAR I VARCHAR ALI STA CE BITI SA DRUGIM TIPOVIMA PODATAKA?
	public static int getColumnSize(String tableCode, String columnCode) {
	
	
	String   catalog          = null;
	String   schemaPattern    = "dbo";
	String   tableNamePattern = tableCode;
	String 	 columnNamePattern = columnCode;

	ResultSet result;
	try {
		result = dmeta.getColumns(catalog, schemaPattern, tableNamePattern,columnNamePattern);
		while(result.next()) {
		    return Integer.parseInt(result.getString(7));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return 0;
}	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DataBase.open();
		//Vector<String> res = DataBase.getTableCodes();
		//System.out.println(res.size());
		//Vector<ColumnDescription> desc = DataBase.getDescriptions("DRZAVA");
		//System.out.println(desc.size());
		getExportedTables("naseljeno_mesto");
		//DataBase.getImportedTables("SLUZBA");
		//DataBase.writeForTables("tLables.properties");
		//DataBase.writeForColumns("cLables.properties");
		
		DataBase.close();
		
	}
	
}
