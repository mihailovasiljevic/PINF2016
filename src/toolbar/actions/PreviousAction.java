package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import form.Form;
import form.FormState;
import main.MainFrame;
import states.UpdateState;

public class PreviousAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public PreviousAction(JDialog standardForm) {
		this.standardForm=standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(standardForm instanceof Form) {
			Form form = ((Form)standardForm);
			if((MainFrame.getInstance().getContext().getState() instanceof UpdateState) && form.getTable().getRowCount() > 0) {
				int i = form.getTable().getSelectedRow() - 1;
				if(i ==  -1 || i == -2) {
					i = form.getTable().getRowCount()-1;
				}
				form.getTable().setRowSelectionInterval(i, i);
			}
		}
	}
}
