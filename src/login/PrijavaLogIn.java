package login;


import main.MainFrame;
import main.MyApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import database.DataBase;


@SuppressWarnings("serial")
public class PrijavaLogIn extends JDialog {
	
	
	private static PrijavaLogIn instance = null;

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JButton btnClose;
	private JPanel pan;

	public PrijavaLogIn() {
		super();
	}

	public static PrijavaLogIn getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new PrijavaLogIn();			
			instance.initialiseGUI();
		}
		return instance;
	}

	public void initialiseGUI() throws ClassNotFoundException, SQLException {
		
		System.out.print("GUI");
		
	
		pan = new JPanel(new BorderLayout());
		
		pan.setBorder(new TitledBorder("Prijava"));

		
		
		Box boxCentar = new Box(BoxLayout.Y_AXIS);
		
		setModal(true); //obezbedjuje focus na samo taj dijalog
		
		
		JPanel panKorisnickoIme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblKorisnickoIme = new JLabel("Ime");
		txtUsername = new JTextField(15);
		panKorisnickoIme.add(lblKorisnickoIme);
		panKorisnickoIme.add(txtUsername);
		boxCentar.add(panKorisnickoIme);
		
		JPanel panLozinka = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLozinka = new JLabel("JMBG");
		txtPassword = new JPasswordField(15);
		panLozinka.add(lblLozinka);
		panLozinka.add(txtPassword);
		boxCentar.add(panLozinka);
		
		JPanel dugmad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					try {
						ulogovan();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnLogin.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ulogovan();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnClose = new JButton(("Close"));
		btnClose.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
						System.exit(1);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnClose.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		dugmad.add(btnLogin);
		dugmad.add(btnClose);
		boxCentar.add(dugmad);
		
		pan.add(boxCentar, BorderLayout.CENTER);
		
		add(pan);
		
		pack(); //kreira frame
		
		setResizable(false);
		
		setLocationRelativeTo(null); //na sredinu stavi
		
		addWindowListener(new MainFrameListener());
		
	
		
	}

	public void ulogovan() throws ClassNotFoundException, SQLException {
		

		
		String username = getTxtUsername().getText();
		@SuppressWarnings("deprecation")
		String password = getTxtPassword().getText();
		
		ResultSet kor= DataBase.getStmt().executeQuery("SELECT * FROM RADNIK where RADNIK_IME='"+username+"' AND JMBG='"+password+"'");

		

		if(kor.next()){

				if(kor.isFirst()){
					dispose();
				}
		}
		else{
				JOptionPane.showMessageDialog(PrijavaLogIn.this,"Pogresno ime ili jmbg");

		}
			
		kor.close();

		
		
	}

	class MainFrameListener implements WindowListener {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(1);
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	
	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}


	public void setTxtPassword(JPasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}




	
}
