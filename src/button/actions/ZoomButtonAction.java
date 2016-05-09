package button.actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import main.MainFrame;
import database.TableDescription;
import form.Form;

public class ZoomButtonAction implements ActionListener{

	private TableDescription description;
	private JTextField txtField;

	public ZoomButtonAction(TableDescription description, JTextField textField) {
		this.description = description;
		this.txtField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Form form = new Form(MainFrame.getInstance(),description);
		form.setVisible(true);
	}
}
