package manubar;

import javax.swing.JMenuItem;

public class MenuBarButton extends JMenuItem {

	private String tableCode;

	public MenuBarButton(String label) {
		super(label);
	}
	
	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	
	
}
