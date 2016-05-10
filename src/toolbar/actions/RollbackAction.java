
package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import form.Form;

public class RollbackAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public RollbackAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(standardForm instanceof Form){
			((Form) standardForm).setMode(1);
		}
		
	}
}
