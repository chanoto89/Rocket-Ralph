package com.thundershock.gameworld;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.thundershock.gamehelpers.AssetLoader;
import com.thundershock.gamehelpers.Bird;
import com.thundershock.gamehelpers.Cloud;
import com.thundershock.gamehelpers.ScrollHandler;
import com.thundershock.gameobjects.Rocket;
import com.thundershock.tweenaccessors.Value;
import com.thundershock.tweenaccessors.ValueAccessor;

public class GameRenderer {

	//Create GameWorld variable
	private GameWorld myWorld;
	
	//Create instance variable for game objects
	private Rocket rocketObject;
	private ScrollHandler scroller;
	private Cloud cloud1, cloud2, cloud3;
	private Bird bird1, bird2, bird3;
	
	//Instance variable for Orthographic camera (make 3D images 2D)
	private OrthographicCamera cam;
	
	//Retrieve the textures
	private TextureRegion bg, rocketBoost, cloud, blackCloud, birdRight, birdLeft, 
	highScore, gameOver, displayEasy, displayHard, menuButton, retryButton, goldStar, blackStar, health, leaderBoards;
	
	private Animation bird1Animation, bird2Animation, johnnyJetpack;
	
	//Render a shape
	private ShapeRenderer shapeRenderer;
	
	
	//Sprite Batcher
	private SpriteBatch batcher;
	
	//Tween Stuff
	private TweenManager manager;
	private Value alpha = new Value();
	private Color transitionColor;
	
	public GameRenderer(GameWorld world){
		myWorld = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, 204);
		
		batcher = new SpriteBatch();
		//Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		//Call helper methods to initialize instance variables
		initGameObjects();
		initAssets();
		
