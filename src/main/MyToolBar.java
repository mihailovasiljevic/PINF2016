package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;

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

public class MyToolBar extends JToolBar {

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
		
		button = new JButton(new ImageIcon(getClass().getResource("/slike/nextForm.gif")));
		button.addActionListener(new NextFormAction(dialog));
		this.add(button);
		

		
		
		
		
		this.setFloatable(false);
		
	}
	
}
