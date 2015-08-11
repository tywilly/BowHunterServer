package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;

public class DespawnEntityPacket extends Packet {

	public DespawnEntityPacket() {
		super((byte)05, "");
	}
	
	public DespawnEntityPacket(String UUID){
		super((byte)05, UUID);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
		
		
		
	}

}
