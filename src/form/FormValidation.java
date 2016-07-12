package form;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import database.ColumnDescription;

public class FormValidation {
	
private Vector<ColumnDescription> colDescs = new Vector<ColumnDescription>();
private Vector<JTextField> txtFields = new Vector<JTextField>();
private boolean formValid;
private Vector<JTextField> invalidFields = new Vector<JTextField>();
private Vector<ButtonGroup> btnGroups = new Vector<ButtonGroup>();


private String message;
	
	public FormValidation(JDialog form){
		if(form instanceof Form)
		{
			this.setTxtFields(((Form) form).getDataPanel().getTextFields());
			this.setColDescs(((Form) form).getDataPanel().getColumnDescription());
			this.setBtnGroups(((Form) form).getDataPanel().getBtnGroups());
		}
		
		int k=0;
		formValid=true;
		message="";
		
		for(int i=0; i<txtFields.size(); i++)
		{
			//System.out.println(colDescs.get(k).getCode());
			//System.out.println(colDescs.get(k).getType());
			while(colDescs.get(k).getType().equalsIgnoreCase("bit"))
			{
				//pomeramo brojac za colDescs
				k++;
			}
				
			//KONTROLA DA LI JE NULL
			if(!colDescs.get(k).isNullable())
			{
				if(txtFields.get(i).getText().equals("") || txtFields.get(i).getText().equals(null))
				{
					message+="Mora se nesto uneti u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid = false;
				}
			}
			//KONTROLA VELICINE STRINGOVA
			if(colDescs.get(k).getType().equalsIgnoreCase("CHAR") 
					|| colDescs.get(k).getType().equalsIgnoreCase("VARCHAR"))
			{
				if(txtFields.get(i).getText().length()>colDescs.get(k).getLength())
				{
					System.out.println(colDescs.get(k).getCode());
					System.out.println(colDescs.get(k).getLength());
					
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
			
			//DA LI JE CEO BROJ
			if(numberTypesInt.contains(colDescs.get(k).getType()))
			{
				
				if(!isInteger(txtFields.get(i).getText())){
					message += "Nije unet ceo broj u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
			
			if(numberTypesDec.contains(colDescs.get(k).getType()))
			{
				if(!isDecimal(txtFields.get(i).getText())){
					message += "Nije unet broj u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
			
			if(colDescs.get(k).getType().equalsIgnoreCase("datetime") || colDescs.get(k).getType().equalsIgnoreCase("date"))
			{
				if(!isDate(txtFields.get(i).getText()))
				{
					message += "Nije unet korektan datum u polje "+txtFields.get(i).getName()+System.lineSeparator();
					invalidFields.add(txtFields.get(i));
					formValid=false;
				}
			}
			
			k++;
		}
		k=0;
		
		for(int i=0; i<btnGroups.size();i++)
		{
			while(!colDescs.get(k).getType().equalsIgnoreCase("bit"))
			{
				//pomeramo brojac za colDescs
				k++;
			}
	
			Enumeration<AbstractButton> btns = btnGroups.get(i).getElements();
				
			boolean selected = false;
			String name = "";
			while(btns.hasMoreElements())
			{	
				JRadioButton btn = (JRadioButton) btns.nextElement(); 
				name=btn.getName();
				if(btn.isSelected())
				{
					selected = true;
				}
			}
				
			if(!selected)
			{
				message += "Nije obelezemo dugme za "+name+System.lineSeparator();
				formValid=false;
			}
		}
	}
		

	
	public FormValidation() {
		// TODO Auto-generated constructor stub
		formValid=true;
	}



	private boolean isInteger(String str)
	{
		if(str.equals(null) || str.equals(""))
			return true;
		return str.matches("-?\\d+");
	}
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	boolean isDate(String input) {
		if(input.equals(null) || input.equals(""))
			return true;
	     try {
	          format.parse(input);
	          return true;
	     }
	     catch(ParseException e){
	          return false;
	     }
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
	
	public Vector<ButtonGroup> getBtnGroups() {
		return btnGroups;
	}

	public void setBtnGroups(Vector<ButtonGroup> btnGroups) {
		this.btnGroups = btnGroups;
	}
}
