package com.tywilly.bowhunter.net.protocol;

import java.util.ArrayList;

import com.tywilly.bowhunter.net.protocol.packets.ActionPacket;
import com.tywilly.bowhunter.net.protocol.packets.ChatPacket;
import com.tywilly.bowhunter.net.protocol.packets.DespawnEntityPacket;
import com.tywilly.bowhunter.net.protocol.packets.DisconnectPacket;
import com.tywilly.bowhunter.net.protocol.packets.LoginPacket;
import com.tywilly.bowhunter.net.protocol.packets.MovePacket;
import com.tywilly.bowhunter.net.protocol.packets.Packet;

public class PacketManager {

	private static Packet[] packets = new Packet[256];
	
	private ArrayList<Packet> awaitingPackets = new ArrayList<Packet>();
	
	public PacketManager(){
		
		packets[1] = new LoginPacket();
		packets[2] = new DisconnectPacket();
		
		packets[3] = new MovePacket();
		
		packets[5] = new DespawnEntityPacket();
		
		packets[9] = new ActionPacket();
		
		packets[11] = new ChatPacket();
		
	}
	
	public static Packet getPacket(byte id){
		return packets[id];
	}
	
	public void queuePacket(Packet packet){
		awaitingPackets.add(packet);
	}
	
	public ArrayList<Packet> getQueue(){
		return awaitingPackets;
	}
	
}
