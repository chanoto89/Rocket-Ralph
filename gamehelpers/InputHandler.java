package com.thundershock.gamehelpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.swarmconnect.Swarm;
import com.thundershock.game.RocketRalph;
import com.thundershock.gameobjects.Rocket;
import com.thundershock.gameworld.GameWorld;
import com.thundershock.screens.MenuScreen;

public class InputHandler implements InputProcessor {

	//instance variable to handle faster scrolling
	private ScrollHandler scrollhandler;
	
	//instance variable to check if game is over to disable scrolling
	private Rocket rocket;
	
	//instance variable to check gamestate
	private GameWorld world;
	
	private RocketRalph game;
	
	float scaleFactorX;
	float scaleFactorY;
	
	Rectangle menu, retry, leaderBoards;
	
	public InputHandler(GameWorld world, RocketRalph game, float scaleFactorX, float scaleFactorY){
		this.world = world;
		this.scrollhandler = world.getScroller();
		this.rocket = world.getRocket();
		this.game = game;
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		menu = new Rectangle(34, 115, 28, 20);
		retry = new Rectangle(76, 115, 28, 20);
		leaderBoards = new Rectangle(20, 140, 96, 20);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if(world.isReady()){
			world.ready();
			world.start();
		}
		
		if(rocket.getIsAlive()){
			this.scrollhandler.speedUp();
			this.scrollhandler.setIsSpedUp(true);
			
			if(!world.isReady()){
				if(AssetLoader.getSound() == true){
					AssetLoader.boost.loop();
				}
			}
			rocket.setIsBoost(true);
		}
		
		if(world.isGameOver() || world.isHighScore()){
			if(menu.contains(screenX, screenY)){
				game.setScreen(new MenuScreen(game));
			}
			
			if(retry.contains(screenX, screenY)){
				world.restart();
			}
			
			if(leaderBoards.contains(screenX, screenY)){
				Swarm.showLeaderboards();
			}
			
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(rocket.getIsAlive()){
			this.scrollhandler.speedDown();
			this.scrollhandler.setIsSpedUp(false);
			this.scrollhandler.setSpedUpCount(0);
			AssetLoader.boost.stop();
			rocket.setIsBoost(false);
		}
		
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public int scaleX(int screenX){
		return (int) (screenX/scaleFactorX);
	}
	
	public int scaleY(int screenY){
		return (int) (screenY/scaleFactorY);
	}
}
