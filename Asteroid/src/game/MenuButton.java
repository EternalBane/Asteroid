package game;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class MenuButton extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Sprite img,img2;
	private int x;
	private int y;
	private boolean hover;
	private MainWindow frame;
	private boolean singleImage = false;
	
	MenuButton(MainWindow frame,String file, int y){
		this.frame = frame;
		hover = false;
		this.y = y;
		
		createImage(file);
	}
	MenuButton(String file){
		hover = false;
		singleImage = true;
		
		createImageSingle(file);
	}
	MenuButton(String file,int y){
		hover = false;
		singleImage = true;
		
		createImage(file);
	}
	private void createImage(String file){
		img = new Sprite(file);
		if(!singleImage)
			img2 = new Sprite(file.replace(".","_h."));
		x = MainWindow.WIDTH/2-img.getWidth()/2;

		setContentAreaFilled(false);
		addMouseListener(this);
	}
	private void createImageSingle(String file){
		img = new Sprite(file);
		setContentAreaFilled(false);
		addMouseListener(this);
	}
	public int getX(){
		if(hover)
			return x-(img2.getWidth()-img.getWidth())/2;
		else 
			return x;
	}
	public int getY(){
		if(hover)
			return y-(img2.getHeight()-img.getHeight())/2;
		else 
			return y;
	}
	public int getWidth(){
		if(hover)
			return img2.getWidth();
		else
			return img.getWidth();
	}
	public int getHeight(){
		if(hover)
			return img2.getHeight();
		else
			return img.getHeight();
	}
	public void paint(Graphics g){
		if(hover && !singleImage)
			g.drawImage(img2.getImage(),0,0,getWidth(),getHeight(),null);
		else
			g.drawImage(img.getImage(),0,0,getWidth(),getHeight(),null);
	}
	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		SoundManager.playMenuHover();
		hover = true;
		repaint();
		frame.repaint();
	}
	public void setX(int x){
		this.x = x;
	}
	public void mouseExited(MouseEvent e) {
		hover = false;
		repaint();
		frame.repaint();
	}
	public void mouseClicked(MouseEvent e) {

	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
}
