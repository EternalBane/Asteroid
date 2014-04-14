package game;

import java.io.File;

import javax.media.Format;
import javax.media.Player;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.format.AudioFormat;
import javax.media.pim.PlugInManager;

public class MySound implements Runnable {
	private Thread thread;
	private boolean infiniteLoop;
	private Player player;
	private File file;
	
	MySound(String name){
		setInfiniteLoop(false);
		
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);
		PlugInManager.addPlugIn(
			"com.sun.media.codec.audio.mp3.JavaDecoder",
			new Format[]{input1, input2},
			new Format[]{output},
			PlugInManager.CODEC
		);
		
		file = new File("sounds/"+name);
	}
	public void setInfiniteLoop(boolean b){
		infiniteLoop = b;
	}
	public void start(){
		if(SoundManager.sound){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void stop(){
		if(player!=null)
			player.stop();
	}
	
	public void run(){
		do{
			try{
				player = Manager.createPlayer(new MediaLocator("file:/"+file.getAbsolutePath()));
				player.start();
				Thread.sleep((long) (player.getDuration().getSeconds()*1000));
			} catch (Exception e){}
		} while(infiniteLoop);
	}
}
