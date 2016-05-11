package com.tywilly.bowhunter.entity;

public abstract class Entity {

	String UUID = "";
	
	private float xLocation = 0;
	private float yLocation = 0;
	
	public abstract void update();
	
	public static String generateUUID(){
		return Math.random() * 1000 + "";
	}
	
	public void setUUID(String UUID){
		this.UUID = UUID;
	}
	
	public String getUUID(){
		return UUID;
	}
	
	public void setXLocation(float x){
		xLocation = x;
	}
	
	public float getXLocation(){
		return xLocation;
	}
	
	public void setYLocation(float y){
		yLocation = y;
	}
	
	public float getYLocation(){
		return yLocation;
	}
	
}
