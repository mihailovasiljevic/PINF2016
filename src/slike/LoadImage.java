package slike;

import java.awt.Image;

import javax.swing.ImageIcon;

public class LoadImage extends ImageIcon {	
	// CONSTRUCTORS
	public LoadImage(String IconName) {
		super(loadIcon(IconName,30,30));
	}
	
	public LoadImage(String IconName, int width, int height) {
		super(loadIcon(IconName,width,height));
	}
	
	// METHODS
	private static Image loadIcon(String IconName, int width, int height) {
		ImageIcon icon = new ImageIcon(IconName);
		Image image= icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return image;
	}
	
	public LoadImage(ImageIcon icon, int width, int height) {
		super(loadIcon(icon,width,height));
	}
	
	private static Image loadIcon(ImageIcon icon, int width, int height) {
		Image image= icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return image;
	}
	
}
