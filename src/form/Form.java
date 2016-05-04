package form;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import database.Description;
import main.MainFrame;
import main.MyToolBar;
import net.miginfocom.swing.MigLayout;

public class Form extends JDialog {

	private Description description;
	
	public Form(Window parent, Description description) {
		super(parent,description.getTableCode());
		
		setLayout(new MigLayout("fill"));
		this.setModal(true);
		this.description = description;
		
		this.init();
		this.setLocationRelativeTo(parent);
	}
	
	private void init() {
		
		setSize(500, 400);
		this.add(new MyToolBar(this),"dock north");
		this.add(new MyTable(),"grow, wrap");
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(new DataPanel(description));
		bottomPanel.add(new ButtonsPanel(),"dock east");
		
		add(bottomPanel, "grow, wrap");
	
		
		
		
	}

	//GETTERS AND SETTERS
	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

}
