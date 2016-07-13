package button.actions;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

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
	private int id;
	
	public ReportButtonAction(Integer id)
	{
		putValue(SMALL_ICON, new ImageIcon("/slike/help.jpg"));
		putValue(SHORT_DESCRIPTION, "Report");
		this.id=id;
	}
	
	public void actionPerformed(ActionEvent evt) {
		try {
	          int status = id;  // i sve vrste
	          System.out.print(status+"UU");
		  Map params = new HashMap(1);
		  params.put("magaciniId", status );
		  System.out.println(getClass().getResource("/jasper/LagerLista.jasper"));
		  JasperPrint jp = JasperFillManager.fillReport(
		  getClass().getResource("/jasper/LagerLista.jasper").openStream(),
		  params, DataBase.getConn());
		  JasperViewer.viewReport(jp, false);

		} catch (Exception ex) {
		  ex.printStackTrace();
		}
	}


}
