package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import form.Form;
import form.StatusBar;
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
		
		if(standardForm instanceof Form){
			State insertState = new InsertState();
			MainFrame.getInstance().getContext().setState(insertState);
			insertState.clearAll((Form)standardForm);
			insertState.setEditable((Form)standardForm, true);
			insertState.disableButtons(((Form)standardForm));
			((Form)standardForm).getDataPanel().getTextFields().get(0).requestFocus();

			Form form = (Form)standardForm;

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



			((Form) standardForm).setMode(2);
		}
	}
}
