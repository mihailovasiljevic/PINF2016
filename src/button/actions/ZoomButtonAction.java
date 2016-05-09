package button.actions;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import main.MainFrame;
import database.ColumnDescription;
import database.TableDescription;
import form.Form;

public class ZoomButtonAction implements ActionListener{

	private TableDescription description;
	private JTextField txtField;
	private ColumnDescription cdesc;

	public ZoomButtonAction(TableDescription description, JTextField textField, ColumnDescription cdesc) {
		this.description = description;
		this.txtField = textField;
		this.cdesc = cdesc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(cdesc.getCode() + " h" +  cdesc.getCodeInParent());
		Form form = new Form(MainFrame.getInstance(),description,txtField,cdesc.getCodeInParent());
		form.setVisible(true);
	}
}
