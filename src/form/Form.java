package form;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import database.TableDescription;
import main.MainFrame;
import main.MyToolBar;
import net.miginfocom.swing.MigLayout;

public class Form extends JDialog {

	private TableDescription description;
	
	public Form(Window parent, TableDescription description) {
		super(parent,description.getLabel());
		
		setLayout(new MigLayout("fill"));
		this.setModal(true);
		this.description = description;
		
		this.init();
		this.setLocationRelativeTo(parent);
	}
	
	private void init() {
		
		setSize(500, 400);
		this.add(new MyToolBar(this),"dock north");
		this.add(new TablePane(),"grow, wrap");
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new MigLayout("fillx"));
		bottomPanel.add(new DataPanel(description));
		bottomPanel.add(new ButtonsPanel(),"dock east");
		
		add(bottomPanel, "grow, wrap");
	
		
		
		
	}

	//GETTERS AND SETTERS
	public TableDescription getDescription() {
		return description;
	}

	public void setDescription(TableDescription description) {
		this.description = description;
	}

}
