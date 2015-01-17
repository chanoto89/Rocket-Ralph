package com.thundershock.gamehelpers;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.thundershock.gameobjects.Rocket;

public class Bird extends Scrollable{
	
	//Create Collision Circle
	private Circle boundingBird;
	
	//determine if bird was hit
	private boolean isHit;
	
	//random number to determine when bird will show up
	private Random r;
	
	//determine if bird should show up
	private boolean isDisplayed;
	
	//determine if bird is coming from left
	private boolean isLeft;

	public Bird(float x, float y, int width, int height, float scrollSpeedY, float scrollSpeedX) {
		super(x, y, width, height, scrollSpeedY, scrollSpeedX);
		
		boundingBird = new Circle();
		
		r = new Random();
		
		if(AssetLoader.getDifficulty() == true){
			if(r.nextInt(3) == 1){
				isDisplayed = true;
			} else isDisplayed = false;
		} else{
			if(r.nextInt(2) == 1){
				isDisplayed = true;
			} else isDisplayed = false;
		}
		
		

	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		

		boundingBird.set(position.x + 6f, position.y + 6, 4.5f);
	}
	
	@Override
	public void reset(float newY){
		super.reset(newY);
		
		if(isLeft){
			this.position.x = -30;
		} else this.position.x = 150;
		
		if(r.nextInt(3) == 1){
			isDisplayed = true;
		} else isDisplayed = false;

		isHit = false;
	}
	
	public void onRestart(float y, float scrollSpeed){
		velocity.y = scrollSpeed;
		reset(y);
	}
	
	public boolean collides(Rocket balloon){
		if(position.y + this.height > balloon.getY()){
			if(this.isHit == false && this.isDisplayed){
				if(Intersector.overlaps(balloon.getBoundingCircle(), this.boundingBird) || Intersector.overlaps(this.boundingBird, balloon.getBoundingRectangle())
						|| Intersector.overlaps(this.boundingBird, balloon.getBoundingJetpack())){
					isHit = true;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean getIsDisplayed(){
		return isDisplayed;
	}
	
	public void setIsLeft(boolean isLeft){
		this.isLeft = isLeft;
	}
	
	public Circle getBoundingBird(){
		return boundingBird;
	}
	
	public boolean getIsHit(){
		return isHit;
	}

}
