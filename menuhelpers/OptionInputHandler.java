package com.thundershock.menuhelpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.thundershock.game.RocketRalph;
import com.thundershock.gamehelpers.AssetLoader;
import com.thundershock.screens.MenuScreen;

public class OptionInputHandler implements InputProcessor {

	RocketRalph game;
	Rectangle easy, hard, on, off, back;
	float scaleFactorX;
	float scaleFactorY;
	
	public OptionInputHandler(RocketRalph game, float scaleFactorX, float scaleFactorY){
		this.game = game;
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		easy = new Rectangle(54, 121, 25, 15);
		hard = new Rectangle(89, 121, 25, 15);
		on = new Rectangle(54, 146, 25, 15);
		off = new Rectangle(89, 146, 25, 15);
		back = new Rectangle(89, 167, 25, 15);
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
		
		if(easy.contains(screenX, screenY)){
			AssetLoader.setDifficulty(true);
		}
		
		if(hard.contains(screenX, screenY)){
			AssetLoader.setDifficulty(false);
		}
		
		if(on.contains(screenX, screenY)){
			AssetLoader.setSound(true);
		}
		
		if(off.contains(screenX, screenY)){
			AssetLoader.setSound(false);
		}
		
		if(back.contains(screenX, screenY)){
			game.setScreen(new MenuScreen(game));
		}
		
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
