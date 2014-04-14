package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class GraphicalText {
	private static final int sHeight = 27;
	private int width;
	private String text;
	private static final int[] sWidth = {20,14,16,17,20,17,18,18,18,18,10,24};
	Image img;
	
	GraphicalText(String text){
		setText(text);
	}
	public void setText(String text){
		this.text = text;
		calcBitmapWidth();
		createBitmap();
	}
	private void calcBitmapWidth(){
		width = 0;
		for(int i=0; i<text.length(); i++)
			width+=getDigitWidth(text.charAt(i));
	}
	private int getDigitWidth(char c){
		if(c=='0' || c=='1' || c=='2' ||c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9')
				return sWidth[str2Int(c+"")];
			else if(c==':')
				return sWidth[10];
			else if(c=='x')
				return sWidth[11];
			else
				return 0;
	}
	private int str2Int(String str){
		int i = 0;
		try{ i = Integer.parseInt(str);
		} catch(Exception e){}
		return i;
	}
	private void createBitmap(){
		BufferedImage image = new BufferedImage(width,sHeight,BufferedImage.TYPE_INT_ARGB); 
		Graphics g = image.getGraphics();
		ImageObserver observer = new ImageObserver() {
					public boolean imageUpdate(Image img, int infoflags, int x, int y,
							int width, int height) {
						return false;
					}
				};
				int curX = 0;
				for(int i=0; i<text.length(); i++){
					ImageIcon img = new ImageIcon(getClass().getResource("/images/digits/"+getFileName(text.charAt(i))));
					g.drawImage(img.getImage(),curX,0,getDigitWidth(text.charAt(i)),sHeight,observer); 
					curX+=getDigitWidth(text.charAt(i));
				}
				img = new ImageIcon(image).getImage();
	}
	private String getFileName(char text){
		String txt = "";
		switch(text){
			case '0': txt = "0"; break;
			case '1': txt = "1"; break;
			case '2': txt = "2"; break;
			case '3': txt = "3"; break;
			case '4': txt = "4"; break;
			case '5': txt = "5"; break;
			case '6': txt = "6"; break;
			case '7': txt = "7"; break;
			case '8': txt = "8"; break;
			case '9': txt = "9"; break;
			case ':': txt = "colon"; break;
			case 'x': txt = "x"; break;
		}
		txt = new String(txt+".png");
		return txt;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return sHeight;
	}
	public Image getImage(){
		return img;
	}
}
