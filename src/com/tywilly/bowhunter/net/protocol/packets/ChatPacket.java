package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;

public class ChatPacket extends Packet {

	public ChatPacket(){
		super((byte)11,"");
	}
	
	public ChatPacket(String msg){
		super((byte)11, msg);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<Server.clients.size();i++){
			
			ClientConnection cli = Server.clients.get(i);
			
			if(cli != connection){
				
				cli.sendPacket(new ChatPacket(payload));
				
			}
			
		}
		
	}

}
