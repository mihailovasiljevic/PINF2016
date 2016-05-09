package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import form.Form;
import form.FormState;
import main.MainFrame;
import states.UpdateState;


public class FirstAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public FirstAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(standardForm instanceof Form) {
			Form form = ((Form)standardForm);
			if((MainFrame.getInstance().getContext().getState() instanceof UpdateState) && form.getTable().getRowCount() > 0) {
				form.getTable().setRowSelectionInterval(0, 0);
			}
		}
	}
}
