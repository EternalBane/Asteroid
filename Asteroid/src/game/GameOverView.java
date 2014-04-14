package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton bBackToMenu;
	private MainWindow mainWindow;
	private String strScore;
	@SuppressWarnings("unused")
	private int intScore;
	private Sprite bg;
	private JLabel bgLabel;

	GameOverView(MainWindow mainWindow,String score){
		this.mainWindow = mainWindow;
		setLayout(null);
		
		strScore = score;
		try{ intScore = Integer.parseInt(strScore);
		} catch(Exception e){}
		
		createGUI();
	}
	private void createGUI(){
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);
		
		bBackToMenu = new JButton("Wróæ do menu");
		bBackToMenu.setBounds(0,MainWindow.HEIGHT-40,200,20);
		bBackToMenu.addActionListener(this);
		add(bBackToMenu);
		
		add(bgLabel);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bBackToMenu)
			mainWindow.viewManager.setView(ViewManager.MENU);
	}
}
