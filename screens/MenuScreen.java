package com.thundershock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thundershock.game.RocketRalph;
import com.thundershock.menuhelpers.MenuInputHandler;
import com.thundershock.menuhelpers.MenuRenderer;

public class MenuScreen implements Screen {

	MenuRenderer menuRender;
	RocketRalph game;
	private float runTime = 0;
	
	public MenuScreen(RocketRalph game){
		menuRender = new MenuRenderer();
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		// set the input processor
		Gdx.input.setInputProcessor(new MenuInputHandler(game, screenWidth/136, screenHeight/204));
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		menuRender.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
