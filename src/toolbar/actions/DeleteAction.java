package toolbar.actions;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import form.Form;
import table.MyTableModel;

public class DeleteAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public DeleteAction(JDialog standardForm) {
		this.standardForm = standardForm;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Form form = (Form) standardForm;
		JTable table = form.getTable();

		int index = table.getSelectedRow();

		if (index == -1) {
			JOptionPane.showMessageDialog(null, "Morate selektovati red da biste mogli da ga obrisete", "Problem",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// provera da li je korisnik siguran
		// prvo provera da li je korisnik siguran
		int reply = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrsite odabrani slog? ",
				"PROVERA", JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			int newIndex = index;
			if (index == table.getModel().getRowCount() - 1)
				newIndex--;
			try {
				MyTableModel mtm = (MyTableModel) table.getModel();
				mtm.deleteRow(index);
				if (table.getModel().getRowCount() > 0)
					table.setRowSelectionInterval(newIndex, newIndex);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "GRESKA", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
