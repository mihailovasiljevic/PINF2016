package manubar.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.TableDescription;
import form.Form;
import main.MainFrame;

public class MenuBarButtonAction  implements ActionListener {

	private TableDescription description;
	
	public MenuBarButtonAction(TableDescription description) {
		this.description = description;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("Pritisnuto dugme tabele " + description.getTableCode());
		System.out.println("ulaz");
		Form form = new Form(MainFrame.getInstance(),description);
		form.setVisible(true);
	}

}
