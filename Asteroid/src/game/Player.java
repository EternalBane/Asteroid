package game;

import java.awt.Image;

public class Player extends Actor {
	private int counter;
	Animation anim;
	Animation explosion;
	private int explosionTime;
	private boolean exp;
	private SkillProtect protect;
	private GraphicalText imgLives;
	
	Player(){
		lives = 3;
		imgLives = new GraphicalText("x"+lives);
		sprites.add(new Sprite("player.png"));
		explosionTime = 0;
		exp = false;
		
		anim = new Animation();
		anim.add(new Sprite("player.png"));
		
		explosion = new Animation();
		explosion.add(new Sprite("explosion/explosion_1.png"),4);
		explosion.add(new Sprite("explosion/explosion_2.png"),4);
		explosion.add(new Sprite("explosion/explosion_3.png"),4);
		explosion.add(new Sprite("explosion/explosion_4.png"),4);
		explosion.add(new Sprite("explosion/explosion_5.png"),4);
		explosion.add(new Sprite("explosion/explosion_6.png"),4);
		explosion.add(new Sprite("explosion/explosion_7.png"),4);
		explosion.add(new Sprite("explosion/explosion_8.png"),4);
		explosion.add(new Sprite("explosion/explosion_9.png"),4);
		explosion.add(new Sprite("explosion/explosion_10.png"),4);
		explosion.add(new Sprite("explosion/explosion_11.png"),4);
		explosion.add(new Sprite("explosion/explosion_12.png"),4);
		explosion.add(new Sprite("explosion/explosion_13.png"),4);
		explosion.add(new Sprite("explosion/explosion_14.png"),4);
		explosion.add(new Sprite("explosion/explosion_15.png"),4);
		explosion.add(new Sprite("explosion/explosion_16.png"),4);
		explosion.add(new Sprite("explosion/explosion_17.png"),4);
		explosion.add(new Sprite("explosion/explosion_18.png"),4);
		explosion.add(new Sprite("explosion/explosion_19.png"),4);
		explosion.add(new Sprite("explosion/explosion_20.png"),4);
		explosion.add(new Sprite("explosion/explosion_21.png"),4);
		explosion.add(new Sprite("explosion/explosion_22.png"),4);
		explosion.add(new Sprite("explosion/explosion_23.png"),4);
		explosion.add(new Sprite("explosion/explosion_24.png"),4);
		explosion.add(new Sprite("explosion/explosion_25.png"),4);
		explosion.add(new Sprite("explosion/explosion_26.png"),4);
		explosion.add(new Sprite("explosion/explosion_27.png"),4);
		explosion.add(new Sprite("explosion/explosion_28.png"),4);
		explosion.add(new Sprite("explosion/explosion_29.png"),4);
		
		
		protect = new SkillProtect();
		
		
		
		sprite = sprites.getSprite(0);
		
		startPosition();
	}
	public void activateProtect(){
		if(alive)
			protect.activate();
	}
	public SkillProtect getProtect(){
		return protect;
	}
	public void restart(){
		startPosition();
		speed = 0;
		alive = true;
	}
	private void startPosition(){
		angle = 270;
		x = MainWindow.WIDTH/2-getWidth()/2;
		y = MainWindow.HEIGHT/2-getHeight()/2;		
	}
	public void moveForward(){
		if(speed>0)
			calculatePosition();
	}
	public void move(ActiveKeys activeKeys){
		protect.update();
		if(alive){
		counter++;
		
		
		if(activeKeys.isExists(ActiveKeys.LEFT) && !activeKeys.isExists(ActiveKeys.RIGHT))
			rotateLeft();
		else if(activeKeys.isExists(ActiveKeys.RIGHT) && !activeKeys.isExists(ActiveKeys.LEFT))
			rotateRight();
		
		if(activeKeys.isExists(ActiveKeys.UP))
			increaseSpeed();
		else if(counter%Timer.playerSlowDown==0) 
			decreaseSpeed();
		
		if(activeKeys.isExists(ActiveKeys.DOWN) && counter%Timer.playerSlowDown==0)
			decreaseSpeed();
		
		if(counter%Timer.playerMove==0)
			moveForward();
		}
	}
	public void increaseSpeed(){
		if(speed<=7.9)
			speed+=0.1;
	}
	public void decreaseSpeed(){
		if(speed>0)
		speed-=0.1;
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
	public boolean checkColision(int x1, int y1, int x2, int y2){
		if(alive){
			if(colidate(x1, y1, x2, y2) ){
				if(!getProtect().isActive()){
					lives--;
					imgLives.setText("x"+lives);
					alive = false;
					explosionTime = 0;
					exp = true;
					SoundManager.playExplosion();
				}
				return true;
			}
		}
		return false;
	}
	public Image getImage(){
		explosionTime++;
		if(explosionTime>=explosion.getTotalTime()){
			exp = false;
		}
		if(alive){
			return anim.getSprite().getImage();
		} else {
			return explosion.getSprite().getImage();
		}
	}
	public boolean isAlive(){
		if(exp==true){
			return true;
		} else
			return alive;
	}
	public int getLives(){
		return lives;
	}
	public int getLivesWidth(){
		return imgLives.getWidth();
	}
	public int getLivesHeight(){
		return imgLives.getHeight();
	}
	public Image getLivesImage(){
		return imgLives.getImage();
	}
	public boolean isExploding(){
		return exp;
	}
}
