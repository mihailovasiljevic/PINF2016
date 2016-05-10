package form;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;



import main.MainFrame;

@SuppressWarnings("serial")
/**
 * Obezbedjuje se statusbar pri dnu aplikacije koji je definisan u 
 * klasi <code>MainFrame</code>.U okviru njega imamo labele
 * koje nam sluze za navodjenje interesantnih stvari kao sto su 
 * trenutni datum,jezik kojim je pisana aplikacija i slicno.
 * 
 * @author nemanja
 * @author rajko
 * @author milos
 * @version 1.0, 12.12.2015.
 * @see javax.swing.JPanel
 * @see javax.swing.JLabel
 * @since 1.0
 * 
 */
public class StatusBar extends JPanel {
	
	/**
	 * Omogucava da se u ovoj labeli ispise jezik u kom je aplikacija
	 * napisana.
	 */
	private JLabel statLab1 = new JLabel("");
	

	private JLabel statLab2 = new JLabel("");
	private JLabel statLab3 = new JLabel("");
	private  JLabel statLab4 = new JLabel("");
	private  JLabel statLab5 = new JLabel("");
	
	/**
	 * Obezbedjuje se preko grida status bar sa jednim redom i
	 * pet kolona,tj pet labela.Tekst ili sadrzaj se u okviru svake
	 * od labela ispisuje po sredini i labele su spustene.U jednoj od labela
	 * obezbedjuje se ispis trenutnog datuma.
	 * 
	 */
	public StatusBar() {
        setLayout(new GridLayout(1,5,1,1));
                
        statLab1.setHorizontalAlignment(SwingConstants.CENTER);
        statLab1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab1);
        
        statLab2.setHorizontalAlignment(SwingConstants.CENTER);
        statLab2.setBorder(BorderFactory.createLoweredBevelBorder());
        add(statLab2);
            
        statLab3.setHorizontalAlignment(SwingConstants.CENTER);
        statLab3.setBorder(BorderFactory.createLoweredBevelBorder());
        add(statLab3);
        
        DateFormat df=DateFormat.getDateInstance();
    	String datum=df.format(new Date());
    	statLab3.setText(datum);
        
        statLab4.setHorizontalAlignment(SwingConstants.CENTER);
        statLab4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    
    
        add(statLab4);
        
        statLab5.setHorizontalAlignment(SwingConstants.CENTER);
        statLab5.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab5);
 
      
	}

	
	public JLabel getStatLab1() {
		return statLab1;
	}

	public void setStatLab1(JLabel statLab1) {
		this.statLab1 = statLab1;
	}

	public JLabel getStatLab2() {
		return statLab2;
	}

	public void setStatLab2(JLabel statLab2) {
		this.statLab2 = statLab2;
	}

	public JLabel getStatLab3() {
		return statLab3;
	}

	public void setStatLab3(JLabel statLab3) {
		this.statLab3 = statLab3;
	}

	public JLabel getStatLab4() {
		return statLab4;
	}

	public void setStatLab4(JLabel statLab4) {
		this.statLab4 = statLab4;
	}

	public JLabel getStatLab5() {
		return statLab5;
	}

	public void setStatLab5(JLabel statLab5) {
		this.statLab5 = statLab5;
	}

	public void init(){
		
		if(Form.getMode()==1){
			System.out.print("iz");
			statLab2.setText("Režim za izmenu");
		}

		if(Form.getMode()==2){
			System.out.print("iz1");
			statLab2.setText("Režim za unos");
		}

		if(Form.getMode()==3){
			System.out.print("iz2");
			statLab2.setText("Režim za pretragu");
		}
	}
	
}
