package game;

import java.util.Random;

public class Asteroid extends Actor {
	private int counter;
	private static final int LEFT = 0;
	private static final int UP = 1;
	private static final int RIGHT = 2;
	private static final int DOWN = 3;
	private int direction;
	private int rotation;
	private int moveAngle;
	private int points;
	
	Asteroid(){
		sprites.add(new Sprite("asteroid.png"));
		sprite = sprites.getSprite(0);
		angle = 0;
		randDirection();
		randX();
		randY();
		randAngle();
		randRotationAngle();
		randRotation();
		randScale();
		randSpeed();
		setPoints();
	}
	private void setPoints(){
		if(scale==1) points = 200;
		else if(scale==0.75) points = 150;
		else points = 100;
	}
	public int getPoints(){
		return points;
	}
	private void randDirection(){
		direction = random(4);
	}
	private void randX(){
		if(direction==LEFT)
			x = -sprite.getWidth();
		else if(direction==RIGHT)
			x = MainWindow.WIDTH;
		else {
			x = random(MainWindow.WIDTH);
		}
	}
	private void randY(){
		if(direction==UP)
			y = -sprite.getHeight();
		else if(direction==DOWN)
			y = MainWindow.HEIGHT;
		else {
			y = random(MainWindow.HEIGHT);
		}
	}
	private void randSpeed(){
		speed = random(6);
		if(scale==1) speed = random(6);
		else if(scale==0.75) speed = random(8);
		else speed = random(10);
		
		if(speed<2) speed=2;
	}
	private void randAngle(){
		moveAngle = random(361);
	}
	private void randRotationAngle(){
		
	}
	private void randRotation(){
		switch(random(2)){
		 	case 0: rotation = LEFT; break;
		 	case 1: rotation = RIGHT; break;
		}
	}
	private void randScale(){
		switch(random(3)){
			case 0: scale = 0.5; break;
			case 1: scale = 0.75; break;
			default: scale = 1.0; break;
		}
	}
	private int random(int max){
		Random rand = new Random();
		return rand.nextInt(max);
	}
	public void rotate(){
		if(rotation==LEFT)
			rotateLeft();
		else
			rotateRight();
	}
	public void move(){
		counter++;
		if(counter%Timer.asteroidMove==0)
			calculatePosition();
	}
	private void calculatePosition(){
		nx = Math.cos(Math.toRadians(moveAngle));
		ny = Math.sin(Math.toRadians(moveAngle));

		x+=speed*nx;
		y+=speed*ny;
		
		if(x>MainWindow.WIDTH) x = -getWidth();
		if(y>MainWindow.HEIGHT) y = -getHeight();
		
		if(x+getWidth()<0) x = MainWindow.WIDTH;
		if(y+getHeight()<0) y = MainWindow.HEIGHT;
	}
	public boolean checkColision(int x1, int y1, int x2, int y2){
		if(alive){
			if(colidate(x1, y1, x2, y2) ){
				alive = false;
				return true;
			}
		}
		return false;
	}
}
