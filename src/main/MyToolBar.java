package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

@SuppressWarnings("serial")
public class MyToolBar extends JToolBar {

	public static Form form;
	MyMenuBar mbar;

	@SuppressWarnings("static-access")
	public MyToolBar(JDialog dialog) {

		JButton button;

		button = new JButton(new ImageIcon(getClass().getResource("/slike/search.gif")));
		button.addActionListener(new SearchAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/refresh.gif")));
		button.addActionListener(new RefreshAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/zoom-pickup.gif")));
		button.addActionListener(new PickupAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/help.gif")));
		button.addActionListener(new HelpAction(dialog));
		this.add(button);
		this.addSeparator();

		button = new JButton(new ImageIcon(getClass().getResource("/slike/first.gif")));
		button.addActionListener(new FirstAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/prev.gif")));
		button.addActionListener(new PreviousAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/next.gif")));
		button.addActionListener(new NextAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/last.gif")));
		button.addActionListener(new LastAction(dialog));
		this.add(button);
		this.addSeparator();

		button = new JButton(new ImageIcon(getClass().getResource("/slike/add.gif")));
		button.addActionListener(new AddAction(dialog));
		this.add(button);

		button = new JButton(new ImageIcon(getClass().getResource("/slike/remove.gif")));
		button.addActionListener(new DeleteAction(dialog));
		this.add(button);
		this.addSeparator();

		final JButton button1 = new JButton(new ImageIcon(getClass().getResource("/slike/nextForm.gif")));

		final JPopupMenu menu = new JPopupMenu("Menu");
		

		String trenutna=((Form)dialog).getDescription().getCode();

		int meni_pop=0;

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
						tab.addActionListener(new NextFormAction(tabela));
						menu.add(tab);
						meni_pop++;

					}
				}
			}
		}

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

}
