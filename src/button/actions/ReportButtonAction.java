package button.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import database.DataBase;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportButtonAction extends AbstractAction  {
	
	private JDialog standardForm;
	
	public ReportButtonAction()
	{
		putValue(SMALL_ICON, new ImageIcon("/slike/help.jpg"));
		putValue(SHORT_DESCRIPTION, "Report");
	//	this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		try {
	          System.out.println(getClass().getResource("/jasper/LagerLista.jasper"));
		  JasperPrint jp = JasperFillManager.fillReport(
		  getClass().getResource("/jasper/LagerLista.jasper").openStream(),
		  null, DataBase.getConn());
		  JasperViewer.viewReport(jp, false);

		} catch (Exception ex) {
		  ex.printStackTrace();
		}

		
	}

}
