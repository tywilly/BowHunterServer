package com.tywilly.bowhunter.net.protocol.packets;

import java.util.Random;

import com.tywilly.bowhunter.net.ClientConnection;

public class HeartBeatPacket extends Packet {

	public HeartBeatPacket() {
		super((byte) 18, "");
		
		Random r = new Random();
		
		byte[] b = new byte[16];
		
		r.nextBytes(b);
		
		String s = new String(b);
		
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
			//connection.disconnect();
		}
		
	}

}
