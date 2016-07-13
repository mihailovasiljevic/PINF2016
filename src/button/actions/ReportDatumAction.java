package button.actions;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import database.DataBase;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import form.DatumForm;


public class ReportDatumAction extends AbstractAction{

	private JDialog standardForm;
	private Integer id;
	private String od;
	private String doo;
	private DatumForm form;

	public ReportDatumAction(Integer id,DatumForm form)
	{
		putValue(SMALL_ICON, new ImageIcon("/slike/help.jpg"));
		putValue(SHORT_DESCRIPTION, "Report");
		this.id=id;
		this.od=od;
		this.doo=doo;
		this.form=form;

	}

	public void actionPerformed(ActionEvent evt) {
		
		
		
		try {
			int status = id; 

			String dateString1 = form.getDatumOd().getText(); 
			String dateString2 = form.getDatumDo().getText();

			if(!ProveraIspravnosti(dateString1, dateString2))
		      {
					JOptionPane.showMessageDialog(null, "Mora biti u formatu dd-MM-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
		      }
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
			Date od = dateFormat.parse(dateString1); 
			Date doo = dateFormat.parse(dateString2); 

			Map params = new HashMap(1);
			params.put("DatumOd", od );
			params.put("DatumDo", doo );
			params.put("magaciniId", status );

			System.out.println(getClass().getResource("/jasper/Analitika.jasper"));
			JasperPrint jp = JasperFillManager.fillReport(
					getClass().getResource("/jasper/Analitika.jasper").openStream(),
					params, DataBase.getConn());
			JasperViewer.viewReport(jp, false);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	private boolean ProveraIspravnosti(String dateFrom, String dateTo)
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date inputDate = dateFormat.parse(dateFrom);
			inputDate = dateFormat.parse(dateTo);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}


