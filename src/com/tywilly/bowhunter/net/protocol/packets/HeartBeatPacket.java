package com.tywilly.bowhunter.net.protocol.packets;

import java.util.Random;

import com.tywilly.bowhunter.net.ClientConnection;

public class HeartBeatPacket extends Packet {

	public HeartBeatPacket() {
		super((byte) 18, "");
		
		String s = "";
		
		Random r = new Random();
		
		s += r.nextInt(999999);
		
		this.setPayload(s);
	}
	
	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub
		
		if(payload.equals(connection.getLastHeartBeatString())){
			connection.setAlive(true);
			connection.setHeartBeatSent(false);
		}else{
			connection.setAlive(false);
		}
		
	}

}
