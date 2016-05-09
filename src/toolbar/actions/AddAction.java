package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import form.Form;
import main.MainFrame;
import states.InsertState;
import states.State;



public class AddAction extends AbstractAction {


	private JDialog standardForm;
	
	public AddAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		State insertState = new InsertState();
		MainFrame.getInstance().getContext().setState(insertState);
		insertState.clearAll((Form)standardForm);
		insertState.setEditable((Form)standardForm, true);
		((Form)standardForm).getDataPanel().getTextFields().get(0).requestFocus();
	}
}
