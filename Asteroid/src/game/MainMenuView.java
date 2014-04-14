package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private MenuButton bResume,bStoryMode,bCreateGame,bRank,bOptions,bExit;
	private boolean resume;
	private Sprite bg;
	private JLabel bgLabel;
	private int menuY;
	
	MainMenuView(MainWindow mainWindow,boolean resume){
		this.mainWindow = mainWindow;
		this.resume = resume;
		setLayout(null);
		menuY = 325;
		createGUI();
	}
	private void createGUI(){
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);
		
		bResume = createButton("bResume.png");
		bStoryMode = createButton("bNewGame.png");
		bCreateGame = createButton("bCreateGame.png");
		bRank = createButton("bRank.png");
		bOptions = createButton("bSettings.png");
		bExit = createButton("bExit.png");
		
		if(resume)
		add(bResume);
		add(bStoryMode);
		add(bCreateGame);
		add(bRank);
		add(bOptions);
		add(bExit);
		add(bgLabel);
	}
	private MenuButton createButton(String file){
		MenuButton b = new MenuButton(mainWindow,file,menuY);
		b.addActionListener(this);
		b.setBounds(b.getX(),b.getY(),b.getWidth(),b.getHeight());
		menuY+=b.getHeight()+15;
		return b;
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bResume)
			mainWindow.viewManager.setView(ViewManager.RESUME);
		else if(obj==bStoryMode)
			mainWindow.viewManager.setView(ViewManager.GAME);
		else if(obj==bCreateGame)
			mainWindow.viewManager.setView(ViewManager.PREPARE_GAME);
		else if(obj==bRank)
			mainWindow.viewManager.setView(ViewManager.RANK);
		else if(obj==bOptions)
			mainWindow.viewManager.setView(ViewManager.OPTIONS);
		else if(obj==bExit)
			mainWindow.close();
		repaint();
	}	
}
