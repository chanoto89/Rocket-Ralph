package com.thundershock.gamehelpers;

import com.thundershock.gameobjects.Rocket;
import com.thundershock.gameworld.GameWorld;

public class ScrollHandler {

	//Instance variables for needed objects
	private Cloud cloud1, cloud2, cloud3;
	
	// ScrollHandler will use the constants below to determine
    // how fast we need to scroll and also determine
    // the size of the gap between each pair of clouds.
	public static int scrollSpeed;
	public static final int CLOUD_GAP = 80;
	
	//Instance variable for needed bird objects
	private Bird bird1, bird2, bird3;
	public static final int BIRD_GAP = 40;
	public int birdSpeedPosX;
	public int birdSpeedNegX;
	
	
	//Instance Variable for game world class to increment score
	private GameWorld gameWorld;
	
	
	//Used to handle the speed up bonus
	private boolean isSpedUp;
	private int spedUpCount = 0;
	
	//variable to handle if cloud was hit and if black cloud was hit
	private boolean isHit;
	private boolean isBlackHit;
	
	//variable to handle bird being hit. To be used by game world class
	private boolean isBirdHit;
	
	//Constructor receives a float that tells us where we need to create our 
	//cloud objects
	public ScrollHandler(GameWorld gameWorld){
		this.gameWorld = gameWorld;
		
		setScrollSpeed();
		setBirdSpeed();
			
		cloud1 = new Cloud(0, -100, 30, 25, scrollSpeed, 0);
		cloud2 = new Cloud(0, cloud1.getTailY() - CLOUD_GAP, 80, 25, scrollSpeed, 0);
		cloud3 = new Cloud(0, cloud2.getTailY() - CLOUD_GAP, 50, 25, scrollSpeed, 0);
		
		bird1 = new Bird(-20, cloud1.getTailY() - BIRD_GAP, 12, 12, scrollSpeed, birdSpeedPosX);
		bird2 = new Bird(150, cloud2.getTailY() - BIRD_GAP, 12, 12, scrollSpeed, birdSpeedNegX);
		bird3 = new Bird(-30, cloud3.getTailY() - BIRD_GAP, 12, 12, scrollSpeed, birdSpeedPosX);
		
		//set whether bird comes in from left or right
		bird1.setIsLeft(true);
		bird2.setIsLeft(false);
		bird3.setIsLeft(true);
		
	}
	
	public void update(float delta){
		cloud1.update(delta);
		cloud2.update(delta);
		cloud3.update(delta);
		
		bird1.update(delta);
		bird2.update(delta);
		bird3.update(delta);
		
		//Check if any of the clouds are scrolled down, 
		//and resent accordingly
		if (cloud1.isScrolledDown()){
			cloud1.reset(cloud3.getTailY() - CLOUD_GAP);
		} else if (cloud2.isScrolledDown()){
			cloud2.reset(cloud1.getTailY() - CLOUD_GAP);
		} else if (cloud3.isScrolledDown()){
			cloud3.reset(cloud2.getTailY() - CLOUD_GAP);
		}
		
		if(bird1.isScrolledDown()){
			bird1.reset(cloud1.getTailY() - BIRD_GAP);
		} else if (bird2.isScrolledDown()){
			bird2.reset(cloud2.getTailY() - BIRD_GAP);
		} else if (bird3.isScrolledDown()){
			bird3.reset(cloud3.getTailY() - BIRD_GAP);
		}
	}
	
	public void updateReady(float delta){
		cloud1.update(delta);
		cloud2.update(delta);
		cloud3.update(delta);
		
		bird1.update(delta);
		bird2.update(delta);
		bird3.update(delta);
		
		
		if (cloud1.isScrolledDown()){
			cloud1.reset(cloud3.getTailY() - CLOUD_GAP);
		} else if (cloud2.isScrolledDown()){
			cloud2.reset(cloud1.getTailY() - CLOUD_GAP);
		} else if (cloud3.isScrolledDown()){
			cloud3.reset(cloud2.getTailY() - CLOUD_GAP);
		}
		
		if(bird1.isScrolledDown()){
			bird1.reset(cloud1.getTailY() - BIRD_GAP);
		} else if (bird2.isScrolledDown()){
			bird2.reset(cloud2.getTailY() - BIRD_GAP);
		} else if (bird3.isScrolledDown()){
			bird3.reset(cloud3.getTailY() - BIRD_GAP);
		}
	}
	
	public Cloud getCloud1(){
		return cloud1;
	}
	
