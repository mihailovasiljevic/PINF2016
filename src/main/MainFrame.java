package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;

import database.DataBase;

public class MainFrame extends JFrame {

	private static MainFrame frame = null;
	private MyMenuBar mbar=null;
	
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
	
}
