package toolbar.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

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


	public NextFormAction(JDialog standardForm,String string) {
		this.standardForm  = standardForm;
		this.string =string;

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		for(int k=0;k<MyMenuBar.tDescriptions.size();k++){

			if(MyMenuBar.tDescriptions.get(k).getLabel().contains(string)){


				int red= ((Form)standardForm).getTable().getSelectedRow();

				if(red>=0){
					Form form = null;
					form = new Form(MainFrame.getInstance(), MyMenuBar.tDescriptions.get(k));
					
						Vector<ColumnDescription> cdescription = DataBase.getDescriptions(((Form) standardForm).getDescription().getCode());
						for(int j = 0; j < cdescription.size(); j++) {
							String sifra = (String) ((Form) standardForm).getTable().getValueAt(red, j);
							boolean primarni_kljuc=DataBase.isPrimaryKey(((Form) standardForm).getDescription().getCode(),cdescription.get(j).getCode());
							boolean strani_kljuc=DataBase.isForeignKey(((Form) standardForm).getDescription().getCode(),cdescription.get(j).getCode());

							if(primarni_kljuc && strani_kljuc==false){
								
								String column=DataBase.PrimaryKeyColumnname(((Form) standardForm).getDescription().getCode(), cdescription.get(j).getCode());
								System.out.print(column);

								System.out.print("SIF:"+sifra);
								try {
									form.nextFilter(sifra,column);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								form.setVisible(true);
								break;
							}
						}
				}else{

					Form form = null;

					form = new Form(MainFrame.getInstance(), MyMenuBar.tDescriptions.get(k));

					form.setVisible(true);

				}

			}

		}
	}



}