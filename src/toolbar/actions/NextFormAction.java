package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;


public class NextFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public NextFormAction(JDialog standardForm) {
		this.standardForm  = standardForm;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
