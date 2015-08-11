package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.world.World;

public class WorldPacket extends Packet {

	public WorldPacket(World world){
		super((byte)10, world.toString());
		
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub

	}

}
