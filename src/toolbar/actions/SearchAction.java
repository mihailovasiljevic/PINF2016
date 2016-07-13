package toolbar.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import main.MainFrame;
import states.InsertState;
import states.SearchState;
import states.State;
import form.Form;

public class SearchAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public SearchAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(standardForm instanceof Form){
			State searchState = new SearchState();
			MainFrame.getInstance().getContext().setState(searchState);
			searchState.clearAll((Form)standardForm);
			searchState.setEditable((Form)standardForm, true);
			
			((Form)standardForm).getButtonsPanel().getBtnCommit().setEnabled(true);
			((Form)standardForm).getButtonsPanel().getBtnRollback().setEnabled(true);
			for(int i=0; i<((Form)standardForm).getDataPanel().getAddedTextFields().size();i++)
			{
				((Form)standardForm).getDataPanel().getAddedTextFields().get(i).setVisible(true);
			}
			
			for(int i=0; i<((Form)standardForm).getDataPanel().getAddedZoomBtns().size();i++)
			{
				((Form)standardForm).getDataPanel().getAddedZoomBtns().get(i).setVisible(true);
			}
			
			for(int i=0; i<((Form)standardForm).getDataPanel().getAddedPickBtns().size();i++)
			{
				((Form)standardForm).getDataPanel().getAddedPickBtns().get(i).setVisible(true);
			}
			((Form)standardForm).getDataPanel().getTextFields().get(0).requestFocus();
			((Form) standardForm).setMode(3);
		}

	}
}
