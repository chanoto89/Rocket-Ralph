package com.thundershock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thundershock.game.RocketRalph;
import com.thundershock.menuhelpers.OptionInputHandler;
import com.thundershock.menuhelpers.OptionRenderer;

public class OptionScreen implements Screen {

	OptionRenderer optionRenderer;
	private float runTime = 0;
	
	public OptionScreen(RocketRalph game){
		optionRenderer = new OptionRenderer();
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		// set the input processor
		// set the input processor
		Gdx.input.setInputProcessor(new OptionInputHandler(game, screenWidth/136, screenHeight/204));
	}
	
	
	@Override
	public void render(float delta) {
		runTime += delta;
		optionRenderer.render(delta, runTime);
		
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
