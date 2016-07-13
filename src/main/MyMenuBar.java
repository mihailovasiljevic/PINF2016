package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import table.MyTable;
import toolbar.actions.NextFormAction;
import button.actions.MenuBarButtonAction;
import button.actions.ReportAnalitikaAction;
import button.actions.ReportButtonAction;
import database.ColumnDescription;
import database.DataBase;
import database.TableDescription;
import form.Form;


@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar {

	public static Vector<TableDescription> tDescriptions; 
	private MyTable table;

	public MyMenuBar() {
		ResourceBundle bundT =
				ResourceBundle.getBundle("database.tLables");
		ResourceBundle bundC =
				ResourceBundle.getBundle("database.cLables");

		tDescriptions = new Vector<TableDescription>();
		Vector<String> tableCodes = null;

		tableCodes = DataBase.getTableCodes();

		JMenu menu = new JMenu("Organizaciona sema");
		JMenuItem button;
		TableDescription tdescription;
		for(int i = 0; i < tableCodes.size(); i++) {
			tdescription = new TableDescription();
			tdescription.setCode(tableCodes.get(i));

			tdescription.setLabel(bundT.getString(tableCodes.get(i)));

			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(tableCodes.get(i));
			Vector<String> nextTables = DataBase.getExportedTables(tableCodes.get(i));
			HashMap<String,String> foreignTables = DataBase.getImportedTables(tableCodes.get(i));

			for(int j = 0; j < nextTables.size(); j++) {
				tdescription.addNextTable(nextTables.get(j));
			}

			for(int j = 0; j < cdescription.size(); j++) {
				String key = tableCodes.get(i) + "." + cdescription.get(j).getCode();
				cdescription.get(j).setLabel(bundC.getString(key));
				cdescription.get(j).setPrimary_key(DataBase.isPrimaryKey(tableCodes.get(i),cdescription.get(j).getCode()));
				cdescription.get(j).setForeign_key(DataBase.isForeignKey(tableCodes.get(i),cdescription.get(j).getCode()));
				String childKey = null;
				for(String s : foreignTables.keySet()){
					if(cdescription.get(j).getCode().contains(s)){
						childKey = s;
						break;
					}
				}
				if(childKey == null)
					if(foreignTables.containsKey(cdescription.get(j).getCode())) {
						if(childKey != null)
							cdescription.get(j).setTableParent(childKey);
						else
							cdescription.get(j).setTableParent(foreignTables.get(cdescription.get(j).getCode()));
							
						//Milos: Umesto setTableParent cu napisati setCodeInParent posto pretpostavljam da je to Nemanja hteo
						//Milos2: koji je smisao ovoga? uzmemo neku kolonu i setujemo joj njen sopstveni Code kao codeInParent?
						cdescription.get(j).setCodeInParent(cdescription.get(j).getCode());
	
					} else {				
						cdescription.get(j).setTableParent(null);
						cdescription.get(j).setCodeInParent(null);
					}
				else
					if(foreignTables.containsKey(childKey)) {
						if(childKey != null)
							cdescription.get(j).setTableParent(childKey);
						else
							cdescription.get(j).setTableParent(foreignTables.get(cdescription.get(j).getCode()));
							
						//Milos: Umesto setTableParent cu napisati setCodeInParent posto pretpostavljam da je to Nemanja hteo
						//Milos2: koji je smisao ovoga? uzmemo neku kolonu i setujemo joj njen sopstveni Code kao codeInParent?
						cdescription.get(j).setCodeInParent(cdescription.get(j).getCode());
	
					} else {				
						cdescription.get(j).setTableParent(null);
						cdescription.get(j).setCodeInParent(null);
					}				
			}
			tdescription.setColumnsDescriptions(cdescription);
			button = new JMenuItem(tdescription.getLabel());
			button.addActionListener(new MenuBarButtonAction(tdescription));
			menu.add(button);
			tDescriptions.add(tdescription);
		}

		/*for(int i = 0; i < MyMenuBar.tDescriptions.size(); i++) {

			if(MyMenuBar.tDescriptions.get(i).getNextTables() == null) {
				System.out.println("cont");
				continue;
			}
			System.out.println(i);
			for(int j = 0; j < MyMenuBar.tDescriptions.get(i).getNextTables().size(); j++) {

				System.out.print(MyMenuBar.tDescriptions.get(i).getCode() +  " " );
				System.out.println(MyMenuBar.tDescriptions.get(i).getNextTables().get(j));
			}
		}*/

		JMenu menu1 = new JMenu("Izvestaj-Lager lista");


		String trenutna="MAGACIN";

		for(int k=0;k<MyMenuBar.tDescriptions.size();k++){

			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(MyMenuBar.tDescriptions.get(k).getCode());


			if(MyMenuBar.tDescriptions.get(k).getCode().equals(trenutna)){

				table = new MyTable(MyMenuBar.tDescriptions.get(k));

				int br_redova= this.table.getRowCount();



				for(int i=0;i<br_redova;i++){

						String id = (String) this.table.getValueAt(i, 0);
						String naziv = (String) this.table.getValueAt(i, 2);
						
						int iden=Integer.parseInt(id);

						JMenuItem item = new JMenuItem(naziv);
						item.addActionListener(new ReportButtonAction(iden));
						menu1.add(item);


				
				}
			}
		}
		
		JMenu menu2 = new JMenu("Izvestaj-Analitika");
		/*JMenuItem item = new JMenuItem("Generate");
		item.addActionListener(new ReportAnalitikaAction());
		menu2.add(item);*/
		
		for(int k=0;k<MyMenuBar.tDescriptions.size();k++){

			Vector<ColumnDescription> cdescription = DataBase.getDescriptions(MyMenuBar.tDescriptions.get(k).getCode());


			if(MyMenuBar.tDescriptions.get(k).getCode().equals(trenutna)){

				table = new MyTable(MyMenuBar.tDescriptions.get(k));

				int br_redova= this.table.getRowCount();



				for(int i=0;i<br_redova;i++){

						String id = (String) this.table.getValueAt(i, 0);
						String naziv = (String) this.table.getValueAt(i, 2);
						
						int iden=Integer.parseInt(id);

						JMenuItem item = new JMenuItem(naziv);
						item.addActionListener(new ReportAnalitikaAction(iden));
						menu2.add(item);


				
				}
			}
		}
		

		this.add(menu);	
		this.add(menu1);	
		this.add(menu2);
	}



	public static String getTableLabel(String tableCode){
		for(TableDescription td : tDescriptions){
			if(td.getCode().equalsIgnoreCase(tableCode)){
				return td.getLabel();
			}
		}
		return "";
	}


}
