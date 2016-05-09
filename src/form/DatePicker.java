package form;
 
import main.MainFrame;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
/** Klasa koja olakšava korisniku unos datuma.*/
public class DatePicker {
 
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
      int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      JLabel l = new JLabel("", JLabel.CENTER);
      String day = "";
      JDialog d;
      JButton[] button = new JButton[49];
      String[] months = { "Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust",
                "Septembar", "Oktobar", "Novembar", "Decembar" };
     
      public DatePicker(Window owner) {
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Pon", "Uto", "Sre", "Čet", "Pet", "Sub", "Ned" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(owner.getWidth(), 120));
        d.setSize(550, 120);
        for (int x = 0; x < button.length; x++) {
          final int selection = x;
          button[x] = new JButton();
          button[x].setFocusPainted(false);
          button[x].setBackground(Color.white);
          if (x > 6) {
            button[x].addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent ae) {
                day = button[selection].getActionCommand();
                d.dispose();
              }
            });
          }
          if (x < 7) {
            button[x].setText(header[x]);
            button[x].setForeground(Color.red);
          }
          p1.add(button[x]);
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));
         
        // Previous month button
        JButton previous = new JButton("<< Prethodni");
        previous.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            if(month == 0) {
                month = 11;
                year--;
            } else {
                month--;
            }
            displayDate();
          }
        });
        p2.add(previous);
         
        // Current month label between the previous and next buttons
        p2.add(l);
         
        // Next month button
        JButton next = new JButton("Sledeći >>");
        next.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            if(month == 11) {
                month = 0;
                year++;
            } else {
                month++;
            }
            displayDate();
          }
        });
        p2.add(next);
         
        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setSize(430, 280);
        d.setResizable(false);
        d.setLocationRelativeTo(owner);
        displayDate();
        d.setVisible(true);
      }
     
      public void displayDate() {
        for (int x = 7; x < button.length; x++) {
          button[x].setText("");
        }
         
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
     
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
          button[x].setText("" + day);
        }
       
        //l.setText(sdf.format(cal.getTime()));
        l.setText(months[month] + " " + year);
        d.setTitle("Kalendar");
      }
     
      public String getPickedDate() {
        if (day.equals("")) {
          return day;
        }
       
        // Set the return date format
        //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(MainFrame.getInstance().getResourceBundle().getString("date"));
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy");  
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
      }
    }
