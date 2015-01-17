package com.thundershock.menuhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thundershock.gamehelpers.AssetLoader;

public class OptionRenderer {

	//Instance variable for Orthographic camera (make 3D images 2D)
		private OrthographicCamera cam;
			
		//Retrieve the textures
		private TextureRegion optionBG, easyTrue, easyFalse, hardTrue, hardFalse, soundOnTrue, soundOnFalse, soundOffTrue, soundOffFalse, back;
		
		//Render a shape
		private ShapeRenderer shapeRenderer;
			
			
		//Sprite Batcher
		private SpriteBatch batcher;
		
		Animation menuAnimation;
		
		
		public OptionRenderer(){
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
			
//			//disable transparency
			batcher.disableBlending();
			
			batcher.draw(optionBG, 0, 0, 136, 204);
			
			batcher.enableBlending();
			drawOptionItems();
			drawMenuAnimation(runTime);
			
			batcher.end();
			
//			shapeRenderer.begin(ShapeType.Line);
//			shapeRenderer.setColor(Color.BLUE);
//			shapeRenderer.rect(53, 136, 30, 15);
//			shapeRenderer.end();
		}
		
		private void drawOptionItems(){
			if(AssetLoader.getDifficulty() == true){
				batcher.draw(easyTrue, 54, 121, 25, 15);
				batcher.draw(hardFalse, 89, 121, 25, 15);
			} else {
				batcher.draw(easyFalse, 54, 121, 25, 15);
				batcher.draw(hardTrue, 89, 121, 25, 15);
			}
			
			if(AssetLoader.getSound() == true){
				batcher.draw(soundOnTrue, 54, 146, 25, 15);
				batcher.draw(soundOffFalse, 89, 146, 25, 15);
			} else{
				batcher.draw(soundOnFalse, 54, 146, 25, 15);
				batcher.draw(soundOffTrue, 89, 146, 25, 15);
			}
			
			batcher.draw(back, 89, 167, 25, 15);
			
		}
		
		
		private void drawMenuAnimation(float runTime){
			batcher.draw(menuAnimation.getKeyFrame(runTime), 22, 36, 90, 80);
			
		}
		
		
		private void initAssets(){
			optionBG = AssetLoader.options;
			easyTrue = AssetLoader.easyTrue;
			easyFalse = AssetLoader.easyFalse;
			hardTrue = AssetLoader.hardTrue;
			hardFalse = AssetLoader.hardFalse;
			soundOnTrue = AssetLoader.soundOnTrue;
			soundOnFalse = AssetLoader.soundOnFalse;
			soundOffTrue = AssetLoader.soundOffTrue;
			soundOffFalse = AssetLoader.soundOffFalse;
			back = AssetLoader.back;
			menuAnimation = AssetLoader.menuAnimation;
		}
}
