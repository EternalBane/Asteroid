package game;

import java.awt.Image;

public class Act {
	private int act;
	private GameView gameView;
	private int asteroids;
	GraphicalText imgStage;
	int time;
	
	Act(GameView gameView){
		this.gameView = gameView;
		act = 1;
		asteroids = 4;
		time = 2;
		imgStage = new GraphicalText(Integer.toString(act));
	}
	public void setAsteroids(int asteroids){
		this.asteroids = asteroids;
		restartStage();
	}
	public void setTime(int time){
		this.time = time;
		gameView.time = new Time(time);
	}
	public int getStage(){
		return act;
	}
	public void restartStage(){
		gameView.player.restart();
		gameView.asteroids = new Asteroids();
		gameView.time = new Time(time);
		gameView.player.getProtect().reset();
		gameView.asteroids.setNum(asteroids);
		gameView.missile.reset();
	}
	public void nextStage(){
		act++;
		asteroids+=2;
		imgStage.setText(Integer.toString(act));
		restartStage();
	}
	public Image getImage(){
		return imgStage.getImage();
	}
	public int getWidth(){
		return imgStage.getWidth();
	}
	public int getHeight(){
		return imgStage.getHeight();
	}
}
