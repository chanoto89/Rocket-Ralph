package com.thundershock.gameworld;

import com.swarmconnect.SwarmLeaderboard;
import com.thundershock.gamehelpers.AssetLoader;
import com.thundershock.gamehelpers.ScrollHandler;
import com.thundershock.gameobjects.Rocket;

public class GameWorld {
	
	//enums to handle the different game states
	public enum GameState{
		READY, RUNNING, GAMEOVER, HIGHSCORE
	}
	
	private GameState currentState; //gets the current game state of the game
	
	private Rocket rocket;
	private ScrollHandler scroller; //handles the scrolling elements like clouds
	private int score = 0;
	
	private int midPointX; //needed for restart
	
	private GameRenderer renderer;
	
	public GameWorld(int midpointX){
		//initialize our variables
		rocket = new Rocket(midpointX - 5, 150, 22, 25 );
		scroller = new ScrollHandler(this);
		currentState = GameState.READY;
		
		midPointX = midpointX;
	}
	
	public void update(float delta){
		
		//switch statement to determine current state of the game
		switch(currentState){
		case READY:
			updateReady(delta);
			break;
			
		case RUNNING:
			updateRunning(delta);
			break;
			
		default:
			
			break;
		}
	}
	
	public void updateReady(float delta){
		if(delta > .15f){
			delta = .15f;
		}
		
		rocket.updateReady(delta);
		scroller.updateReady(delta);
	}
	
	public void updateRunning(float delta){
		//used to make sure the time between runs doesn't cause lag
		if(delta > .15f){
			delta = .15f;
		}
		
		rocket.update(delta);
		scroller.update(delta);
		
		if(rocket.getIsAlive() && scroller.collides(rocket)){
			
			
			if(scroller.getIsBirdHit()){
				if(AssetLoader.getSound() == true){
				AssetLoader.birdDead.play();
				}
			} 
			
			if(scroller.getIsBlackHit() || scroller.getIsBirdHit()){
				rocket.subtractHealth(5);
			} else rocket.subtractHealth(1);
			
			
			
			if(rocket.getHealth() < 1)
			{
				//Clean up on game over
				scroller.stop();
				
				if(AssetLoader.getSound() == true){
				AssetLoader.dead.play();
				}
				
				AssetLoader.boost.stop();
				rocket.die();
				currentState = GameState.GAMEOVER;
				
				if(this.score > AssetLoader.getHighScore()){
					AssetLoader.setHighScore(score);
					currentState = GameState.HIGHSCORE;
					
					if(AssetLoader.getDifficulty() == true){
						//set high score
						SwarmLeaderboard.submitScore(18165, score);
					} else if (AssetLoader.getDifficulty() == false){
						//set high score
						SwarmLeaderboard.submitScore(18167, score);
					}
					
				}
			} else{
				if(AssetLoader.getSound() == true){
					AssetLoader.hit.play(.8f);
				}
			}
			
			renderer.prepareTransition(255, 255, 255, .5f);
		}
	}
	
	public void restart(){
		score = 0;
		rocket.onRestart(this.midPointX - 12);
		scroller.onRestart();
		ready();
	}
	
	public void ready(){
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void addScore(int increment){
		score += increment;
	}
	
	public Rocket getRocket(){
		return rocket;
	}

	public ScrollHandler getScroller() {
        return scroller;
    }
	
	public int getScore(){
		return score;
	}
	public boolean isReady() {
        return currentState == GameState.READY;
    }
	
	public boolean isRunning(){
		return currentState == GameState.RUNNING;
	}

    public void start() {
        currentState = GameState.RUNNING;
        rocket.onRestart(this.midPointX - 12);
        scroller.onRestart();
    }
	
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public boolean isHighScore() {
    	return currentState == GameState.HIGHSCORE;
    }
	
    public void setRenderer(GameRenderer renderer){
    	this.renderer = renderer;
    }
}
