package button.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import form.DatumForm;
import form.Form;
import form.DatePicker;



import javax.swing.JTextField;

public class PickDateAction implements ActionListener{

	private JTextField textField;
	private Form form;
	private DatumForm dateform =new DatumForm();

	public PickDateAction(Form form, JTextField textField) {
		this.setTextField(textField);
		this.setForm(form);
	}

	public PickDateAction(JTextField textField) {
		// TODO Auto-generated constructor stub
		this.setTextField(textField);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(form!=null){
			DatePicker dpck = new DatePicker(form);
			textField.setText(dpck.getPickedDate());
		}else{
			DatePicker dpck = new DatePicker(dateform);
			textField.setText(dpck.getPickedDate());
		}

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
