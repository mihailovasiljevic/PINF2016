package manubar.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.Description;
import form.Form;
import main.MainFrame;

public class MenuBarButtonAction  implements ActionListener {

	private Description description;
	
	public MenuBarButtonAction(Description description) {
		this.description = description;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Pritisnuto dugme tabele " + description.getTableCode());
		Form form = new Form(MainFrame.getInstance(),description);
		form.setVisible(true);
	}

}
