package manubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarButtonAction  implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuBarButton button = (MenuBarButton) e.getSource();
		System.out.println("Pritisnuto dugme tabele " + button.getTableCode());
	}

}
