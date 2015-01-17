package com.thundershock.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.thundershock.game.RocketRalph;
import com.thundershock.gamehelpers.InputHandler;
import com.thundershock.gameworld.GameRenderer;
import com.thundershock.gameworld.GameWorld;

public class GameScreen implements Screen {
	
	//create new game world class
	GameWorld world;
	
	//create new game renderer class
	GameRenderer renderer;
	
	//for runtime
	private float runTime = 0;
	
	public GameScreen(RocketRalph game){
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		int gameWidth = 136;
		
		Gdx.app.log("GameScreen", "Attached");
		world = new GameWorld(gameWidth/2); //initialize
		renderer = new GameRenderer(world); //initialize
		
		//set the renderer to the game world
		world.setRenderer(renderer);
		
		// set the input processor
		Gdx.input.setInputProcessor(new InputHandler(world, game, screenWidth/136, screenHeight/204));
	}

	@Override
	public void render(float delta) {
		//update the runtime
		runTime += delta;
		
		world.update(delta); //Update GameWorld
		renderer.render(runTime, delta); //Render GameRenderer
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
