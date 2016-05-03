package manubar;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import database.DataBase;

public class MyMenuBar extends JMenuBar {

	private Vector<MenuBarButton> menuButtons = new Vector<MenuBarButton>();
	
	public MyMenuBar() {
		
		Vector<String> tableCodes = null;
		try {
			tableCodes = DataBase.getTableCodes();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		JMenu menu = new JMenu("Jedinica");
		
		MenuBarButton button;
		for(int i = 0; i < tableCodes.size(); i++) {
			button = new MenuBarButton(tableCodes.get(i));
			button.setTableCode(tableCodes.get(i));
			button.addActionListener(new MenuBarButtonAction());
			menu.add(button);
		}
		
		this.add(menu);
		
		
	}
	
}
