package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class GameView extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private Thread thread;
	private int speed;
	private JPanel view;
	protected Player player;
	protected Asteroids asteroids;
	private Sprite bg;
	protected Score score;
	protected Time time;
	private Font font;
	protected boolean loop;
	protected Act act;
	protected SkillMissile missile;
		
	GameView(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		speed = 10;
		view = new JPanel();
		view.setLayout(null);
		view.setSize(640,480);
		loop = true;
		
		createGUI();
		
		
		mainWindow.soundManager.playBG();
		score = new Score();
		player = new Player();
		act = new Act(this);
		act.restartStage();
	}
	private void createGUI(){
		bg = new Sprite("bgGame.png");
		font = new Font("Verdana",Font.BOLD,20);
		missile = new SkillMissile();
	}
	private AffineTransform transform(AffineTransform transform, Actor actor){
        transform.setToTranslation(actor.getX(),actor.getY());
        transform.scale(actor.getScale(),actor.getScale());
        transform.rotate(actor.getRadians(),actor.getWidth()/2,actor.getHeight()/2);
		return transform;
	}
	
	public void paint (Graphics g) {
		AffineTransform transform;
		Graphics2D g2 = (Graphics2D) g;	
		g2.setColor(Color.WHITE);
		g2.setFont(font);
		
		
		// Background
		g2.drawImage(bg.getImage(),0,0,bg.getWidth(),bg.getHeight(),null);	
        
		// >> Asteroids
        for(int i=0; i<asteroids.getCount(); i++){
        	transform = transform(new AffineTransform(),asteroids.get(i));
            g2.drawImage(asteroids.get(i).getImage(), transform, this);
        }
		
        if(player.getProtect().isActive())
        	g2.drawImage(player.getProtect().getImage().getImage(),player.getX()-37,player.getY()-37,null);
        
        if(missile.isActive()){
        	transform = transform(new AffineTransform(),missile);
        	g2.drawImage(missile.getImage(), transform, this);
        }
        // >> Player
		transform = transform(new AffineTransform(),player);

        g2.drawImage(player.getImage(), transform, this);

        
        // >> GUI
        g2.drawImage(score.getImage(),15,15,score.getWidth(),score.getHeight(),null);
        g2.drawImage(time.getImage(),MainWindow.WIDTH/2-time.getWidth()/2,15,time.getWidth(),time.getHeight(),null);
        g2.drawImage(player.getLivesImage(),MainWindow.WIDTH-player.getLivesWidth()-15,15,player.getLivesWidth(),player.getLivesHeight(),null);
        g2.drawImage(act.getImage(),MainWindow.WIDTH/2-act.getWidth()/2,MainWindow.HEIGHT-act.getHeight()-15,act.getWidth(),act.getHeight(),null);
	}	
	public void start(){
		if(thread==null){
			thread = new Thread(this);
			thread.start();
		}
	}
	public void resume(){
		mainWindow.soundManager.playBG();
		loop=true;
		thread = new Thread(this);
		thread.start();
	}
	public void run() {
		while(thread==Thread.currentThread() && loop){		
			player.move(mainWindow.activeKeys);
			asteroids.move();
			time.decreaseTime();
			score.increase();
			
			for(int i=0; i<asteroids.getCount(); i++){
				Asteroid asteroid = asteroids.get(i);
				boolean b = player.checkColision(asteroid.getX(),asteroid.getY(),asteroid.getX()+asteroid.getSWidth(),asteroid.getY()+asteroid.getSWidth());
				if(b){
					asteroids.destroy(i);
					score.add(asteroid.getPoints());
				}
				
				if(missile.isActive()){
					boolean b2 = asteroid.checkColision(missile.getX(),missile.getY(),missile.getX()+missile.getSWidth(),missile.getY()+missile.getSHeight());
					if(b2){
						asteroids.destroy(i);
						score.add(asteroid.getPoints());
						missile.reset();
					}
				}
			}
			asteroids.checkColision(player.getX(),player.getY(),player.getX()+player.getWidth(),player.getY()+player.getHeight());
		
			
			// PROTECT SKILL
			if(mainWindow.activeKeys.isExists(ActiveKeys.PROTECT))
				player.activateProtect();
			// MISSILE SKILL
			if(mainWindow.activeKeys.isExists(ActiveKeys.SHOOT))
				missile.activate(player.getX()+player.getWidth()/2,player.getY()+player.getHeight()/2,player.getAngle());
			// MISSILE SKILL MOVE
			if(missile.isActive())
				missile.move();
			
			// GAME OVER
			if(player.getLives()==0 && !player.isExploding()){
				loop = false;
				score.addAll();
				mainWindow.viewManager.setResume(false);
				mainWindow.viewManager.setView(ViewManager.GAME_OVER);
				mainWindow.soundManager.stopBG();
			}
			
			// PLAYER DIED > RESTART STAGE
			else if(!player.isAlive() && player.getLives()>0)
				act.restartStage();
			
			// BACK TO MENU
			if(mainWindow.activeKeys.isExists(KeyEvent.VK_ESCAPE)){
				mainWindow.soundManager.stopBG();
				loop = false;
				mainWindow.viewManager.setResume(true);
				mainWindow.viewManager.setView(ViewManager.MENU);
			}
			
			// NEXT STAGE
			if(time.isEnded())
				act.nextStage();
			
			repaint();
			try { Thread.sleep(speed);
			} catch (InterruptedException e) {}
		}
	}
}
