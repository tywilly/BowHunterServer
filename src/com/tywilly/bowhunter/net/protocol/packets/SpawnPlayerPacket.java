package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;

public class SpawnPlayerPacket extends Packet {

	public SpawnPlayerPacket(){
		super((byte)07, "");
	}
	
	public SpawnPlayerPacket(String UUID, String username, String location){
		super((byte)07, UUID + " " + username + " " + location);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
	}

}
