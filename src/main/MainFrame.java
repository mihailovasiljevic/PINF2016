package main;



import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.PrijavaLogIn;
import database.DataBase;
import states.Context;
import states.InsertState;
import util.json.IJSONParser;
import util.json.JSONModel;
import util.json.JacksonJSONParser;

public class MainFrame extends JFrame {

	private static MainFrame frame = null;
	private MyMenuBar mbar=null;
	private Context context;
	private JPanel statusBar;
	private JLabel statusLabel;
	private PrijavaLogIn plogin;
	private ArrayList<JSONModel> jsonModels;
	
	private MainFrame() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screen.getWidth() * 0.75);
		int height = (int) (screen.getHeight() * 0.75);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		mbar=new MyMenuBar();
		this.setJMenuBar(mbar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			DataBase.open();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.context = new Context();
		this.statusBar = new JPanel();
		this.statusBar.setLocation(this.getHeight()-100, 0);
		this.statusBar.setSize(this.getWidth(), 100);
		this.statusLabel = new JLabel("STATUSNA LINIJA");
		this.statusBar.add(this.statusLabel);
		this.add(this.statusBar);
		
		System.out.print("Usao");
		
		IJSONParser jsonParser = new JacksonJSONParser();
		jsonModels = jsonParser.parseJSON("config.json", "TABLES");
		((JacksonJSONParser) jsonParser).printParsed();
		//plogin = new PrijavaLogIn();
		
		//addWindowListener(new MainFrameListener());
	}
	

	public JPanel getStatusBar() {
		return statusBar;
	}


	public void setStatusBar(JPanel statusBar) {
		this.statusBar = statusBar;
	}


	public static MainFrame getInstance() {
		if(frame == null) {
			frame = new MainFrame();
		}
		return frame;
	}
	
	
	public void OtvoriLoginDijalog() throws ClassNotFoundException, SQLException {

		PrijavaLogIn p = PrijavaLogIn.getInstance();
		p.setVisible(true);
	}
	
	class MainFrameListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			try {
				OtvoriLoginDijalog();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public MyMenuBar getMbar() {
		return mbar;
	}

	public void setMbar(MyMenuBar mbar) {
		this.mbar = mbar;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}


	public ArrayList<JSONModel> getJsonModels() {
		return jsonModels;
	}


	public void setJsonModels(ArrayList<JSONModel> jsonModels) {
		this.jsonModels = jsonModels;
	}
	
	
}
