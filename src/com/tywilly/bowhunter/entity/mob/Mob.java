package com.tywilly.bowhunter.entity.mob;

import com.tywilly.bowhunter.entity.Entity;

public abstract class Mob extends Entity{

	private int mobId = -1;
	
	private float xDir = 0.0f;
	private float yDir = 0.0f;
	
	private int health = 100;
	
	public Mob(int mobId){
		this.mobId = mobId;
	}
	
	public int getMobID(){
		return mobId;
	}

	public float getXDir() {
		return xDir;
	}

	public void setXDir(float xDir) {
		this.xDir = xDir;
	}

	public float getYDir() {
		return yDir;
	}

	public void setYDir(float yDir) {
		this.yDir = yDir;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
}
