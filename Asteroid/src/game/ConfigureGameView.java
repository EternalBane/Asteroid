package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ConfigureGameView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MenuButton bStart,bBackToMenu;
	private MainWindow mainWindow;
	private Sprite bg;
	private JLabel bgLabel,timeLabel,asteroidsLabel;
	private JComboBox<String> listTime,listAsteroids;
	
	ConfigureGameView(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		setLayout(null);
		
		createGUI();
	}
	private void createGUI(){
		Font font = new Font("Verdana",Font.PLAIN,18);
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);	
		
		timeLabel = new JLabel("Czas trwania poziomu:");
		timeLabel.setFont(font);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setBounds(220,350,300,20);
		
		asteroidsLabel = new JLabel("Iloœæ asteroid na planszy:");
		asteroidsLabel.setForeground(Color.WHITE);
		asteroidsLabel.setFont(font);
		asteroidsLabel.setBounds(220,380,300,20);
		
		Border borderBlack = BorderFactory.createMatteBorder(2,2,2,2,new Color(178,214,57,100));
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBackground(new Color(0,0,0,100));
		container.setBounds(200, 300, 400, 190);
		container.setBorder(borderBlack);
		
		bBackToMenu = new MenuButton(mainWindow,"bBackToMenu.png",MainWindow.HEIGHT-40);
		bBackToMenu.setX(20);
		bBackToMenu.setBounds(bBackToMenu.getX(),bBackToMenu.getY(),bBackToMenu.getWidth(),bBackToMenu.getHeight());
		bBackToMenu.addActionListener(this);
		
		bStart = createButton("bNewGame.png");
		
		
		listTime = new JComboBox<String>();
		listTime.setBounds(480,350,100,20);
		listAsteroids = new JComboBox<String>();
		listAsteroids.setBounds(480,380,100,20);
		((JLabel)listAsteroids.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel)listTime.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		listTime.addItem("1 minuta");
		listTime.addItem("2 minuty");
		listTime.addItem("3 minuty");
		listTime.addItem("4 minuty");
		listTime.addItem("5 minut");
		listTime.addItem("6 minut");
		listTime.addItem("7 minut");
		listTime.addItem("8 minut");
		listTime.addItem("9 minut");
		listTime.addItem("10 minut");
		
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
		add(container);
		add(bgLabel);
	}
	private MenuButton createButton(String file){
		MenuButton b = new MenuButton(mainWindow,file,430);
		b.addActionListener(this);
		b.setBounds(b.getX(),b.getY(),b.getWidth(),b.getHeight());
		return b;
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bBackToMenu)
			mainWindow.viewManager.setView(ViewManager.MENU);
		else if(obj==bStart){
			mainWindow.viewManager.setAsteroids(listAsteroids.getSelectedIndex()+1);
			mainWindow.viewManager.setTime(listTime.getSelectedIndex()+1);
			mainWindow.viewManager.setView(ViewManager.GAME);
		}
		
	}
}
