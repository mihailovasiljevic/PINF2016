package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import database.DataBase;
import states.Context;
import states.InsertState;

public class MainFrame extends JFrame {

	private static MainFrame frame = null;
	private MyMenuBar mbar=null;
	private Context context;
	private JPanel statusBar;
	private JLabel statusLabel;
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
	}
	
	public MyMenuBar getMbar() {
		return mbar;
	}

	public void setMbar(MyMenuBar mbar) {
		this.mbar = mbar;
	}

	public static MainFrame getInstance() {
		if(frame == null) {
			frame = new MainFrame();
		}
		return frame;
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
	
	
}
