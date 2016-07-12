
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
		
	}
}
