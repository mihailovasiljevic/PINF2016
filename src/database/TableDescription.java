package database;

import java.util.Vector;

/** Za skladistenje podataka izvucenih iz baze */
public class TableDescription {

	private String code;
	private String label;
	private Vector<String> nextTables = new Vector<String>();
	private Vector<ColumnDescription> columnsDescriptions;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Vector<String> getNextTables() {
		return nextTables;
	}
	public void setNextTables(Vector<String> nextTables) {
		this.nextTables = nextTables;
	}
	public Vector<ColumnDescription> getColumnsDescriptions() {
		return columnsDescriptions;
	}
	public void setColumnsDescriptions(Vector<ColumnDescription> columnsDescriptions) {
		this.columnsDescriptions = columnsDescriptions;
	}
	
	public void addNextTable(String tableCode) {
		if(nextTables == null) {
			nextTables = new Vector<String>();
		}
		nextTables.add(tableCode);
	}

}
