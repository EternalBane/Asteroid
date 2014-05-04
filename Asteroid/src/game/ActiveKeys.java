package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActiveKeys implements KeyListener {
	private int[] list = new int[0];
	private boolean set;
	public static int LEFT,RIGHT,DOWN,UP,PROTECT,SHOOT;
	private String setType;
	private OptionsView optionsView;
	
	ActiveKeys(MainWindow frame){
		LEFT = frame.config.getLeft();
		RIGHT = frame.config.getRight();
		DOWN = frame.config.getDown();
		UP = frame.config.getUp();
		PROTECT = frame.config.getProtect();
		SHOOT = frame.config.getShoot();
	}
	public void add(int key){
		if(!isExists(key)){
			int[] temp = new int[list.length+1];
			for(int i=0; i<list.length; i++)
				temp[i] = list[i];
			temp[temp.length-1] = key;
			
			list = temp;
			//dispKeys();
		}
	}
	public void remove(int key){
		if(isExists(key)){
			int tempI = 0;
			int[] temp = new int[list.length-1];
			for(int i=0; i<list.length; i++)
				if(list[i]!=key){
					temp[tempI] = list[i];
					tempI++;
				}
			list = temp;
			//dispKeys();
		}
	}
	public void removeAll(){
		list = new int[0];
	}
	public boolean isExists(int key){
		if(key==0) remove(key);
		for(int i=0; i<list.length; i++)
			if(list[i]==key)
				return true;
		return false;
	}
	public void dispKeys(){
		System.out.print("Keys: ");
		for(int i=0; i<list.length; i++)
			System.out.print(list[i]+" ");
		System.out.println("");
	}
	public void keyPressed(KeyEvent e) {
		add(e.getKeyCode());
		
		if(set){
			if(setType.equals("LEFT"))
				LEFT = e.getKeyCode();
			else if(setType.equals("RIGHT"))
				RIGHT = e.getKeyCode();
			else if(setType.equals("UP"))
				UP = e.getKeyCode();
			else if(setType.equals("DOWN"))
				DOWN = e.getKeyCode();
			else if(setType.equals("PROTECT"))
				PROTECT = e.getKeyCode();
			else if(setType.equals("SHOOT"))
				SHOOT = e.getKeyCode();
			
			optionsView.setKey(setType,getKeyChar(e.getKeyCode()));
			set = false;
		}
	}
	public static String getKeyChar(int key){
		if(key==32)
			return "Spacja";
		if(key==37)
			return "Lewo";
		if(key==38)
			return "Góra";
		if(key==39)
			return "Prawo";
		if(key==40)
			return "Dó³";
		else
			return KeyEvent.getKeyText(key);
	}
	public void keyReleased(KeyEvent e) {
		remove(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void unsetKey(){
		set = false;
	}
	public void setKey(String key, OptionsView optionsView){
		this.optionsView = optionsView;
		setType = key;
		set = true;
	}
}
