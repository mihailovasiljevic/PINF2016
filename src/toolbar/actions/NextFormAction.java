package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import database.TableDescription;
import form.Form;
import main.MainFrame;
import main.MyMenuBar;


public class NextFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private String standardForm;
	MyMenuBar mbar;
	TableDescription tdb;

	public NextFormAction(String string) {
		this.standardForm  = string;

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {


		mbar=new MyMenuBar();


		for(int k=0;k<mbar.gettDescriptions().size();k++){

			if(mbar.gettDescriptions().get(k).getLabel().contains(standardForm)){

				Form form = new Form(MainFrame.getInstance(),mbar.gettDescriptions().get(k));
				form.setVisible(true);
			}
		}





	}

}
