package com.thundershock.menuhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thundershock.gamehelpers.AssetLoader;

public class MenuRenderer {

	//Instance variable for Orthographic camera (make 3D images 2D)
	private OrthographicCamera cam;
		
	//Retrieve the textures
	private TextureRegion menuBG;
	
	//Render a shape
	private ShapeRenderer shapeRenderer;
		
		
	//Sprite Batcher
	private SpriteBatch batcher;
	
	//Animation
	private Animation menuAnimation;
	
	
	public MenuRenderer(){
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);
		
		batcher = new SpriteBatch();
		//Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		initAssets();
	}
	
	public void render(float delta, float runTime){
		
		/*
		 * 1. Draw a black background to prevent flickering
		 */
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Begin SpriteBatch
		batcher.begin();
		
//		//disable transparency
		batcher.disableBlending();
		
		batcher.draw(menuBG, 0, 0, 136, 204);
		
		batcher.enableBlending();
		
		drawMenuAnimation(runTime);
		
		batcher.end();
		
//		shapeRenderer.begin(ShapeType.Line);
//		shapeRenderer.setColor(Color.BLUE);
//		shapeRenderer.rect(53, 166, 30, 15);
//		shapeRenderer.end();
	}
	
	
	private void drawMenuAnimation(float runTime){
		batcher.draw(menuAnimation.getKeyFrame(runTime), 22, 36, 90, 80);
		
	}
	
	private void initAssets(){
		menuBG = AssetLoader.menu;
		menuAnimation = AssetLoader.menuAnimation;
	}
	
}
