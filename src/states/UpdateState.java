package states;

import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import form.Form;
import form.data.ConcreteDataGetter;
import form.data.IDataGetter;
import main.MainFrame;
import table.MyTableModel;

public class UpdateState extends AState {

	@Override
	public void doAction(Context context, Form form) {
		MainFrame.getInstance().getContext().setState(new UpdateState());
		if (form.getDescription().getCode().equals("ROBNA_KARTICA")) {
			for (JButton b : form.getButtonsPanel().getButtons()) {
				if (b.getToolTipText().equalsIgnoreCase("Nivelacija")) {
					form.getButtonsPanel().add(b);
					break;
				}
			}
		} else {
			int index = form.getTable().getSelectedRow();

			IDataGetter data = new ConcreteDataGetter();
			LinkedHashMap<String, String> formattedData = data.getData(form.getDataPanel());
			try {
				MyTableModel mtm = (MyTableModel) form.getTable().getModel();
				mtm.updateRow(index, formattedData);
				form.getTable().setRowSelectionInterval(index, index);
				form.refresh(index);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
			}

			if (form.getDescription().getCode().equals("ROBNA_KARTICA")) {
				for (JButton b : form.getButtonsPanel().getButtons()) {
					if (b.getToolTipText().equalsIgnoreCase("Nivelacija")) {
						form.getButtonsPanel().add(b);
						break;
					}
				}
			}
		}
	}

}
