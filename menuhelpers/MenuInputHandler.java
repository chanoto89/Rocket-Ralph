package com.thundershock.menuhelpers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.thundershock.game.RocketRalph;
import com.thundershock.screens.GameScreen;
import com.thundershock.screens.OptionScreen;

public class MenuInputHandler implements InputProcessor {

	RocketRalph game;
	Rectangle start, menu;
	float scaleFactorX;
	float scaleFactorY;
	
	
	public MenuInputHandler(RocketRalph game, float scaleFactorX, float scaleFactorY){
		this.game = game;
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		start = new Rectangle(53, 136, 30, 15);
		menu = new Rectangle(53, 166, 30, 15);
		
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if(start.contains(screenX, screenY)){
			game.setScreen(new GameScreen(game));
		}
		
		if(menu.contains(screenX, screenY)){
			game.setScreen(new OptionScreen(game));
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
