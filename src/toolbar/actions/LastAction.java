package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import form.Form;
import form.FormState;
import main.MainFrame;
import states.UpdateState;

public class LastAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;


	public LastAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(standardForm instanceof Form) {
			Form form = ((Form)standardForm);
			if((MainFrame.getInstance().getContext().getState() instanceof UpdateState) && form.getTable().getRowCount() > 0) {
				int i = form.getTable().getRowCount() - 1;
				form.getTable().setRowSelectionInterval(i, i);
			}
		}
	}
}
