package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sprite {	
	private ImageIcon img;
	
	Sprite(String name){
		img = new ImageIcon(getClass().getResource("/images/"+name));
	}
	public Image getImage(){
		return img.getImage();
	}
	public ImageIcon getImageIcon(){
		return new ImageIcon(img.getImage());
	}
	public int getWidth(){
		return img.getIconWidth();
	}
	public int getHeight(){
		return img.getIconHeight();
	}
}
