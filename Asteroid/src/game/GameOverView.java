package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GameOverView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MenuButton bBackToMenu,bOK;
	private MainWindow mainWindow;
	private String strScore;
	private int intScore;
	private Sprite bg;
	private JLabel bgLabel;
	private JTextField tfName;
	private GraphicalText imgScore;

	GameOverView(MainWindow mainWindow,String score){
		this.mainWindow = mainWindow;
		setLayout(null);
		intScore=0;
		strScore = score;
		imgScore = new GraphicalText(strScore);
		try{ intScore = Integer.parseInt(strScore);
		} catch(Exception e){}
		
		createGUI();
	}
	private void createGUI(){
		Border borderBlack = BorderFactory.createMatteBorder(2,2,2,2,new Color(25,22,19));
		Border containerBorder = BorderFactory.createMatteBorder(2,2,2,2,new Color(178,214,57,100));
		Font tfFont = new Font("Verdana",Font.BOLD,24);
		
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);
		
		bBackToMenu = new MenuButton(mainWindow,"bBackToMenu.png",MainWindow.HEIGHT-40);
		bBackToMenu.setX(20);
		bBackToMenu.setBounds(bBackToMenu.getX(),bBackToMenu.getY(),bBackToMenu.getWidth(),bBackToMenu.getHeight());
		bBackToMenu.addActionListener(this);
		
		JLabel labScore = new JLabel("Twój wynik to:");
		labScore.setFont(tfFont);
		labScore.setForeground(Color.WHITE);
		JLabel labScore2 = new JLabel(new ImageIcon(imgScore.getImage()));
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBackground(new Color(0,0,0,100));
		container.setBounds(200, 320, 400, 220);
		container.setBorder(containerBorder);
		
		JLabel labSetName = new JLabel("Podaj nick:");
		labSetName.setForeground(Color.WHITE);
		labSetName.setFont(tfFont);
		labSetName.setHorizontalAlignment(JTextField.CENTER);
		
		tfName = new JTextField();
		tfName.setBackground(new Color(255,255,255));
		
		bOK = new MenuButton(mainWindow,"bOK.png",500);
		bOK.setX(385);
		bOK.setBounds(bOK.getX(),bOK.getY(),bOK.getWidth(),bOK.getHeight());
		bOK.addActionListener(this);
		
		tfName.setHorizontalAlignment(JTextField.CENTER);
		tfName.setBorder(borderBlack);
		//tfName.setOpaque(false);
		tfName.setFont(tfFont);
		//tfName.setForeground(Color.WHITE);
		
		labScore.setBounds(220, 330, 200, 40);
		labScore2.setBounds(425, 330, imgScore.getWidth(), 40);
		labSetName.setBounds(250, 400, 300, 40);
		tfName.setBounds(250, 450, 300, 40);
		
		if(mainWindow.ranking.checkScore(intScore)){
			add(labScore);
			add(labScore2);
			add(labSetName);
			add(tfName);
			add(bOK);
			add(container);
		}
		
		add(bBackToMenu);
		add(bgLabel);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bBackToMenu)
			mainWindow.viewManager.setView(ViewManager.MENU);
		if(obj==bOK)
			if(!tfName.getText().equals("")){
				mainWindow.ranking.addNewScore(tfName.getText(),intScore+"");
				mainWindow.viewManager.setView(ViewManager.RANK);
			}
	}
}
