package game;

public class Animation {
	private Sprite[] list;
	private int[] spriteTime;
	private int sceneIndex;
	private int movieTime;
	private int totalTime;
	private boolean animationEnded;

	Animation(){
		list = new Sprite[0];
		spriteTime = new int[0];
		totalTime = 0;
		animationEnded = false;
		start();
	}
	public synchronized void add(Sprite sprite){
		addScene(sprite,-1);
	}
	public synchronized void add(Sprite sprite, int t){
		addScene(sprite,t);
	}
	private synchronized void addScene(Sprite sprite, int t){
		if(t==-1){
			totalTime=-1;
		}
		else
			totalTime += t;
		
		Sprite[] temp = new Sprite[list.length+1];
		for(int i=0; i<list.length; i++)
			temp[i] = list[i];
		temp[list.length] = sprite;
		list = temp;
		
		int[] tempTime = new int[spriteTime.length+1];
		for(int i=0; i<spriteTime.length; i++)
			tempTime[i] = spriteTime[i];
		tempTime[spriteTime.length] = t;
		spriteTime = tempTime;
	}
	private synchronized void start(){
		movieTime = 0;
		sceneIndex = 0;
	}
	public synchronized Sprite getSprite(){
		if(list.length==0){
			return null;
		}else{
			if(totalTime==-1)
				return list[0];
			
			movieTime++;
			if(movieTime>=spriteTime[sceneIndex]){
				movieTime=0;
				if(sceneIndex+1<list.length)
					sceneIndex++;
				else {
					sceneIndex=0;
				}
			}
			return list[sceneIndex];
		}
	}
	public boolean isAnimationEnded(){
		return animationEnded;
	}
	public int getTotalTime(){
		return totalTime;
	}
}
