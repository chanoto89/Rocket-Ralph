package com.thundershock.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Rocket {
	
	private Vector2 position;  
	private Vector2 velocity; 
	
	private int width;
	private int height;
	
	private int health;
	
	private boolean isAlive;
	
	private int readyCounter = 1;
	private boolean readyMove = true;
	
	private boolean isBoost;
	
	
	//Bounding Circle used for collision
	private Circle boundingCircle;
	//Bounding Rectangle used for collision
	private Rectangle boundingRectangle;
	
	private Rectangle boundingJetpack;
	
	public Rocket(float x, float y, int width, int height){
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0,0);
		
		//initialize the bounding circle and rectangle for the balloon
		boundingCircle = new Circle();
		boundingRectangle = new Rectangle();
		boundingJetpack = new Rectangle();
		//set isAlive to true
		isAlive = true;
		
		//initialize the health
		health = 5;
	}
	
	
	public void update(float delta){
		if(isAlive){
			//update balloon, bounding circle, and bounding rectangle
			position.x -= Gdx.input.getAccelerometerX();
			boundingCircle.set(position.x + 11f, position.y + 4.5f,  4f);
			boundingRectangle.set(position.x + 8f, position.y + 11f, 6f, 13f);
			boundingJetpack.set(position.x + .5f, position.y + 9f, 21f, 2f);
			
			if (position.x < 0){
				position.x = 0;
			} else if (position.x > 126){
				position.x = 126;
			}
		}
	}
	
	public void updateReady(float delta){
		if(this.readyMove == true){
			position.x += .5f;
			readyCounter += 1;
		} 
		
		if(this.readyMove == false){
			position.x -= .5f;
			readyCounter -= 1;
		}
		
		if(this.readyCounter > 100){
			this.readyMove = false;
		}
		
		if(this.readyCounter < -100){
			this.readyMove = true;
		}

	}
	
	public void subtractHealth(int x){
		health -= x;
		if(health < 0){
			health = 0;
		}
	}
	
	public void onRestart(int x){
		position.x = x;
		velocity.x = 0;
		isAlive = true;
		health = 5;
		this.readyCounter = 0;
		this.readyMove = true;
	}
	
	public void die(){
		isAlive = false;
	}
	
	public boolean getIsBoost(){
		return isBoost;
	}
	
	public void setIsBoost(boolean isBoost){
		this.isBoost = isBoost;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public float getWitdh(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public Circle getBoundingCircle(){
		return boundingCircle;
	}
	
	public Rectangle getBoundingRectangle(){
		return boundingRectangle;
	}
	
	public Rectangle getBoundingJetpack(){
		return boundingJetpack;
	}
	
	public boolean getIsAlive(){
		return isAlive;
	}
	
	public int getHealth(){
		return health;
	}
}
