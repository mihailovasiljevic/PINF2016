package form;

import java.util.HashSet;
import java.util.Set;
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
		
		for(int i=0; i<txtFields.size(); i++)
		{
		
			//KONTROLA DA LI JE NULL
			if(!colDescs.get(i).isNullable())
			{
				if(txtFields.get(i).getText().equals("") || txtFields.get(i).getText().equals(null))
				{
					message+="Mora se nesto uneti u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid = false;
				}
			}
			//KONTROLA VELICINE STRINGOVA
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
			
			
			//KONTROLA TIPA
			Set<String> numberTypesInt = new HashSet<String>();
			Set<String> numberTypesDec = new HashSet<String>();
			numberTypesInt.add("tinyint");
			numberTypesInt.add("int");
			numberTypesInt.add("bigint");
			numberTypesDec.add("numeric");
			numberTypesDec.add("real");
			numberTypesDec.add("float");
			numberTypesDec.add("double");
			numberTypesDec.add("decimal");
			System.out.println(colDescs.get(i).getType());
			//DA LI JE CEO BROJ
			if(numberTypesInt.contains(colDescs.get(i).getType()))
			{
				
				if(!isInteger(txtFields.get(i).getText())){
					message += "Nije unet ceo broj u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
			
			if(numberTypesDec.contains(colDescs.get(i).getType()))
			{
				if(!isDecimal(txtFields.get(i).getText())){
					message += "Nije unet broj u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
		}
		
	}
	
	private boolean isInteger(String str)
	{
		if(str.equals(null) || str.equals(""))
			return true;
		return str.matches("-?\\d+");
	}
	
	private boolean isDecimal(String str){
		if(str.equals(null) || str.equals(""))
			return true;
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
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
