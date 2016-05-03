package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	public static void close() throws SQLException {
		System.out.println("close");
		conn.close();
	}
	
	public static Vector<String> getTableCodes() throws SQLException {

		String   catalog          = null;
		String   schemaPattern    = null;
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
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DataBase.open();
		DataBase.getTableCodes();
		DataBase.close();
	}
	
}
