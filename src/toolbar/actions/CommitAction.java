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
			
			FormValidation frmVldtn=new FormValidation(standardForm,txtFields,colDescs);
			if(frmVldtn.isFormValid())
			{
				//TODO 1.0 COMMIT
				InsertState insertState = new InsertState();
				insertState.doAction(MainFrame.getInstance().getContext(), (Form)standardForm);

				
				JOptionPane.showMessageDialog(standardForm, "SVE JE OK!!!");
			}
			
			else
			{
				
				for(int i=0; i<frmVldtn.getInvalidFields().size(); i++)
				{
					frmVldtn.getInvalidFields().get(i).setBackground(Color.RED);
				}
				JOptionPane.showMessageDialog(standardForm, frmVldtn.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}

