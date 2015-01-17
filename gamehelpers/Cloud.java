package com.thundershock.gamehelpers;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.thundershock.gameobjects.Rocket;

public class Cloud extends Scrollable{
	
	private Random r;
	
	//Bounding rectangles for clouds
	private Rectangle cloud1, cloud2;
	
	//booleans to determine if cloud was black or white
	private boolean blackCloud1, blackCloud2;
	
	//boolean to determine if black cloud was hit
	private boolean isHitBlackCloud;
	
	//needed for collision
	private float cloud1CollisionWidth, cloud1CollisionX, cloud2CollisionWidth, cloud2CollisionX, cloudCollisionY, cloudCollisionHeight;
	
	//booleans to determine if cloud already hit
	private boolean cloud1Hit = false; 
	private boolean cloud2Hit = false;
	
	//boolean to determine if the cloud level was already scored
	private boolean isScored = false;
	
	//variable for cloud gap
	public int cloudGap;

	public Cloud(float x, float y, int width, int height, float scrollSpeedY, float scrollSpeedX) {
		super(x, y, width, height, scrollSpeedY, scrollSpeedX);
		//Initialize a Random object for Random number generation
		r = new Random();
		
		//initialize cloud1 and cloud2
		cloud1 = new Rectangle();
		cloud2 = new Rectangle();
		
		//determine if cloud 1 will be black cloud or white cloud
		if(r.nextInt(7) == 5){
			this.blackCloud1 = true;
		} if(r.nextInt(7) == 5){
			this.blackCloud2 = true;
		}
		
		//determine the cloud gap by the difficulty level
		if(AssetLoader.getDifficulty() == true){
			cloudGap = 23;
		} else cloudGap = 20;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		
		cloud1CollisionWidth = width * .8f;
		cloud1CollisionX = (width - cloud1CollisionWidth) / 2;
		
		cloud2CollisionWidth = (136 - (width + cloudGap)) * .8f;
		cloud2CollisionX = (136 - (width + cloudGap) - cloud2CollisionWidth) / 2 + 3; //adjusted to account for better collision
		
		cloudCollisionY = height / 4;
		cloudCollisionHeight = (height - cloudCollisionY) / 2;
		
		cloud1.set(position.x + cloud1CollisionX , position.y + cloudCollisionY, cloud1CollisionWidth, cloudCollisionHeight);
		//adjust the Y in cloud two for better collision for user
		cloud2.set(position.x + width + cloudGap + cloud2CollisionX, position.y + cloudCollisionY + 2, cloud2CollisionWidth, cloudCollisionHeight);
	}
	
	@Override
	public void reset(float newY){
		//call the reset method in the superclass 
		super.reset(newY);
		
		//change the width to a random number
		width = r.nextInt(83) + 15;
		
		//reset the collision on the clouds
		cloud1Hit = false;
		cloud2Hit = false;
		
		//reset the black clouds
		blackCloud1 = false;
		blackCloud2 = false;
		
		//reset if black cloud was hit
		isHitBlackCloud = false;
		
		//reset the isScored 
		isScored = false;
		
		//determine if clouds are black or white
		if(r.nextInt(7) == 5){
			this.blackCloud1 = true;
		} if(r.nextInt(7) == 5){
			this.blackCloud2 = true;
		}
	}
	
	public boolean collides(Rocket balloon){
		if(position.y + cloudCollisionY + cloudCollisionHeight > balloon.getY() ){
			return cloudCollisionCheck(balloon);
					
					/*(Intersector.overlaps(balloon.getBoundingCircle(), cloud1) 
					|| Intersector.overlaps(balloon.getBoundingCircle(), cloud2)
					|| Intersector.overlaps(balloon.getBoundingRectangle(), cloud1)
					|| Intersector.overlaps(balloon.getBoundingRectangle(), cloud2));*/
		}
		
		
		return false;
	}
	
	public void onRestart(float y, float scrollSpeed){
		velocity.y = scrollSpeed;
		reset(y);
	}
	
	
	public Rectangle getCloud1(){
		return cloud1;
	}
	
	public Rectangle getCloud2(){
		return cloud2;
	}
	
	public boolean getIsScored(){
		return isScored;
	}
	
	public void setScored(boolean isScored){
		this.isScored = isScored;
	}
	
	public boolean getIsBlackCloud1(){
		return this.blackCloud1;
	}
	
	public boolean getIsBlackCloud2(){
		return this.blackCloud2;
	}
	
	public boolean getIsHitBlackCloud(){
		return this.isHitBlackCloud;
	}
	
	public int getCloudGap(){
		return cloudGap;
	}
	
	
	public boolean cloudCollisionCheck(Rocket balloon){
		if(cloud1Hit == false){
			if(Intersector.overlaps(balloon.getBoundingCircle(), cloud1) || Intersector.overlaps(balloon.getBoundingRectangle(), cloud1) 
					||Intersector.overlaps(balloon.getBoundingJetpack(), cloud1)){
				cloud1Hit = true;
				if(blackCloud1 == true){
					this.isHitBlackCloud = true;
				}
				return true;
			} 
		}
		
		if (cloud2Hit == false){
			if (Intersector.overlaps(balloon.getBoundingCircle(), cloud2) || Intersector.overlaps(balloon.getBoundingRectangle(), cloud2)
					||Intersector.overlaps(balloon.getBoundingJetpack(), cloud2)) {
				cloud2Hit = true;
				if(blackCloud2 == true){
					this.isHitBlackCloud = true;
				}
				return true;
			}
		}
		return false;
	}
}
