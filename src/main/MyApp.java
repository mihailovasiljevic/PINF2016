package main;

import java.sql.SQLException;

import database.DataBase;

public class MyApp {

	public static void main(String[] args) {
		
		try {
			DataBase.open();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MainFrame frame = MainFrame.getInstance();
		frame.setVisible(true);
		
	}
	
}
