package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;

public class SpawnEntityPacket extends Packet {

	public SpawnEntityPacket(String UUID, String entityPath, String location, int rotation){
		super((byte)04, UUID + " " + entityPath + " " + location + " " +  rotation);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
	}

}
