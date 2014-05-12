package game;

import javax.swing.JPanel;

public class ViewManager {
	MainWindow mainWindow;
	GameView gameView;
	public final static int MENU = 0;
	public final static int PREPARE_GAME = 1;
	public final static int GAME = 2;
	public final static int RESUME = 3;
	public final static int RANK = 4;
	public final static int OPTIONS = 5;
	public final static int GAME_OVER = 6;
	protected JPanel view;
	private boolean resume;
	private int time;
	private int asteroids;
	
	ViewManager(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		resume = false;
	}
	public void setTime(int time){
		this.time = time;
	}
	public void setAsteroids(int asteroids){
		this.asteroids = asteroids;
	}
	public void setResume(boolean b){
		resume = b;
	}
	public void setView(int view){
		if(view==MENU){
			MainMenuView menu = new MainMenuView(mainWindow,resume);
			this.view = menu;
		}
		else if(view==RESUME){
			this.view = gameView;
			gameView.resume();
		}
		else if(view==GAME){
			gameView = new GameView(mainWindow);
			if(time>0){
				gameView.act.setTime(time);
				time = 0; 
			}
			if(asteroids>0){
				gameView.act.setAsteroids(asteroids);
				asteroids = 0; 
			}
			this.view = gameView;
			gameView.start();
		}
		else if(view==GAME_OVER){
			GameOverView gameOverView = new GameOverView(mainWindow,gameView.score.getScore());
			this.view = gameOverView;
		}
		else if(view==RANK){
			RankingView rankingView = new RankingView(mainWindow);
			this.view = rankingView;
		}
		else if(view==OPTIONS){
			OptionsView optionsView = new OptionsView(mainWindow);
			this.view = optionsView;
		}
		else if(view==PREPARE_GAME){
			ConfigureGameView configureGameView = new ConfigureGameView(mainWindow);
			this.view = configureGameView;
		}
		
		mainWindow.setView(this.view);
	}
}