	public Cloud getCloud2(){
		return cloud2;
	}
	
	public Cloud getCloud3(){
		return cloud3;
	}
	
	public Bird getBird1(){
		return bird1;
	}
	
	public Bird getBird2(){
		return bird2;
	}
	
	public Bird getBird3(){
		return bird3;
	}
	
	public void speedUp(){
		cloud1.speedUp();
		cloud2.speedUp();
		cloud3.speedUp();
		
		bird1.speedUp();
		bird2.speedUp();
		bird3.speedUp();
	}
	
	public void speedDown(){
		cloud1.speedDown();
		cloud2.speedDown();
		cloud3.speedDown();
		
		bird1.speedDown();
		bird2.speedDown();
		bird3.speedDown();
	}
	
	public void stop(){
		cloud1.stop();
		cloud2.stop();
		cloud3.stop();
		
		bird1.stop();
		bird2.stop();
		bird3.stop();
	}
	
	public void setIsSpedUp(boolean isSpedUp){
		this.isSpedUp = isSpedUp;
	}
	
	public void setSpedUpCount(int spedUpCount){
		this.spedUpCount = spedUpCount;
	}
	
	public boolean getIsBlackHit(){
		return this.isBlackHit;
	}
	
	public boolean getIsBirdHit(){
		return this.isBirdHit;
	}
	
	//return true if any time balloon hits the cloud zones
	public boolean collides(Rocket balloon){
		if(!cloud1.getIsScored() && cloud1.getY() + (cloud1.getHeight()/2) > balloon.getY()){
			gameWorld.addScore(1);
			cloud1.setScored(true);
			
			if(AssetLoader.getSound() == true){
				AssetLoader.coin.play(.7f);
			}
			
			
			if(isSpedUp){
				spedUpCount += 1;
				if((spedUpCount % 5) == 0){
					gameWorld.addScore(5);
				}
			}
		} else if(!cloud2.getIsScored() && cloud2.getY() + (cloud2.getHeight()/2) > balloon.getY()){
			gameWorld.addScore(1);
			cloud2.setScored(true);


			if(AssetLoader.getSound() == true){
				AssetLoader.coin.play(.7f);
			}
			
			if(isSpedUp){
				spedUpCount += 1;
				if((spedUpCount % 5) == 0){
					gameWorld.addScore(5);
				}
			}
		} else if(!cloud3.getIsScored() && cloud3.getY() + (cloud3.getHeight()/2) > balloon.getY()){
			gameWorld.addScore(1);
			cloud3.setScored(true);


			if(AssetLoader.getSound() == true){
				AssetLoader.coin.play(.7f);
			}
			
			if(isSpedUp){
				spedUpCount += 1;
				if((spedUpCount % 5) == 0){
					gameWorld.addScore(5);
				}
			}
		}
		
		this.isHit = (cloud1.collides(balloon) || cloud2.collides(balloon) || cloud3.collides(balloon) || bird1.collides(balloon) || bird2.collides(balloon) || bird3.collides(balloon)); 
		
		this.isBlackHit = (cloud1.getIsHitBlackCloud() || cloud2.getIsHitBlackCloud() || cloud3.getIsHitBlackCloud());
		
		this.isBirdHit = (bird1.getIsHit() || bird2.getIsHit() || bird3.getIsHit());
		
		return isHit; 
	}
	
	public void setScrollSpeed(){
		if(AssetLoader.getDifficulty() == true){
			scrollSpeed = 90;
		} else scrollSpeed = 120;
	}
	
	public void setBirdSpeed(){
		if(AssetLoader.getDifficulty() == true){
			birdSpeedPosX = 25;
			birdSpeedNegX = -25;
		} else {
			birdSpeedPosX = 45;
			birdSpeedNegX = -45;
		}
	}
	
	public void onRestart(){
		
		setScrollSpeed();
		
		cloud1.onRestart(-100, scrollSpeed);
		cloud2.onRestart(cloud1.getTailY() - CLOUD_GAP, scrollSpeed);
		cloud3.onRestart(cloud2.getTailY() - CLOUD_GAP, scrollSpeed);
		
		bird1.onRestart(cloud1.getTailY() - BIRD_GAP, scrollSpeed);
		bird2.onRestart(cloud2.getTailY() - BIRD_GAP, scrollSpeed);
		bird3.onRestart(cloud3.getTailY() - BIRD_GAP, scrollSpeed);
		
	}
	
}