		transitionColor = new Color();
		//set initial transition
		prepareTransition(255, 255, 255, 1f);
	}
	
	public void render(float runTime, float delta){
		
		/*
		 * 1. Draw a black background to prevent flickering
		 */
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Begin SpriteBatch
		batcher.begin();
		
//		//disable transparency
		batcher.disableBlending();
		//batcher.draw(bg, 0, 0, 136, 204);
		
		drawBackground();
		
		batcher.enableBlending();
		
		drawRocket(runTime);
		
		drawClouds();
		drawBirds(runTime);
		
			
		//draw the health
		/*AssetLoader.healthShadow.draw(batcher, "HEALTH - " + myBalloon.getHealth(), 5, 14);
		AssetLoader.healthFont.draw(batcher, "HEALTH - " + myBalloon.getHealth(), 4, 13);*/
		
		
		drawHealth(rocketObject.getHealth());
		
		drawScore();
		
		
		
		//end SpriteBatch
		batcher.end();
		
		//draw tween transition
		drawTransition(delta);
		
		//example shape rending for balloon collision
/*		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.circle(rocketObject.getBoundingCircle().x, rocketObject.getBoundingCircle().y, rocketObject.getBoundingCircle().radius);
		
		shapeRenderer.rect(rocketObject.getBoundingRectangle().x, rocketObject.getBoundingRectangle().y, rocketObject.getBoundingRectangle().width, rocketObject.getBoundingRectangle().height);
		
		shapeRenderer.rect(rocketObject.getBoundingJetpack().x, rocketObject.getBoundingJetpack().y, rocketObject.getBoundingJetpack().width, rocketObject.getBoundingJetpack().height);
		
		
		shapeRenderer.rect(cloud1.getCloud1().x, cloud1.getCloud1().y, cloud1.getCloud1().width, cloud1.getCloud1().height);
		shapeRenderer.rect(cloud1.getCloud2().x, cloud1.getCloud2().y, cloud1.getCloud2().width, cloud1.getCloud2().height);
		
		shapeRenderer.rect(cloud2.getCloud1().x, cloud2.getCloud1().y, cloud2.getCloud1().width, cloud2.getCloud1().height);
		shapeRenderer.rect(cloud2.getCloud2().x, cloud2.getCloud2().y, cloud2.getCloud2().width, cloud2.getCloud2().height);
		
		shapeRenderer.rect(cloud3.getCloud1().x, cloud3.getCloud1().y, cloud3.getCloud1().width, cloud3.getCloud1().height);
		shapeRenderer.rect(cloud3.getCloud2().x, cloud3.getCloud2().y, cloud3.getCloud2().width, cloud3.getCloud2().height);
		
		shapeRenderer.circle(bird1.getBoundingBird().x, bird1.getBoundingBird().y, bird1.getBoundingBird().radius);
		shapeRenderer.circle(bird2.getBoundingBird().x, bird2.getBoundingBird().y, bird2.getBoundingBird().radius);
		shapeRenderer.circle(bird3.getBoundingBird().x, bird3.getBoundingBird().y, bird3.getBoundingBird().radius);
		
		shapeRenderer.end();
		*/
		
		
	}
	
	private void drawBackground(){
		batcher.draw(bg, 0, 0, 136, 204);
	}
	
	private void drawRocket(float runTime){
		if(rocketObject.getIsBoost()){
			batcher.draw(rocketBoost, rocketObject.getX(), rocketObject.getY(), rocketObject.getWitdh(), rocketObject.getHeight());
		} else {
			batcher.draw(johnnyJetpack.getKeyFrame(runTime), rocketObject.getX(), rocketObject.getY(), rocketObject.getWitdh(), rocketObject.getHeight());
		}
	}
	
	private void drawClouds(){
		//Determine if the clouds should be black or white
		if(cloud1.getIsBlackCloud1()){
			batcher.draw(blackCloud, cloud1.getX(), cloud1.getY(), cloud1.getWidth(), cloud1.getHeight());
		} else batcher.draw(cloud, cloud1.getX(), cloud1.getY(), cloud1.getWidth(), cloud1.getHeight());
		
		if(cloud1.getIsBlackCloud2()){
			if(AssetLoader.getDifficulty() == true){
				batcher.draw(blackCloud, cloud1.getWidth() + cloud1.getCloudGap(), cloud1.getY(), 136 - (cloud1.getWidth() + 23), cloud1.getHeight());
			} else batcher.draw(blackCloud, cloud1.getWidth() + cloud1.getCloudGap(), cloud1.getY(), 136 - (cloud1.getWidth() + 20), cloud1.getHeight());
		} else {
			if (AssetLoader.getDifficulty() == true){
				batcher.draw(cloud, cloud1.getWidth() + cloud1.getCloudGap(), cloud1.getY(), 136 - (cloud1.getWidth() + 23), cloud1.getHeight());
			} else batcher.draw(cloud, cloud1.getWidth() + cloud1.getCloudGap(), cloud1.getY(), 136 - (cloud1.getWidth() + 20), cloud1.getHeight());
		}
		
		

		
		if(cloud2.getIsBlackCloud1()){
			batcher.draw(blackCloud, cloud2.getX(), cloud2.getY(), cloud2.getWidth(), cloud2.getHeight());
		} else batcher.draw(cloud, cloud2.getX(), cloud2.getY(), cloud2.getWidth(), cloud2.getHeight());
		
		if(cloud2.getIsBlackCloud2()){
			if(AssetLoader.getDifficulty() == true){
				batcher.draw(blackCloud, cloud2.getWidth() + cloud2.getCloudGap(), cloud2.getY(), 136 - (cloud2.getWidth() + 23), cloud2.getHeight());
			} else batcher.draw(blackCloud, cloud2.getWidth() + cloud2.getCloudGap(), cloud2.getY(), 136 - (cloud2.getWidth() + 20), cloud2.getHeight());
		} else {
			if (AssetLoader.getDifficulty() == true){
				batcher.draw(cloud, cloud2.getWidth() + cloud2.getCloudGap(), cloud2.getY(), 136 - (cloud2.getWidth() + 23), cloud2.getHeight());
			} else batcher.draw(cloud, cloud2.getWidth() + cloud2.getCloudGap(), cloud2.getY(), 136 - (cloud2.getWidth() + 20), cloud2.getHeight());
		}

		
		
		
		if(cloud3.getIsBlackCloud1()){
			batcher.draw(blackCloud, cloud3.getX(), cloud3.getY(), cloud3.getWidth(), cloud3.getHeight());
			
		} else batcher.draw(cloud, cloud3.getX(), cloud3.getY(), cloud3.getWidth(), cloud3.getHeight());
		
		
		if(cloud3.getIsBlackCloud2()){
			if(AssetLoader.getDifficulty() == true){
				batcher.draw(blackCloud, cloud3.getWidth() + cloud3.getCloudGap(), cloud3.getY(), 136 - (cloud3.getWidth() + 23), cloud3.getHeight());
			} else batcher.draw(blackCloud, cloud3.getWidth() + cloud3.getCloudGap(), cloud3.getY(), 136 - (cloud3.getWidth() + 20), cloud3.getHeight());
		} else {
			if (AssetLoader.getDifficulty() == true){
				batcher.draw(cloud, cloud3.getWidth() + cloud3.getCloudGap(), cloud3.getY(), 136 - (cloud3.getWidth() + 23), cloud3.getHeight());
			} else batcher.draw(cloud, cloud3.getWidth() + cloud3.getCloudGap(), cloud3.getY(), 136 - (cloud3.getWidth() + 20), cloud3.getHeight());
		}
	}
	
	
	private void drawBirds(float runTime){
		if(bird1.getIsDisplayed()){
			if(rocketObject.getIsAlive() == true){
				batcher.draw(bird2Animation.getKeyFrame(runTime), bird1.getX(), bird1.getY(), bird1.getWidth(), bird1.getHeight());
			}else batcher.draw(birdRight, bird1.getX(), bird1.getY(), bird1.getWidth(), bird1.getHeight());	
		}
		
		if(bird2.getIsDisplayed()){
			if(rocketObject.getIsAlive() == true){
				batcher.draw(bird1Animation.getKeyFrame(runTime), bird2.getX(), bird2.getY(), bird2.getWidth(), bird2.getHeight());
			} else batcher.draw(birdLeft, bird2.getX(), bird2.getY(), bird2.getWidth(), bird2.getHeight());
		}
		
		if(bird3.getIsDisplayed()){
			if(rocketObject.getIsAlive()){
				batcher.draw(bird2Animation.getKeyFrame(runTime), bird3.getX(), bird3.getY(), bird3.getWidth(), bird3.getHeight());
			}else batcher.draw(birdRight, bird3.getX(), bird3.getY(), bird3.getWidth(), bird3.getHeight());
			
		}
	}
	
	
	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 210);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);

		}
	}
	
	private void drawScore(){
		
		//Convert the score to string
		String myScore = myWorld.getScore() + "";
		
		//Draw Shadow for score
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136/ 2) - (3* myScore.length()), 10);
		//Draw Text
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2) - (3 * myScore.length() - 1), 9);
		
		if(myWorld.isReady()){
			//Draw Shadow
			AssetLoader.commandShadow.draw(batcher, "Press to Start", (136/2) - 50, 76);
			AssetLoader.commandFont.draw(batcher, "Press to Start", (136/2) - 51, 75); 
		} else{
			if(myWorld.isGameOver() || myWorld.isHighScore()){
                
				if(myWorld.isGameOver()){
					batcher.draw(gameOver, 34, 35, 68, 20);
					drawDifficulty();
					drawEndGameButtons();
				} else {
					if(myWorld.isHighScore()){
						
						batcher.draw(highScore, 34, 35, 68, 20);
						drawDifficulty();
						drawEndGameButtons();
					}
				}
				
				AssetLoader.healthFont.draw(batcher, "" + myWorld.getScore(), 84, 74);
				AssetLoader.healthFont.draw(batcher, "" + AssetLoader.getHighScore(), 84,  96);
				
				
				drawRating(myWorld.getScore());
			}
		}
	}
	
	private void drawDifficulty(){
		if(AssetLoader.getDifficulty() == true){
			batcher.draw(displayEasy, 20, 60, 96, 50);
		} else batcher.draw(displayHard, 20, 60, 96, 50);
	}
	
	private void drawEndGameButtons(){
		batcher.draw(menuButton, 34, 115, 28, 20);
		batcher.draw(retryButton, 76, 115, 28, 20);
		batcher.draw(leaderBoards, 20, 140, 96, 20);
	}
	
	private void drawHealth(int healthCount){
		if (healthCount > 0){
			batcher.draw(health, 4, 8.5f, 6, 15);
		}
		
		if (healthCount > 1){
			batcher.draw(health, 11, 8.5f, 6, 15);
		}
		
		if (healthCount > 2){
			batcher.draw(health, 18, 8.5f, 6, 15);
		}
		
		if (healthCount > 3){
			batcher.draw(health, 25, 8.5f, 6, 15);
		}
		
		if (healthCount > 4){
			batcher.draw(health, 32, 8.5f, 6, 15);
		}
		
	}
	
	private void drawRating(int score){
			
		if(AssetLoader.getDifficulty() == true){
			
			if(score > 300){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(goldStar, 50, 74, 8, 10);
				batcher.draw(goldStar, 58, 74, 8, 10);
			}
			
			else if(score > 175){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(goldStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score > 100){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score > 50){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score > 20){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(blackStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score >= 0){
				batcher.draw(blackStar, 24, 74, 8, 10);
				batcher.draw(blackStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			
		} else {
			
			if(score > 100){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(goldStar, 50, 74, 8, 10);
				batcher.draw(goldStar, 58, 74, 8, 10);
			}
			
			else if(score > 75){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(goldStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score > 50){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(goldStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score > 25){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(goldStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}

			else if(score > 10){
				batcher.draw(goldStar, 24, 74, 8, 10);
				batcher.draw(blackStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			else if(score >= 0){
				batcher.draw(blackStar, 24, 74, 8, 10);
				batcher.draw(blackStar, 32, 74, 8, 10);
				batcher.draw(blackStar, 41, 74, 8, 10);
				batcher.draw(blackStar, 50, 74, 8, 10);
				batcher.draw(blackStar, 58, 74, 8, 10);
			}
			
			
		}
	}
	
	
	private void initGameObjects(){
		rocketObject = myWorld.getRocket();
		scroller = myWorld.getScroller();
		cloud1 = scroller.getCloud1();
		cloud2 = scroller.getCloud2();
		cloud3 = scroller.getCloud3();
		bird1 = scroller.getBird1();
		bird2 = scroller.getBird2();
		bird3 = scroller.getBird3();
	}
	
	private void initAssets(){
		bg = AssetLoader.bg;
		cloud = AssetLoader.cloud;
		blackCloud = AssetLoader.blackCloud;
		birdLeft = AssetLoader.bird1;
		bird1Animation = AssetLoader.bird1Animation;
		birdRight = AssetLoader.bird2;
		bird2Animation = AssetLoader.bird2Animation;
		rocketBoost = AssetLoader.johnnyBoost;
		highScore = AssetLoader.highScore;
		gameOver = AssetLoader.gameOver;
		displayEasy = AssetLoader.displayEasy;
		displayHard = AssetLoader.displayHard;
		menuButton = AssetLoader.menuButton;
		retryButton = AssetLoader.retryButton;
		goldStar = AssetLoader.goldStar;
		blackStar = AssetLoader.blackStar;
		health = AssetLoader.health;
		johnnyJetpack = AssetLoader.johnnyJetpack;
		leaderBoards = AssetLoader.leaderBoards;
	}
}
