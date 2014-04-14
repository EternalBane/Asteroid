package game;

import java.awt.Image;

public class Time {
	private int time;
	private int counter;
	GraphicalText imgTime;
	
	Time(int minutes){
		time = minutes*60;
		imgTime = new GraphicalText(getTime());
	}
	public void decreaseTime(){
		counter++;
		if(time>0 && counter%Timer.time==0){
			time--;
			counter=0;
		}
		imgTime.setText(getTime());
	}
	public Image getImage(){
		return imgTime.getImage();
	}
	public int getWidth(){
		return imgTime.getWidth();
	}
	public int getHeight(){
		return imgTime.getHeight();
	}
	public String getTime(){
		int minutes = 0;
		int seconds = time;
		while(seconds>=60){
			seconds-=60;
			minutes++;
		}
		String time;
		
		if(minutes<10)
			time = new String("0"+minutes+":");
		else
			time = new String(minutes+":");
		
		if(seconds<10)
			time = new String(time+"0"+seconds);
		else
			time = new String(time+seconds);
		
		return time;
	}
	public boolean isEnded(){
		if(time>0)
			return false;
		else 
			return true;
	}
}
