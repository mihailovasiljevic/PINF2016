package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class LastAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;


	public LastAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
