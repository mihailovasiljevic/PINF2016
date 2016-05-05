package button.actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;
import database.TableDescription;
import form.Form;

public class ZoomButtonAction implements ActionListener{

	private TableDescription description;

	public ZoomButtonAction(TableDescription description) {
		this.description = description;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Form form = new Form(MainFrame.getInstance(),description);
		form.setVisible(true);
	}
}
