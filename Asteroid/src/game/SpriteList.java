package game;

public class SpriteList {
	private Sprite[] list = new Sprite[0];
	
	SpriteList(){
		
	}
	public void add(Sprite sprite){
		Sprite[] temp = new Sprite[list.length+1];
		for(int i=0; i<list.length; i++)
			temp[i] = list[i];
		temp[list.length] = sprite;
		list = temp;
	}
	public Sprite getSprite(int i){
	  if(i<list.length)
		  return list[i];
	  else 
		  return null;
	}
}
