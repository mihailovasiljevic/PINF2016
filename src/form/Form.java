package form;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;

import database.Description;
import main.MainFrame;
import main.MyToolBar;

public class Form extends JDialog {

	private Description description;
	
	public Form(Window parent, Description description) {
		super(parent,description.getTableCode());
		
		this.setModal(true);
		this.description = description;
		
		this.init();
		this.setLocationRelativeTo(parent);
	}
	
	private void init() {
		
		setSize(500, 400);
		this.add(new MyToolBar(this),BorderLayout.NORTH);
	}

	//GETTERS AND SETTERS
	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

}
