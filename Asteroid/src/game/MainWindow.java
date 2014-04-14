package game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements WindowListener  {
	private static final long serialVersionUID = 1L;
	protected static final int WIDTH = 800;
	protected static final int HEIGHT = 600;
	
	protected ActiveKeys activeKeys;
	protected ViewManager viewManager;
	protected SoundManager soundManager;
	protected Config config;
	
	MainWindow(){
		setTitle("Asteroid");
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
					
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(WIDTH+6,HEIGHT+26);
		setLocation((screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/asteroid.png")));
		
		config = new Config();
		soundManager = new SoundManager();
		activeKeys = new ActiveKeys(this);
		viewManager = new ViewManager(this);
		viewManager.setView(ViewManager.MENU);
		
		addKeyListener(activeKeys);
		addWindowListener(this);
	}
	protected void setView(JPanel view){
		getContentPane().removeAll();
		view.setBounds(0,0,WIDTH,HEIGHT);
		add(view);
		requestFocusInWindow();
		repaint();
	}
	protected void close(){
		dispose();
		System.exit(0);
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {
		activeKeys.removeAll();
	}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	public static void main(String[] args){
		MainWindow mainWindow = new MainWindow();
		mainWindow.setVisible(true);
	}
}