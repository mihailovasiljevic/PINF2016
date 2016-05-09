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
	MyMenuBar mbar;
	TableDescription tdb;

	public NextFormAction(JDialog standardForm,String string) {
		this.standardForm  = standardForm;
		this.string =string;

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		for(int k=0;k<MyMenuBar.tDescriptions.size();k++){

			if(MyMenuBar.tDescriptions.get(k).getLabel().contains(string)){

				//	Form form = new Form(MainFrame.getInstance(),MyMenuBar.tDescriptions.get(k));
				//	Form form=new Form();
				/*MyTable mt=new MyTable(Form.description);
				System.out.print(Form.description.getCode());
				mt.next(MyMenuBar.tDescriptions.get(k));*/

				int red= ((Form)standardForm).getTable().getSelectedRow();
			/*	int broj_kol= ((Form)standardForm).getTable().getColumnCount();
				String s=  (String) ((Form) standardForm).getTable().getValueAt(0, 1);*/


				if(red>=0){
					/*for(int kol=0;kol<broj_kol;kol++){
						String sifra = (String) ((Form) standardForm).getTable().getValueAt(red, kol);
						System.out.print(sifra);*/
						Vector<ColumnDescription> cdescription = DataBase.getDescriptions(MyMenuBar.tDescriptions.get(k).getCode());
						for(int j = 0; j < cdescription.size(); j++) {
							String sifra = (String) ((Form) standardForm).getTable().getValueAt(red, j);
							boolean primarni_kljuc=DataBase.isPrimaryKey(MyMenuBar.tDescriptions.get(k).getCode(),cdescription.get(j).getCode());
							boolean strani_kljuc=DataBase.isForeignKey(MyMenuBar.tDescriptions.get(k).getCode(),cdescription.get(j).getCode());

							if(primarni_kljuc && strani_kljuc==false){

								Form form = null;

								form = new Form(MainFrame.getInstance(), MyMenuBar.tDescriptions.get(k));
								System.out.print("SIF:"+sifra);
								form.nextFilter(sifra);

								form.setVisible(true);
								System.out.print(sifra);
								break;
							}
						}
				}else{

					Form form = null;

					form = new Form(MainFrame.getInstance(), MyMenuBar.tDescriptions.get(k));

					form.setVisible(true);

				}

			}
			//	form.setVisible(true);
		}
	}



}