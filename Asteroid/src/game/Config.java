package game;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Config {
	private int[] config = new int[8];
	private String[] properties = new String[8];
	private File file;
	private FileInputStream fstream;
	
	Config(){
		file = new File("config.txt");
		config[7] = -1;
		loadConfig();
		defaultConfig();
		SoundManager.setSound(getSound());
	}
	private void loadConfig(){
		properties[0] = "left";
		properties[1] = "right";
		properties[2] = "up";
		properties[3] = "down";
		properties[4] = "protect";
		properties[5] = "shoot";
		properties[6] = "shoot2";
		properties[7] = "sound";
		
		if(file.canRead())
		     try {
		         fstream = new FileInputStream(file);

		         DataInputStream in = new DataInputStream(fstream);
		         BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(file), "UTF-8"));
		         String strLine;
		         while ((strLine = br.readLine()) != null)   { 
		             String[] values = strLine.split(";");
		             if(values.length == 2) add(values);
		         }
		         in.close();
		         br.close();
		     } catch (FileNotFoundException e) {
		     } catch (IOException e) {
		     }
		
		int key;
		for(int i=0; i<config.length-1; i++){
			key = config[0];
			for(int j=i+1; j<config.length-1; j++){
				if(config[j]==key)
					config[j]=0;
			}
		}
	}
	private void defaultConfig(){
		if(config[0]==0)
			config[0]=KeyEvent.VK_LEFT;
		if(config[1]==0)
			config[1]=KeyEvent.VK_RIGHT;
		if(config[2]==0)
			config[2]=KeyEvent.VK_UP;
		if(config[3]==0)
			config[3]=KeyEvent.VK_DOWN;
		if(config[4]==0)
			config[4]=KeyEvent.VK_A;
		if(config[5]==0)
			config[5]=KeyEvent.VK_S;
		if(config[6]==0)
			config[6]=KeyEvent.VK_D;
		if(config[7]==-1)
			config[7]=1;
	}
	private void add(String[] values){
		for(int i=0; i<config.length; i++){
			if(values[0].equals(properties[i]))
				config[i] = strToInt(values[1]);
		}
	}
	private int strToInt(String str){
		int i = 0;
		try{ i = Integer.parseInt(str);
		} catch(Exception e){}
		if((i>=32 && i<=126) || i==1)
			return i;
		else 
			return 0;
	}
	private void saveConfig(){
		String str = new String();
		for(int i=0; i<config.length; i++)
			str = new String(str+properties[i]+";"+config[i]+"\r\n");
		
		BufferedWriter writer = null;
		try{
		    writer = new BufferedWriter( new FileWriter(file));
		    writer.write(str);
		} catch ( IOException e){}
		finally{
		    try{ if ( writer != null)
		         writer.close( );
		    }catch ( IOException e){}
		}
	}
	public int getLeft(){
		return config[0];
	}
	public int getRight(){
		return config[1];
	}
	public int getUp(){
		return config[2];
	}
	public int getDown(){
		return config[3];
	}
	public int getProtect(){
		return config[4];
	}
	public int getShoot(){
		return config[5];
	}
	public int getShoot2(){
		return config[6];
	}
	public boolean getSound(){
		if(config[7]==1)
			return true;
		else
			return false;
	}
	public void setLeft(int left) {
		config[0] = left;
		saveConfig();
	}
	public void setRight(int right) {
		config[1] = right;
		saveConfig();
	}
	public void setUp(int up) {
		config[2] = up;
		saveConfig();
	}
	public void setDown(int down) {
		config[3] = down;
		saveConfig();
	}
	public void setProtect(int protect) {
		config[4] = protect;
		saveConfig();
	}
	public void setShoot(int shoot) {
		config[5] = shoot;
		saveConfig();
	}
	public void setSound(boolean sound) {
		if(sound)
			config[7] = 1;
		else
			config[7] = 0;
		saveConfig();
	}
}
