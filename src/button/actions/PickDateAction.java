package button.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import org.jdatepicker.JDatePicker;


public class PickDateAction implements ActionListener{

	private JTextField textField;

	public PickDateAction(JTextField textField) {
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Form form = new Form(MainFrame.getInstance(),description);
		//form.setVisible(true);
	}
}
