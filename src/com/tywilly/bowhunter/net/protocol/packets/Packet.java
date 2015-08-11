package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;



public abstract class Packet {
	
	private byte packetId;
	private String payload;
	
	public Packet(){
	}
	
	public Packet(byte id, String payload){
		this.packetId = id;
		this.payload = payload;
	}
	
	public abstract void onRecieve(byte id, String payload, ClientConnection connection);

	public byte getID(){
		return packetId;
	}

	public String getPayload(){
		return payload;
	}
	
	public String getData(){
		return packetId + " " + payload;
	}
	
}
