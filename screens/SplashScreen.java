package com.thundershock.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thundershock.game.RocketRalph;
import com.thundershock.gamehelpers.AssetLoader;
import com.thundershock.tweenaccessors.SpriteAccessor;

public class SplashScreen implements Screen {

	private TweenManager manager;
	private SpriteBatch batcher;
	private Sprite sprite;
	private RocketRalph game;
	
	public SplashScreen(RocketRalph game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		manager.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();


        sprite.setSize(width, height);
        sprite.setPosition(0, 0);
        setupTween();
        batcher = new SpriteBatch();
		
	}
	
	
	private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				//game.setScreen(new GameScreen());
				game.setScreen(new MenuScreen(game));
				
			}
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, 1f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 1f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
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
