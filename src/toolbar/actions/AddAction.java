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
			((Form)standardForm).getDataPanel().getTextFields().get(0).requestFocus();
			
			Form form = (Form)standardForm;
			System.out.println(form.getDataPanel().getSearchIndices().size());
			for(int i=0; i<form.getDataPanel().getSearchIndices().size();i++)
			{
				form.getDataPanel().getTextFields().get(form.getDataPanel().getSearchIndices().get(i)).setVisible(false);
			}
			
			for(int i=0; i<form.getDataPanel().getZoomBtnIndices().size();i++)
			{
				form.getDataPanel().getZoomBtns().get(form.getDataPanel().getZoomBtnIndices().get(i)).setVisible(false);
			}
			
			for(int i=0; i<form.getDataPanel().getBtnPickIndices().size();i++)
			{
				form.getDataPanel().getBtnPicks().get(form.getDataPanel().getBtnPickIndices().get(i)).setVisible(false);
			}
			form.getDataPanel().getSearchIndices().clear();
			form.getDataPanel().getZoomBtnIndices().clear();
			form.getDataPanel().getBtnPickIndices().clear();

			((Form) standardForm).setMode(2);
		}
	}
}
