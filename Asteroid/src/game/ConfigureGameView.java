package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConfigureGameView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton bBackToMenu;
	private MenuButton bStart;
	private MainWindow frame;
	private Sprite bg;
	private JLabel bgLabel,timeLabel,asteroidsLabel;
	private JComboBox<String> listTime,listAsteroids;
	
	ConfigureGameView(MainWindow frame){
		this.frame = frame;
		setLayout(null);
		
		createGUI();
	}
	private void createGUI(){
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);	
		
		timeLabel = new JLabel("Czas:");
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setBounds(300,300,100,20);
		
		asteroidsLabel = new JLabel("Iloœæ asteroid:");
		asteroidsLabel.setForeground(Color.WHITE);
		asteroidsLabel.setBounds(300,330,100,20);
		
		bBackToMenu = new JButton("Wróæ do menu");
		bBackToMenu.setBounds(0,MainWindow.HEIGHT-40,200,20);
		bBackToMenu.addActionListener(this);
		
		bStart = createButton("bNewGame.png");
		
		
		listTime = new JComboBox<String>();
		listTime.setBounds(400,300,100,20);
		listAsteroids = new JComboBox<String>();
		listAsteroids.setBounds(400,330,100,20);
		
		listTime.addItem("1 min");
		listTime.addItem("2 min");
		listTime.addItem("3 min");
		listTime.addItem("4 min");
		listTime.addItem("5 min");
		listTime.addItem("6 min");
		listTime.addItem("7 min");
		listTime.addItem("8 min");
		listTime.addItem("9 min");
		listTime.addItem("10 min");
		
		listAsteroids.addItem("1");
		listAsteroids.addItem("2");
		listAsteroids.addItem("3");
		listAsteroids.addItem("4");
		listAsteroids.addItem("5");
		listAsteroids.addItem("6");
		listAsteroids.addItem("7");
		listAsteroids.addItem("8");
		listAsteroids.addItem("9");
		listAsteroids.addItem("10");
		
		add(listTime);
		add(listAsteroids);
		add(timeLabel);
		add(asteroidsLabel);
		add(bBackToMenu);
		add(bStart);
		add(bgLabel);
	}
	private MenuButton createButton(String file){
		MenuButton b = new MenuButton(frame,file,400);
		b.addActionListener(this);
		b.setBounds(b.getX(),b.getY(),b.getWidth(),b.getHeight());
		return b;
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bBackToMenu)
			frame.viewManager.setView(ViewManager.MENU);
		else if(obj==bStart){
			frame.viewManager.setAsteroids(listAsteroids.getSelectedIndex()+1);
			frame.viewManager.setTime(listTime.getSelectedIndex()+1);
			frame.viewManager.setView(ViewManager.GAME);
		}
		
	}
}
