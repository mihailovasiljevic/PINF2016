package main;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import database.DataBase;
import database.Description;
import manubar.actions.MenuBarButtonAction;

public class MyMenuBar extends JMenuBar {
	
	public MyMenuBar() {
		
		Vector<String> tableCodes = null;
		try {
			tableCodes = DataBase.getTableCodes();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		JMenu menu = new JMenu("Organizaciona sema");
		JMenuItem button;
		Description description;
		for(int i = 0; i < tableCodes.size(); i++) {
			description = new Description();
			description.setTableCode(tableCodes.get(i));
			button = new JMenuItem(description.getTableCode());
			button.addActionListener(new MenuBarButtonAction(description));
			menu.add(button);
		}
		
		this.add(menu);
		
		
	}
	
}
