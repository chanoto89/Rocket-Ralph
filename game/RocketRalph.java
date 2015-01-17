package com.thundershock.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.thundershock.gamehelpers.AssetLoader;
import com.thundershock.screens.SplashScreen;

public class RocketRalph extends Game {

	@Override
	public void create() {
		Gdx.app.log("HotAir", "Created");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}
	
	
	@Override
	public void dispose(){
		super.dispose();
		AssetLoader.dispose();
	}

	
}
