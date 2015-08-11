package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;

public class SetHealthPacket extends Packet {

	public SetHealthPacket(String UUID, int health){
		super((byte)8, UUID + " " + health);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub

	}

}
