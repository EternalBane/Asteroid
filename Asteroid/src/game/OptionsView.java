package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class OptionsView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MenuButton bBackToMenu;
	private MainWindow mainWindow;
	private Sprite bg;
	private JLabel bgLabel;
	private JTextField tfLeft,tfRight,tfUp,tfDown,tfProtect,tfShoot;
	private JButton bSound;
	private MenuButton bLeft,bRight,bUp,bDown,bProtect,bShoot;
	private int menuY;
	
	private JLabel lSound,lLeft,lRight,lUp,lDown,lProtect,lShoot;
	
	Border borderGreen = BorderFactory.createMatteBorder(2,2,2,2,Color.green);
	Border borderBlack = BorderFactory.createMatteBorder(2,2,2,2,Color.black);
	
	
	OptionsView(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		setLayout(null);
		menuY = 348;
	
		createGUI();
	}
	private void createGUI(){
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);
		
		bBackToMenu = new MenuButton(mainWindow,"bBackToMenu.png",MainWindow.HEIGHT-40);
		bBackToMenu.setX(20);
		bBackToMenu.setBounds(bBackToMenu.getX(),bBackToMenu.getY(),bBackToMenu.getWidth(),bBackToMenu.getHeight());
		bBackToMenu.addActionListener(this);
		
		tfLeft = createMyTextField(ActiveKeys.LEFT);
		tfRight = createMyTextField(ActiveKeys.RIGHT);
		tfUp = createMyTextField(ActiveKeys.UP);
		tfDown = createMyTextField(ActiveKeys.DOWN);
		tfProtect = createMyTextField(ActiveKeys.PROTECT);
		tfShoot = createMyTextField(ActiveKeys.SHOOT);
		
		bSound = new JButton();
		bSound.setBounds(370,320,100,20);
		bSound.addActionListener(this);
		bSound.setContentAreaFilled(false);
		bSound.setBorder(null);
		soundButton();
		
		
		bLeft = createMyButton();
		bRight = createMyButton();
		bUp = createMyButton();
		bDown = createMyButton();
		bProtect = createMyButton();
		bShoot = createMyButton();
		
		tfLeft.setBounds(370,345,100,20);
		tfRight.setBounds(370,370,100,20);
		tfUp.setBounds(370,395,100,20);
		tfDown.setBounds(370,420,100,20);
		tfProtect.setBounds(370,445,100,20);
		tfShoot.setBounds(370,470,100,20);
		
		
		lSound = createLabel("bSound.png",250,320);
		lLeft = createLabel("bLeft.png",250,345);
		lRight = createLabel("bRight.png",250,370);
		lUp = createLabel("bForward.png",250,395);
		lDown = createLabel("bSlowDown.png",250,420);
		lProtect = createLabel("bShield.png",250,445);
		lShoot = createLabel("bShot.png",250,470);
		
		add(lSound);
		add(lLeft);
		add(lRight);
		add(lUp);
		add(lDown);
		add(lProtect);
		add(lShoot);
		
		add(bSound);
		add(tfLeft);
		add(bLeft);
		add(tfRight);
		add(bRight);
		add(tfUp);
		add(bUp);
		add(tfDown);
		add(bDown);
		add(tfProtect);
		add(bProtect);
		add(tfShoot);
		add(bShoot);
		add(bBackToMenu);
		add(bgLabel);
	}
	private JLabel createLabel(String file, int x, int y){
		Sprite img = new Sprite(file);
		JLabel b = new JLabel(img.getImageIcon());
		b.setBounds(x,y,img.getWidth(),img.getHeight());
		return b;
	}
	private void soundButton(){
		Sprite img;
		if(SoundManager.sound)
			img = new Sprite("bYes.png");
		else
			img = new Sprite("bNo.png");

		bSound.setIcon(img.getImageIcon());
	}
	private JTextField createMyTextField(int keyCode){
		JTextField tf = new JTextField(ActiveKeys.getKeyChar(keyCode));
		tf.setEditable(false);
		tf.setHorizontalAlignment(JTextField.CENTER);
		tf.setBorder(borderBlack);
		return tf;
	}
	private MenuButton createMyButton(){
		MenuButton b = new MenuButton(mainWindow,"bChange.png",menuY);
		b.setX(480);
		b.setBounds(b.getX(),b.getY(),b.getWidth(),b.getHeight());
		b.addActionListener(this);
		menuY+=25;
		return b;
	}

	
	protected void setKey(String type,String key){
		if(type.equals("LEFT")){
			mainWindow.config.setLeft(ActiveKeys.LEFT);
			editKey(tfLeft,key,borderBlack);
		}
		else if(type.equals("RIGHT")){
			mainWindow.config.setRight(ActiveKeys.RIGHT);
			editKey(tfRight,key,borderBlack);
		}
		else if(type.equals("UP")){
			mainWindow.config.setUp(ActiveKeys.UP);
			editKey(tfUp,key,borderBlack);
		}
		else if(type.equals("DOWN")){
			mainWindow.config.setDown(ActiveKeys.DOWN);
			editKey(tfDown,key,borderBlack);
		}
		else if(type.equals("PROTECT")){
			mainWindow.config.setProtect(ActiveKeys.PROTECT);
			editKey(tfProtect,key,borderBlack);
		}
		else if(type.equals("SHOOT")){
			mainWindow.config.setShoot(ActiveKeys.SHOOT);
			editKey(tfShoot,key,borderBlack);
		}
	}
	private void editKey(JTextField tf,String key,Border border){
		if(key!=null)
			tf.setText(key);
		tf.setBorder(border);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bBackToMenu)
			mainWindow.viewManager.setView(ViewManager.MENU);
		else if(obj==bLeft){
			editKey(tfLeft,null,borderGreen);
			mainWindow.activeKeys.setKey("LEFT",this);
		}
		else if(obj==bRight){
			editKey(tfRight,null,borderGreen);
			mainWindow.activeKeys.setKey("RIGHT",this);
		}
		else if(obj==bUp){
			editKey(tfUp,null,borderGreen);
			mainWindow.activeKeys.setKey("UP",this);
		}
		else if(obj==bDown){
			editKey(tfDown,null,borderGreen);
			mainWindow.activeKeys.setKey("DOWN",this);
		}
		else if(obj==bProtect){
			editKey(tfProtect,null,borderGreen);
			mainWindow.activeKeys.setKey("PROTECT",this);
		}
		else if(obj==bShoot){
			editKey(tfShoot,null,borderGreen);
			mainWindow.activeKeys.setKey("SHOOT",this);
		}
		else if(obj==bSound){
			SoundManager.setSound(!SoundManager.sound);
			mainWindow.config.setSound(SoundManager.sound);
			soundButton();
		}
		mainWindow.requestFocusInWindow();
	}
}
