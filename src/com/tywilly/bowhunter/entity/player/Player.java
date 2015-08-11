package com.tywilly.bowhunter.entity.player;

import com.tywilly.bowhunter.entity.Entity;
import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.net.protocol.packets.MovePacket;
import com.tywilly.bowhunter.net.protocol.packets.SetHealthPacket;

public class Player extends Entity {

	private String username = "";

	private int health = 100;

	private int exp = 0;
	
	public Player() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userame) {
		this.username = userame;
	}

	public void setHealth(int health) {
		this.health = health;

		if (this.health > 0) {
			Server.getClientConnectionByUsername(getUsername()).sendPacket(
					new SetHealthPacket(getUUID(), getHealth()));
		} else {
			// Die
			onDeath();
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	private void onDeath(){
		
		for(int i=0;i<Server.clients.size();i++){
			ClientConnection cli = Server.clients.get(i);
			
			cli.sendPacket(new MovePacket(getUUID(), 200 + " " + 200));
		}
		
		this.setHealth(100);
		this.setExp(0);
		
	}

	public void setExp(int exp){
		this.exp = exp;
	}
	
	public int getExp(){
		return exp;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
