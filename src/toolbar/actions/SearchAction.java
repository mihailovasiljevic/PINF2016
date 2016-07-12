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
			for(int i=0; i<((Form)standardForm).getDataPanel().getTextFields().size();i++)
			{
				if(!((Form)standardForm).getDataPanel().getTextFields().get(i).isVisible())
				{
				((Form)standardForm).getDataPanel().getTextFields().get(i).setVisible(true);
				((Form)standardForm).getDataPanel().getSearchIndices().add(i);
				}
			}
			
			for(int i=0; i<((Form)standardForm).getDataPanel().getBtnPicks().size();i++)
			{
				if(!((Form)standardForm).getDataPanel().getBtnPicks().get(i).isVisible()){
					((Form)standardForm).getDataPanel().getBtnPicks().get(i).setVisible(true);
					((Form)standardForm).getDataPanel().getBtnPickIndices().add(i);
				}
			}
			
			for(int i=0; i<((Form)standardForm).getDataPanel().getZoomBtns().size();i++)
			{
				if(!((Form)standardForm).getDataPanel().getZoomBtns().get(i).isVisible()){
				((Form)standardForm).getDataPanel().getZoomBtns().get(i).setVisible(true);
				((Form)standardForm).getDataPanel().getZoomBtnIndices().add(i);
				}
			}
			((Form)standardForm).getDataPanel().getTextFields().get(0).requestFocus();
			((Form) standardForm).setMode(3);
		}

	}
}
