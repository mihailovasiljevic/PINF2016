package button.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import form.Form;
import form.DatePicker;



public class PickDateAction implements ActionListener{

	private JTextField textField;
	private Form form;

	public PickDateAction(Form form, JTextField textField) {
		this.setTextField(textField);
		this.setForm(form);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DatePicker dpck = new DatePicker(form);
		textField.setText(dpck.getPickedDate());
		
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
}
