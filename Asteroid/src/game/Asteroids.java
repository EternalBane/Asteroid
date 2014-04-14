package game;

public class Asteroids {
	private Asteroid[] asteroidList = new Asteroid[0];
	private int num;
	
	Asteroids(){
		num = 0;
	}
	public void setNum(int num){
		this.num = num;
	}
	private void newAsteroid(){
		Asteroid[] temp = new Asteroid[asteroidList.length+1];
		for(int i=0; i<asteroidList.length; i++)
			temp[i] = asteroidList[i];
		temp[asteroidList.length] = new Asteroid();
		asteroidList = temp;
	}
	private void destroyAsteroid(int a){
		Asteroid[] temp = new Asteroid[asteroidList.length-1];
		int n=0;
		for(int i=0; i<asteroidList.length; i++)
			if(i!=a){
				temp[n] = asteroidList[i];
				n++;
			}
		asteroidList = temp;
	}
	public void move(){
		if(asteroidList.length<num)
			newAsteroid();
		for(int i=0; i<asteroidList.length; i++){
			asteroidList[i].move();
			asteroidList[i].rotate();
		}
	}
	public Asteroid get(int i){
		return asteroidList[i];
	}
	public int getCount(){
		return asteroidList.length;
	}
	public void checkColision(int x1, int y1, int x2, int y2){
		for(int i=0; i<asteroidList.length; i++)
			if(asteroidList[i].checkColision(x1, y1, x2, y2))
				destroy(i);
	}
	public void destroy(int i){
		destroyAsteroid(i);
	}
}
