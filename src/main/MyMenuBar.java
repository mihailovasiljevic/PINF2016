package main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import database.ColumnDescription;
import database.DataBase;
import database.TableDescription;
import manubar.actions.MenuBarButtonAction;

public class MyMenuBar extends JMenuBar {
	
	public MyMenuBar() {
		
		ResourceBundle bundT =
				ResourceBundle.getBundle("database.tLables");
		ResourceBundle bundC =
				ResourceBundle.getBundle("database.cLables");
		
		
		Vector<String> tableCodes = null;
		
		tableCodes = DataBase.getTableCodes();
		JMenu menu = new JMenu("Organizaciona sema");
		JMenuItem button;
		TableDescription tdescription;
		for(int i = 0; i < tableCodes.size(); i++) {
			tdescription = new TableDescription();
			tdescription.setCode(tableCodes.get(i));
			
			tdescription.setLabel(bundT.getString(tableCodes.get(i))); //za sada je labela ustvari kod
			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(tableCodes.get(i));
			HashMap<String, String> foreignTables = DataBase.getImportedTables(tableCodes.get(i));
			for(int j = 0; j < cdescription.size(); j++) {
				String key = tableCodes.get(i) + "." + cdescription.get(j).getCode();
				cdescription.get(j).setLabel(bundC.getString(key));
				cdescription.get(j).setPrimary_key(DataBase.isPrimaryKey(tableCodes.get(i),cdescription.get(j).getCode()));
				cdescription.get(j).setForeign_key(DataBase.isForeignKey(tableCodes.get(i),cdescription.get(j).getCode()));
				if(foreignTables.containsKey(cdescription.get(j).getCode())) {
					cdescription.get(j).setTableParent(foreignTables.get(cdescription.get(j).getCode()));
				} else {
					cdescription.get(j).setTableParent(null);
				}
			}
			tdescription.setColumnsDescriptions(cdescription);
			button = new JMenuItem(tdescription.getLabel());
			button.addActionListener(new MenuBarButtonAction(tdescription));
			menu.add(button);
		}
		this.add(menu);
		
	}
	
}
