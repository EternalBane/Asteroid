package game;

import java.awt.Image;

public class Actor {
	protected SpriteList sprites;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double speed;
	protected int lives;
	protected boolean alive;
	protected Sprite sprite;
	protected int angle;
	protected double nx = 0;
	protected double ny = 0;
	protected double scale;
	
	Actor(){
	  sprites = new SpriteList();
	  speed = 1;
	  scale = 1;
	  alive = true;
	}
	public Image getImage(){
		return sprite.getImage();
	}
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public void rotateLeft(){
		angle-=2;
		if(angle<0)
			angle = 360;
	}
	public void rotateRight(){
		angle+=2;
		if(angle>360)
			angle = 0;
	}
	public int getWidth() {
		return sprite.getWidth();
	}
	public int getHeight() {
		return sprite.getHeight();
	}
	public int getSWidth() {
		return (int)(sprite.getWidth()*scale);
	}
	public int getSHeight() {
		return (int)(sprite.getHeight()*scale);
	}
	public int getSpeed(){
		return (int)speed;
	}
	public int getHealth(){
		return lives;
	}
	public boolean isAlive(){
		if(lives>0)
			return true;
		else return false;
	}
	public int getLives(){
		return lives;
	}
	public int getAngle(){
		return angle;
	}
	public double getRadians(){
		return Math.toRadians(angle);
	}
	public double getScale(){
		return scale;
	}
	protected boolean colidate(int x1, int y1, int x2, int y2){
		int left = getX();
		int right = getX()+getSWidth();
		int top = getY();
		int bottom = getY()+getSHeight();
		
		for (int x = x1+5; x < x2-5; x++) {
			  for (int y = y1+5; y < y2-5; y++) {
			     if (x>=left && x<=right && y>=top && y<=bottom) {
			        return true;
			     }
			  }
			}
		
		return false;
	}
}
