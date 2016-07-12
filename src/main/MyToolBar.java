package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import button.actions.MenuBarButtonAction;
import button.actions.ZoomButtonAction;
import database.ColumnDescription;
import database.DataBase;
import database.TableDescription;
import form.Form;
import toolbar.actions.AddAction;
import toolbar.actions.DeleteAction;
import toolbar.actions.FirstAction;
import toolbar.actions.HelpAction;
import toolbar.actions.LastAction;
import toolbar.actions.NextAction;
import toolbar.actions.NextFormAction;
import toolbar.actions.PickupAction;
import toolbar.actions.PreviousAction;
import toolbar.actions.RefreshAction;
import toolbar.actions.SearchAction;
import util.json.JSONModel;

@SuppressWarnings("serial")
public class MyToolBar extends JToolBar {

	public static Form form;
	MyMenuBar mbar;
	
	private JButton pickButton;

	@SuppressWarnings("static-access")
	public MyToolBar(JDialog dialog) {
		
		Form form = (Form)dialog;
		ArrayList<JSONModel> jsonModels = MainFrame.getInstance().getJsonModels();
		ArrayList<JButton> buttons = new ArrayList<>();
		JButton button;

		button = new JButton(new ImageIcon(getClass().getResource("/slike/search.gif")));
		button.setToolTipText("Pretraga");
		button.addActionListener(new SearchAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/refresh.gif")));
		button.setToolTipText("Refresh");
		button.addActionListener(new RefreshAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/zoom-pickup.gif")));
		button.setToolTipText("Zoom pickup");
		button.addActionListener(new PickupAction(dialog));
		this.pickButton = button;
		buttons.add(pickButton);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/help.gif")));
		button.setToolTipText("Pomoc");
		button.addActionListener(new HelpAction(dialog));
		buttons.add(button);
		this.add(button);
		this.addSeparator();

		button = new JButton(new ImageIcon(getClass().getResource("/slike/first.gif")));
		button.setToolTipText("Pocetak");
		button.addActionListener(new FirstAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/prev.gif")));
		button.setToolTipText("Prethodni");
		button.addActionListener(new PreviousAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/next.gif")));
		button.setToolTipText("Sledeci");
		button.addActionListener(new NextAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/last.gif")));
		button.setToolTipText("Poslednji");
		button.addActionListener(new LastAction(dialog));
		this.add(button);
		buttons.add(button);
		this.addSeparator();

		button = new JButton(new ImageIcon(getClass().getResource("/slike/add.gif")));
		button.setToolTipText("Dodavanje");
		button.addActionListener(new AddAction(dialog));
		buttons.add(button);
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/remove.gif")));
		button.setToolTipText("Brisanje");
		button.addActionListener(new DeleteAction(dialog));
		buttons.add(button);
		this.add(button);
		this.addSeparator();

		final JButton button1 = new JButton(new ImageIcon(getClass().getResource("/slike/nextForm.gif")));
		button1.setToolTipText("Sledeca forma");
		buttons.add(button1);
		
		/**
		 * Disable dugmice koji nisu u funkciju za odgovarajucu formu
		 */
		System.out.println("ime tabele: "+form.getDescription().getCode());
		
		for(JSONModel model : jsonModels){
			if(form.getDescription().getCode().equalsIgnoreCase(model.getTableName())){
				for(String s : model.getToolbarItems()){
					for(JButton but : buttons){
						if(s.equalsIgnoreCase(but.getToolTipText())){
							but.setEnabled(false);
						}
					}
				}
				break;
			}
		}
		
		
		
		final JPopupMenu menu = new JPopupMenu("Menu");
		

		String trenutna=((Form)dialog).getDescription().getCode();

		int meni_pop=0;

		Vector<String> foreignTables = DataBase.getExportedTables(trenutna);
		for(String key : foreignTables){
			meni_pop++;
			String tableLabel = MyMenuBar.getTableLabel(key);
			JMenuItem tab = new JMenuItem(tableLabel);
			tab.addActionListener(new NextFormAction(dialog,tableLabel));
			menu.add(tab);			
		}
/*
		for(int k=0;k<MyMenuBar.tDescriptions.size();k++){

			HashMap<String,String> foreignTables = DataBase.getImportedTables(MyMenuBar.tDescriptions.get(k).getCode());
			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(MyMenuBar.tDescriptions.get(k).getCode());
			for(int j = 0; j < cdescription.size(); j++) {

				boolean primarni_kljuc=DataBase.isPrimaryKey(trenutna,cdescription.get(j).getCode());
				boolean strani_kljuc=DataBase.isForeignKey(trenutna,cdescription.get(j).getCode());

				if(primarni_kljuc && strani_kljuc==false){

					if(foreignTables.containsKey(cdescription.get(j).getCode())){

						String tabela=MyMenuBar.tDescriptions.get(k).getLabel();
						JMenuItem tab = new JMenuItem(tabela);
						//otvaranje tabela iz padajuceg menija
						tab.addActionListener(new NextFormAction(dialog,tabela));
						menu.add(tab);
						meni_pop++;

					}
				}
			}
		}
*/
		if(meni_pop>0){
			button1.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					menu.show(button1, button1.getWidth()/2, button1.getHeight()/2);
				}
			} );
		}else{
			button1.setEnabled(false);
		}




		this.add(button);
		this.add(button1);


		this.setFloatable(false);

	}
	
	public void disablePick() {
		this.pickButton.setEnabled(false);
	}

}
