package game;


import java.awt.Image;

public class Score {
	int score;
	int tempScore;
	GraphicalText imgScore;
	
	Score(){
		score = 0;
		tempScore = 0;
		imgScore = new GraphicalText(getScore());
	}
	public void increase(){
		if(tempScore>0){
			tempScore--;
			score++;
			imgScore.setText(getScore());
		}
	}
	public void addAll(){
		score+=tempScore;
		tempScore=0;
	}
	public void add(int score){
		tempScore += score;
	}
	public String getScore(){
		String strScore = Integer.toString(score);
		while(strScore.length()<8)
			strScore = new String("0"+strScore);
		return strScore;
	}
	public Image getImage(){
		return imgScore.getImage();
	}
	public int getWidth(){
		return imgScore.getWidth();
	}
	public int getHeight(){
		return imgScore.getHeight();
	}
}
