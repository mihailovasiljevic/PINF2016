package form;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.ColumnDescription;

public class FormValidation {
	
private Vector<ColumnDescription> colDescs = new Vector<ColumnDescription>();
private Vector<JTextField> txtFields = new Vector<JTextField>();
private boolean formValid;
private Vector<JTextField> invalidFields = new Vector<JTextField>();
private String message;

	public FormValidation(JDialog form,Vector<JTextField> txtFields, Vector<ColumnDescription> colDescs){
		this.setColDescs(colDescs);
		this.setTxtFields(txtFields);
		formValid=true;
		message="";
		//KONTROLA VELICINE STRINGOVA
		for(int i=0; i<txtFields.size(); i++)
		{
			if(colDescs.get(i).getType().equalsIgnoreCase("CHAR") 
					|| colDescs.get(i).getType().equalsIgnoreCase("VARCHAR"))
			{
				if(txtFields.get(i).getText().length()>colDescs.get(i).getLength())
				{
					message += "Doslo je do prekoracenja broja karaktera u polju "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
		}
		
	}

	public Vector<ColumnDescription> getColDescs() {
		return colDescs;
	}

	public void setColDescs(Vector<ColumnDescription> colDescs) {
		this.colDescs = colDescs;
	}

	public Vector<JTextField> getTxtFields() {
		return txtFields;
	}

	public void setTxtFields(Vector<JTextField> txtFields) {
		this.txtFields = txtFields;
	}

	public boolean isFormValid() {
		return formValid;
	}

	public void setFormValid(boolean formValid) {
		this.formValid = formValid;
	}

	public Vector<JTextField> getInvalidFields() {
		return invalidFields;
	}

	public void setInvalidFields(Vector<JTextField> invalidFields) {
		this.invalidFields = invalidFields;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
