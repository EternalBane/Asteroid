package game;

import java.awt.Image;

public class SkillMissile extends Actor {
	private int curCooldown;
	private int curTime;
	private int duration;
	private boolean active;
	private Animation animation;
	
	SkillMissile(){
		duration = 200;
		active = false;
		
		sprites.add(new Sprite("missile.png"));
		sprite = sprites.getSprite(0);
		
		animation = new Animation();
		animation.add(new Sprite("missile.png"),2);
		
	}
	public void activate(int x, int y, int angle){
		if(curCooldown==0 && !active){
			active = true;
			curTime = 0;
			this.x = x-getWidth()/2;
			this.y = y-getHeight()/2;
			this.angle = angle;
			
			speed = 40;
			move();
			speed = 3;
		}
	}
	public void reset(){
		curCooldown = 0;
		active = false;
	}
	public boolean isActive(){
		return active;
	}
	public Image getImage(){
		return animation.getSprite().getImage();
	}
	public void move(){
		if(active){
			curTime++;
			if(curTime>duration)
				active = false;
			calculatePosition();
		}
	}
	private void calculatePosition(){
		nx = Math.cos(getRadians());
		ny = Math.sin(getRadians());
		
		x+=speed*nx;
		y+=speed*ny;
		
		if(x>MainWindow.WIDTH) x = -sprite.getWidth();
		if(y>MainWindow.HEIGHT) y = -sprite.getHeight();
		
		if(x+sprite.getWidth()<0) x = MainWindow.WIDTH;
		if(y+sprite.getHeight()<0) y = MainWindow.HEIGHT;
	}
}
