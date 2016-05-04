package database;

import java.util.Vector;

/** Za skladistenje podataka izvucenih iz baze */
public class Description {

	private String tableCode;
	private Vector<String> columns;
	private Vector<String> foreignColumns;

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public Vector<String> getColumns() {
		return columns;
	}

	public void setColumns(Vector<String> columns) {
		this.columns = columns;
	}

	public Vector<String> getForeignColumns() {
		return foreignColumns;
	}

	public void setForeignColumns(Vector<String> foreignColumns) {
		this.foreignColumns = foreignColumns;
	}
	
}
