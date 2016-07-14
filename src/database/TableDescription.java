package database;

import java.util.Vector;

/** Za skladistenje podataka izvucenih iz baze */
public class TableDescription {

	private String code;
	private String label;
	private Vector<String> nextTables = new Vector<String>();
	private Vector<ColumnDescription> columnsDescriptions;
	private int id;
	
	public ColumnDescription getColumnDescription(String code) {
		for(int i = 0; i < columnsDescriptions.size(); i++) {
			if(columnsDescriptions.get(i).getCode().equals(code)) {
				return columnsDescriptions.get(i);
			}
		}
		return null;
	}
	public String getCode(String label){
		for(int i = 0; i < columnsDescriptions.size(); i++) {
			if(columnsDescriptions.get(i).getLabel().equals(label)) {
				return columnsDescriptions.get(i).getCode();
			}
		}
		return label;		
	}
	public ColumnDescription getColumnDescription(int i) {
		return columnsDescriptions.get(i);
	}	
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
