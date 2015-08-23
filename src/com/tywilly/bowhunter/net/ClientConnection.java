package com.tywilly.bowhunter.net;

import java.net.SocketAddress;

import com.tywilly.bowhunter.entity.player.Player;
import com.tywilly.bowhunter.entity.player.PlayerLoader;
import com.tywilly.bowhunter.net.protocol.PacketManager;
import com.tywilly.bowhunter.net.protocol.packets.HeartBeatPacket;
import com.tywilly.bowhunter.net.protocol.packets.Packet;
import com.tywilly.bowhunter.update.UpdateThread;

public class ClientConnection {
	
	private SocketAddress address;

	private Player player = new Player(this);
	
	private Server server;
	
	private String lastHeartBeatString = "";
	private long lastHeartBeatTime = 0;
	private boolean isAlive = true;
	private boolean isHeartBeatSent = false; 
	

	public ClientConnection(SocketAddress address, Server server) {
		this.server = server;
		this.address = address;
	}
	
	public Player getPlayer() {
		return player;
	}

	public ClientConnection getThis() {
		return this;
	}

	public long getLastHeartBeatTime(){
		return lastHeartBeatTime;
	}
	
	public String getLastHeartBeatString(){
		return lastHeartBeatString;
	}
	
	public void setHeartBeatSent(boolean b){
		this.isHeartBeatSent = b;
	}
	
	public boolean isHeartBeatSent(){
		return this.isHeartBeatSent;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
	
	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	public void receivePacket(byte id, String data){
		System.out.println(data);
		PacketManager.getPacket(id).onRecieve(id, data, this);
	}
	
	public void sendHeartBeat(){
		
		HeartBeatPacket hb = new HeartBeatPacket();
		
		this.lastHeartBeatString = hb.getPayload();
		
		this.lastHeartBeatTime = System.currentTimeMillis();
		
		this.sendPacket(hb);
		
		this.isHeartBeatSent = true;
		
	}
	
	public void sendPacket(Packet pack) {
		System.out.println(pack.getPayload());
		server.sendPacket(pack, this);
	}

	public SocketAddress getAddress(){
		return address;
	}
	
	public void disconnect(String reason) {
		System.out.println("User " + getPlayer().getUsername() + " has left! Reason: " + reason);

		UpdateThread.ents.remove(this.getPlayer());

		PlayerLoader.savePlayer(this.getPlayer());

		Server.clients.remove(this);
	}
	
	public void disconnect() {
		System.out.println("User " + getPlayer().getUsername() + " has left!");

		UpdateThread.ents.remove(this.getPlayer());

		PlayerLoader.savePlayer(this.getPlayer());

		Server.clients.remove(this);
	}

}
