package button.actions;


import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import database.DataBase;
import form.DatumForm;

public class ReportAnalitikaAction  extends AbstractAction  {
	
	private JDialog standardForm;
	private Integer id;
	
	public ReportAnalitikaAction(Integer id)
	{
		putValue(SMALL_ICON, new ImageIcon("/slike/help.jpg"));
		putValue(SHORT_DESCRIPTION, "Report");
		this.id=id;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		DatumForm d=new DatumForm(id);
		d.setVisible(true);
		
	}

}
