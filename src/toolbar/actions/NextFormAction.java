package toolbar.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import table.MyTable;
import database.ColumnDescription;
import database.DataBase;
import database.TableDescription;
import form.Form;
import main.MainFrame;
import main.MyMenuBar;

public class NextFormAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	private String string;
	public static boolean openedFromNext = false;
	public NextFormAction(JDialog standardForm, String string) {
		this.standardForm = standardForm;
		this.string = string;
		System.out.println("IZ NEXTA: " + string);

	}
	private int fitlerField = -1;
	public static int selectedId = -1;
	public static String colName = "";


	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		TableDescription td = null;
		for (TableDescription t : MyMenuBar.tDescriptions) {
			if (t.getCode().equalsIgnoreCase(string)) {

				try {
					int idx = ((Form) standardForm).getTable().getSelectedRow();

					if (idx != -1) {
						int id = Integer.parseInt((String) ((Form) standardForm).getTable().getModel().getValueAt(idx, 0));
						
						String selected = t.getColumnDescription(0).getCode();
						Form f = new Form(standardForm, t);
						

						openedFromNext = true;
						selectedId = id;
						colName = ((Form) standardForm).getTable().getModel().getColumnName(0);
						System.out.println("COLNAME: " +t.getCode(colName));
						colName = t.getCode(colName);
						int brojPolja = 1;
						for(int i = 0; i < f.getTable().getModel().getColumnCount();i++){
							if(t.getCode(f.getTable().getModel().getColumnName(i)).equalsIgnoreCase(colName)){
								brojPolja = i;
								break;
							}
						}
						final int test = brojPolja;
					    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
					        public boolean include(Entry entry) {
					          Integer population = Integer.parseInt((String) entry.getValue(test));
					          return population.intValue() == id;
					        }
					      };
					      
					      TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(f.getTable().getModel());
					      sorter.setRowFilter(filter);
					      f.getTable().setRowSorter(sorter);
						f.setVisible(true);
						System.out.println("Prozor otvoren: " + openedFromNext);

					}
					
					break;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		/*
		 * for(int k=0;k<MyMenuBar.tDescriptions.size();k++){
		 * 
		 * if(MyMenuBar.tDescriptions.get(k).getLabel().contains(string)){
		 * 
		 * 
		 * int red= ((Form)standardForm).getTable().getSelectedRow();
		 * 
		 * if(red>=0){ Form form = null; form = new
		 * Form(MainFrame.getInstance(), MyMenuBar.tDescriptions.get(k));
		 * 
		 * Vector<ColumnDescription> cdescription =
		 * DataBase.getDescriptions(((Form)
		 * standardForm).getDescription().getCode()); for(int j = 0; j <
		 * cdescription.size(); j++) { String sifra = (String) ((Form)
		 * standardForm).getTable().getValueAt(red, j); boolean
		 * primarni_kljuc=DataBase.isPrimaryKey(((Form)
		 * standardForm).getDescription().getCode(),cdescription.get(j).getCode(
		 * )); boolean strani_kljuc=DataBase.isForeignKey(((Form)
		 * standardForm).getDescription().getCode(),cdescription.get(j).getCode(
		 * ));
		 * 
		 * if(primarni_kljuc && strani_kljuc==false){
		 * 
		 * String column=DataBase.PrimaryKeyColumnname(((Form)
		 * standardForm).getDescription().getCode(),
		 * cdescription.get(j).getCode()); System.out.print(column);
		 * 
		 * System.out.print("SIF:"+sifra); try { form.nextFilter(sifra,column);
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } form.setVisible(true); break; } } }else{
		 * 
		 * Form form = null;
		 * 
		 * form = new Form(MainFrame.getInstance(),
		 * MyMenuBar.tDescriptions.get(k));
		 * 
		 * form.setVisible(true);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

	}

}