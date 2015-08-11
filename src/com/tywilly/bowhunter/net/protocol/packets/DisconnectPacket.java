package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;

public class DisconnectPacket extends Packet{

	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		connection.disconnect();
		
		for(int i=0;i<Server.clients.size();i++){
			
			ClientConnection cli = Server.clients.get(i);
			
			cli.sendPacket(new DespawnEntityPacket(connection.getPlayer().getUUID()));
			
		}
		
	}

}
