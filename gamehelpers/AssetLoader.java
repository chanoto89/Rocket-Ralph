package com.thundershock.gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static TextureRegion bg, cloud, blackCloud, johnny, johnnyBoost, johnnySmoke;
	public static Animation johnnyJetpack;
	
	public static TextureRegion bird1, bird2, bird1Up, bird2Up;
	public static Animation bird1Animation, bird2Animation;
	
	public static Texture logoTexture;
	public static Texture menuTexture;
	public static TextureRegion logo, menu, options, easyTrue, easyFalse, hardTrue, hardFalse, soundOnTrue, soundOnFalse, soundOffTrue, soundOffFalse, back;
	
	public static Texture gameOverTexture;
	public static TextureRegion highScore, gameOver, displayEasy, displayHard, menuButton, retryButton, goldStar, blackStar, health, menuRocketFire, menuRocketNoFire, leaderBoards;
	public static Animation menuAnimation;
	
	//Sound when balloon guy dies and makes it through next level
	public static Sound dead, birdDead, hit, boost;
	public static Sound coin;
	
	//Font to display text to screen
	public static BitmapFont font, shadow, healthFont, healthShadow, commandFont, commandShadow;
	
	//preferences settings
	public static Preferences prefs;
	
	public static void load(){
		
		/*****************************************************
		 * *****************Game Textures*********************
		 */
		
		texture = new Texture(Gdx.files.internal("data/jetpackjohnny.ss.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg = new TextureRegion(texture, 0,0,100,100);
		bg.flip(false, true);
		
		johnny = new TextureRegion(texture,703,1, 83, 98);
		johnny.flip(false, true);
		
		johnnySmoke = new TextureRegion(texture,803,1, 83, 98);
		johnnySmoke.flip(false, true);
		
		TextureRegion[] johnnyAnim = {johnny, johnnySmoke};
		johnnyJetpack = new Animation(.25f, johnnyAnim);
		johnnyJetpack.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		johnnyBoost = new TextureRegion(texture,903,1, 83, 98);
		johnnyBoost.flip(false, true);
		
		cloud = new TextureRegion(texture, 312, 34, 73, 27);
		cloud.flip(false, true);
		
		blackCloud = new TextureRegion(texture,412,34, 73, 27);
		blackCloud.flip(false, true);
		
		bird1 = new TextureRegion(texture, 531, 37, 37, 23);
		bird1.flip(false, true);
		
		bird1Up = new TextureRegion(texture, 631, 37, 37, 23);
		bird1Up.flip(false, true);
		
		bird2 = new TextureRegion(texture, 531, 37, 37, 23);
		bird2.flip(true, true);
		
		bird2Up = new TextureRegion(texture, 631, 37, 37, 23);
		bird2Up.flip(true, true);
		
		TextureRegion[] birdsRight = {bird1, bird1Up};
		bird1Animation = new Animation(.15f, birdsRight);
		bird1Animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		TextureRegion[] birdsLeft = {bird2, bird2Up};
		bird2Animation = new Animation(.15f, birdsLeft);
		bird2Animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		
		/*****************************************************
		 * *****************Logo Textures*********************
		 */
		
		
		logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		logoTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		logo = new TextureRegion(logoTexture);
		logo.flip(false, false);
		
		
		/*****************************************************
		 * *****************Menu Textures*********************
		 */
		
		
		menuTexture = new Texture(Gdx.files.internal("data/menu4.ss.png"));
		menuTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		menu = new TextureRegion(menuTexture, 0, 0, 386, 455);
		menu.flip(false, true);
		
		options = new TextureRegion(menuTexture, 386, 0, 385, 455);
		options.flip(false, true);
		
		easyTrue = new TextureRegion(menuTexture, 779, 9, 57, 28);
		easyTrue.flip(false, true);
		
		easyFalse = new TextureRegion(menuTexture, 780, 50, 56, 27);
		easyFalse.flip(false, true);
		
		hardTrue = new TextureRegion(menuTexture, 779, 91, 57, 28);
		hardTrue.flip(false, true);
		
		hardFalse = new TextureRegion(menuTexture, 780, 132, 56, 27);
		hardFalse.flip(false, true);
		
		soundOnTrue = new TextureRegion(menuTexture, 779, 173, 57, 28);
		soundOnTrue.flip(false, true);
		
		soundOnFalse = new TextureRegion(menuTexture, 780, 214, 56, 27);
		soundOnFalse.flip(false, true);
		
		soundOffTrue = new TextureRegion(menuTexture, 779, 255, 57, 28);
		soundOffTrue.flip(false, true);
		
		soundOffFalse = new TextureRegion(menuTexture, 780, 296, 56, 27);
		soundOffFalse.flip(false, true);
		
		back = new TextureRegion(menuTexture, 779, 336, 57, 28);
		back.flip(false, true);
		
		menuRocketFire = new TextureRegion(menuTexture, 1157, 0, 206, 154);
		menuRocketFire.flip(false, true);
		
		menuRocketNoFire = new TextureRegion(menuTexture, 1543, 0, 206, 154);
		menuRocketNoFire.flip(false, true);
		
		TextureRegion[] menuAnim = {menuRocketFire, menuRocketNoFire};
		menuAnimation = new Animation(2f, menuAnim);
		menuAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		
		/*****************************************************
		 * *****************Game Over Textures****************
		 */
		
		gameOverTexture = new Texture(Gdx.files.internal("data/gameover2.ss.png"));
		gameOverTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		highScore = new TextureRegion(gameOverTexture, 6, 7, 199, 42);
		highScore.flip(false, true);
		
		gameOver = new TextureRegion(gameOverTexture, 6, 65, 199, 42);
		gameOver.flip(false, true);
		
		displayEasy = new TextureRegion(gameOverTexture, 7, 126, 199, 85);
		displayEasy.flip(false, true);
		
		displayHard = new TextureRegion(gameOverTexture, 7, 226, 199, 85);
		displayHard.flip(false, true);
		
		menuButton = new TextureRegion(gameOverTexture, 7, 330, 88, 39);
		menuButton.flip(false, true);
		
		retryButton = new TextureRegion(gameOverTexture, 117, 330, 88, 39);
		retryButton.flip(false, true);
		
		goldStar = new TextureRegion(gameOverTexture, 7, 394, 17, 17);
		goldStar.flip(false, true);
		
		blackStar = new TextureRegion(gameOverTexture, 30, 394, 17, 17);
		blackStar.flip(false, true);
		
		health = new TextureRegion(gameOverTexture, 210, 0, 65, 245);
		health.flip(false, true);
		
		leaderBoards = new TextureRegion(gameOverTexture, 430, 8, 206, 39);
		leaderBoards.flip(false, true);
				
		
		
		dead = Gdx.audio.newSound(Gdx.files.internal("data/explosion.wav"));
		birdDead = Gdx.audio.newSound(Gdx.files.internal("data/birdChirp.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		hit = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
		boost = Gdx.audio.newSound(Gdx.files.internal("data/jetpack.wav"));
		
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);
		
		healthFont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		healthFont.setScale(.15f, -.15f);
		healthShadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		healthShadow.setScale(.15f, -.15f);
		
		commandFont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		commandFont.setScale(.18f, -.18f);
		commandShadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		commandShadow.setScale(.18f, -.18f);
		
		//Create or retrieve existing preferences file
		prefs = Gdx.app.getPreferences("HotAir");
		
		//provide default high score of 0	
		if(!prefs.contains("difficulty")){
			setDifficulty(true);
		}
		
		if(!prefs.contains("sound")){
			setSound(true);
		}
		
		if(!prefs.contains("easyHighScore")){
			prefs.putInteger("easyHighScore", 0);
		}
		
		if(!prefs.contains("hardHighScore")){
			prefs.putInteger("hardHighScore", 0);
		}
		
		prefs.flush();
	}
	
	
	//Receives integer and maps it to the high score prefs
	public static void setHighScore(int val){
		
		if(getDifficulty() == true){
			prefs.putInteger("easyHighScore", val);
			prefs.flush(); //saves the preferences
		} else {
			prefs.putInteger("hardHighScore", val);
		prefs.flush(); //saves the preferences
		}
		
	}
	
	//Retrieves the current high score to use in other classes
	public static int getHighScore(){
		
		if(getDifficulty() == true){
			return prefs.getInteger("easyHighScore");
		} else {
			return prefs.getInteger("hardHighScore");
		}
		
	}
	
	public static void setDifficulty(boolean diff){
		prefs.putBoolean("difficulty", diff);
		prefs.flush();
	}
	
	
	public static boolean getDifficulty(){
		return prefs.getBoolean("difficulty");
	}
	
	public static void setSound(boolean sound){
		prefs.putBoolean("sound", sound);
		prefs.flush();
	}
	
	public static boolean getSound(){
		return prefs.getBoolean("sound");
	}
	
	
	public static void dispose(){
		//We must dispose of the assets when finished
		texture.dispose();
		logoTexture.dispose();
		menuTexture.dispose();
		gameOverTexture.dispose();
		birdDead.dispose();
		hit.dispose();
		boost.dispose();
		dead.dispose();
		coin.dispose();
		font.dispose();
		shadow.dispose();
		healthFont.dispose();
		healthShadow.dispose();
		commandFont.dispose();
		commandShadow.dispose();
	}
}
