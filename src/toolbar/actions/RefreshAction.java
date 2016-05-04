package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class RefreshAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	//kada se napravi genericka forma, staviti tu klasu umesto JDialog
	private JDialog standardForm;


	public RefreshAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
