package com.thundershock.gamehelpers;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	//protected allows inheritance from sub classes
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledDown;
	
	public Scrollable(float x, float y, int width, int height, float scrollSpeedY, float scrollSpeedX){
		position = new Vector2(x, y);
		velocity = new Vector2(scrollSpeedX, scrollSpeedY);
		this.width = width;
		this.height = height;
		isScrolledDown = false;
	}
	
	public void update(float delta){
		position.add(velocity.cpy().scl(delta));
		
				
		//if the scroll-able object is not visible
		if (position.y > 204){
			isScrolledDown = true;
		}
	}
	
	public void updateReady(float delta){
		position.add(velocity.cpy().scl(delta));
		
		
		//if the scroll-able object is not visible
		if (position.y > 204){
			isScrolledDown = true;
		}
	}
	
	// Reset: Should Override in subclass for more specific behavior.
    public void reset(float newY) {
        position.y = newY;
        isScrolledDown = false;
    }
    
    public void stop(){
    	velocity.y = 0;
    }
    
    //method for speeding up the scrollable objects
	public void speedUp(){
		if(AssetLoader.getDifficulty() == true){
			velocity.y = 150;
		} else velocity.y = 180;
	}
	
    //method for decreasing speed for scrollable objects
	public void speedDown(){
		if(AssetLoader.getDifficulty() == true){
			velocity.y = 90;
		} else velocity.y = 120;
	}

    // Getters for instance variables
    public boolean isScrolledDown() {
        return isScrolledDown;
    }

    public float getTailY() {
        return position.y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	
}
