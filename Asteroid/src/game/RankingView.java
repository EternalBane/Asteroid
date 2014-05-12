package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class RankingView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MenuButton bBackToMenu;
	private MainWindow mainWindow;
	private Sprite bg;
	private JLabel bgLabel;
	private JPanel container;
	private JLabel[] labNumber,labName,labScore;
	private Font font;
	
	RankingView(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		font = new Font("Verdana",Font.PLAIN,18);
		setLayout(null);
		createGUI();
	}
	private JLabel createJLabel(String text, int x, int number, int width, int height){
		JLabel label = new JLabel(text);
		label.setBounds(x, (number+1)*25+10, width,height);
		label.setFont(font);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		return label;
	}
	private void createGUI(){
		JLabel labNumberTitle = createJLabel("#",0,-1,50,20);
		JLabel labNickTitle = createJLabel("Nick",50,-1,200,20);
		JLabel labScoreTitle = createJLabel("Punkty",250,-1,150,20);
		
		labNumberTitle.setForeground(new Color(237,235,64));
		labNickTitle.setForeground(new Color(237,235,64));
		labScoreTitle.setForeground(new Color(237,235,64));
		
		String[][] list = mainWindow.ranking.getList();
		labNumber = new JLabel[10];
		labName = new JLabel[10];
		labScore = new JLabel[10];
		
		Border borderBlack = BorderFactory.createMatteBorder(2,2,2,2,new Color(178,214,57,100));
		container = new JPanel();
		container.setLayout(null);
		container.setBackground(new Color(0,0,0,100));
		container.setBounds(200, 280, 400, 293);
		container.setBorder(borderBlack);
		
		container.add(labNumberTitle);
		container.add(labNickTitle);
		container.add(labScoreTitle);
		
		for(int i=0; i<10; i++){
			labNumber[i] = createJLabel(Integer.toString(i+1),0,i,50,20);
			labName[i] = createJLabel("",50,i,200,20);
			labScore[i] = createJLabel("",250,i,150,20);
			
			container.add(labNumber[i]);
			container.add(labName[i]);
			container.add(labScore[i]);
		}
		
		for(int i=0; i<list.length;i++){
			labName[i].setText(list[i][0]);
			labScore[i].setText(list[i][1]);
		}
		
		bg = new Sprite("bgMenu.png");
		bgLabel = new JLabel(bg.getImageIcon());
		bgLabel.setBounds(0,0,MainWindow.WIDTH,MainWindow.HEIGHT);		
		
		
		bBackToMenu = new MenuButton(mainWindow,"bBackToMenu.png",MainWindow.HEIGHT-40);
		bBackToMenu.setX(20);
		bBackToMenu.setBounds(bBackToMenu.getX(),bBackToMenu.getY(),bBackToMenu.getWidth(),bBackToMenu.getHeight());
		bBackToMenu.addActionListener(this);

		add(bBackToMenu);
		add(container);
		add(bgLabel);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj==bBackToMenu){
			mainWindow.viewManager.setView(ViewManager.MENU);
		}
		
	}
}
