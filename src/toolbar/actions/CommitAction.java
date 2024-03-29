package toolbar.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.ColumnDescription;
import form.Form;
import form.FormValidation;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;
import states.Context;
import states.InsertState;
import states.SearchState;
import states.State;
import states.UpdateState;
import sun.applet.Main;



public class CommitAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public CommitAction(JDialog standardForm) {
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(standardForm instanceof Form)
		{
			Vector<JTextField> txtFields=((Form)standardForm).getDataPanel().getTextFields();
			Vector<ColumnDescription> colDescs = ((Form)standardForm).getDataPanel().getColumnDescription();
		
			for(int i=0; i<txtFields.size(); i++)
			{
				txtFields.get(i).setBackground(Color.white);
			}
			
			FormValidation frmVldtn;
			Context context = MainFrame.getInstance().getContext();
			if(context.getState() instanceof SearchState)
			{
				frmVldtn = new FormValidation();
	
			}
			
			else frmVldtn = new FormValidation(standardForm);
				
			if(frmVldtn.isFormValid())
			{
				
				
				if(context.getState() instanceof InsertState){
					InsertState insertState = new InsertState();
					insertState.doAction(context, (Form)standardForm);
					insertState.sync(context, (Form)standardForm);
				}else if (context.getState() instanceof UpdateState){
					UpdateState udateState = new UpdateState();
					udateState.doAction(context, (Form)standardForm);					
				}else if (context.getState() instanceof SearchState){
					SearchState searchState = new SearchState();
					searchState.doAction(context, (Form)standardForm);
				}
					//search mode
				

				
				/*JOptionPane.showMessageDialog(standardForm, "SVE JE OK!!!");*/
			}
			
			else
			{
				
				for(int i=0; i<frmVldtn.getInvalidFields().size(); i++)
				{
					frmVldtn.getInvalidFields().get(i).setBackground(Color.RED);
				}
				JOptionPane.showMessageDialog(standardForm, frmVldtn.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
			//ako je u rezimu za unos prilikom comita ne vracaj se na rezim izmene
			if(((Form) standardForm).getMode()!=2)
			((Form) standardForm).setMode(1);
		}
		
	}
}

