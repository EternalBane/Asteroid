package game;

public class SoundManager {
	MySound bg;
	public static boolean sound = true;
	
	SoundManager(){
		bg = new MySound("game.mp3");
		bg.setInfiniteLoop(true);
	}
	public void playBG(){
		bg.stop();
		bg.start();
	}
	public void stopBG(){
		bg.stop();
	}
	public static void playExplosion(){
		new MySound("explosion.mp3").start();
	}
	public static void playMenuHover(){
		new MySound("menuHover.mp3").start();
	}
	public static void setSound(boolean b){
		sound = b;
	}
}
