package form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import button.actions.PickDateAction;
import button.actions.ReportDatumAction;


public class DatumForm extends JDialog{


	private int id;
	private JTextField DatumOd;
	private JTextField DatumDo;
	private JButton btnOk;
	private JPanel pan;
	
	
	
	public DatumForm() {
	}


	public DatumForm(int id){
		this.id=id;
		
		initDate();

	}
	
	
	private void initDate(){

		
		pan = new JPanel(new BorderLayout());
		
		pan.setBorder(new TitledBorder("Date"));

		Box boxCentar = new Box(BoxLayout.Y_AXIS);

		JPanel panDatumOd = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDatumOd = new JLabel("Datum od:");
		DatumOd = new JTextField(15);
		panDatumOd.add(lblDatumOd);
		panDatumOd.add(DatumOd);
		JButton datePickBtn = new JButton ("...");
		datePickBtn.addActionListener(new PickDateAction(DatumOd));
		panDatumOd.add(datePickBtn);
		boxCentar.add(panDatumOd);
		
		
		JPanel panDatumDo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDatumDo = new JLabel("Datum do:");
		DatumDo = new JTextField(15);
		panDatumDo.add(lblDatumDo);
		panDatumDo.add(DatumDo);
		JButton datePickBtn1 = new JButton ("...");
		datePickBtn1.addActionListener(new PickDateAction(DatumDo));
		panDatumDo.add(datePickBtn1);
		boxCentar.add(panDatumDo);
		
		
		
		JPanel dugmad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnOk=new JButton("Ok");
		btnOk.addActionListener(new ReportDatumAction(id,this));
		
		dugmad.add(btnOk);
		boxCentar.add(dugmad);
		
		pan.add(boxCentar, BorderLayout.CENTER);
		
		add(pan);
		
		pack(); //kreira frame
		
		setResizable(false);
		
		setLocationRelativeTo(null); //na sredinu stavi
	}
	
	public void report(){
		new ReportDatumAction(id,this);
	}


	public JTextField getDatumOd() {
		return DatumOd;
	}


	public void setDatumOd(JTextField DatumOd) {
		this.DatumOd = DatumOd;
	}


	public JTextField getDatumDo() {
		return DatumDo;
	}


	public void setDatumDo(JTextField DatumDo) {
		this.DatumDo = DatumDo;
	}
	
	
	
}

