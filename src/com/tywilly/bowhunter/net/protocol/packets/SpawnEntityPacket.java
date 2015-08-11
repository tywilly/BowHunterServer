package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;

public class SpawnEntityPacket extends Packet {

	public SpawnEntityPacket(String UUID, int entityType, String location, int rotation){
		super((byte)04, UUID + " " + entityType + " " + location + " " +  rotation);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
	}

}
