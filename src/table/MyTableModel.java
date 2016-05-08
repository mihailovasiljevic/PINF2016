package table;

import javax.swing.table.DefaultTableModel;

import database.TableDescription;

public class MyTableModel extends DefaultTableModel{

	public MyTableModel(TableDescription tdescription) {
		if(tdescription == null) {
			System.out.println("null");
		}
		
		if(tdescription.getColumnsDescriptions() == null) {
			System.out.println("null");
		}
		for(int i = 0; i < tdescription.getColumnsDescriptions().size(); i++) {
			
			this.addColumn(tdescription.getColumnsDescriptions().get(i).getLabel());
			
		}

	}
	
    @Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }
	
	
}
