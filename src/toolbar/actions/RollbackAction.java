
package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import states.InsertState;
import states.State;
import states.UpdateState;
import main.MainFrame;
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
		State updateState = new UpdateState();
		MainFrame.getInstance().getContext().setState(updateState);
		Form form = (Form)standardForm;
		updateState.disableButtons(((Form)standardForm));
		for(int i=0; i<form.getDataPanel().getAddedTextFields().size();i++)
		{
			form.getDataPanel().getAddedTextFields().get(i).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getAddedZoomBtns().size();i++)
		{
			form.getDataPanel().getAddedZoomBtns().get(i).setVisible(false);
		}
		
		for(int i=0; i<form.getDataPanel().getAddedPickBtns().size();i++)
		{
			form.getDataPanel().getAddedPickBtns().get(i).setVisible(false);
		}
		
	}
}
