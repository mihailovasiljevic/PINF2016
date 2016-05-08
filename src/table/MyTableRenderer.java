package table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import form.Form;

public class MyTableRenderer extends DefaultTableCellRenderer {

	private Form form;
	
	public MyTableRenderer(Form form) {
		this.form = form;
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		 
		setBorder(noFocusBorder);
		
		return this;
	 }
	
}
