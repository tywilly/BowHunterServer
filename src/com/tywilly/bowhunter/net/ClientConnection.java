package com.tywilly.bowhunter.net;

import java.net.SocketAddress;

import com.tywilly.bowhunter.entity.player.Player;
import com.tywilly.bowhunter.entity.player.PlayerLoader;
import com.tywilly.bowhunter.net.protocol.PacketManager;
import com.tywilly.bowhunter.net.protocol.packets.Packet;
import com.tywilly.bowhunter.update.UpdateThread;

public class ClientConnection {

	private PacketManager packetMan;
	
	private SocketAddress address;

	private Player player = new Player();
	
	private Server server;

	public ClientConnection(SocketAddress address, Server server) {
		this.server = server;
		this.address = address;
		packetMan = new PacketManager();
	}

	public Player getPlayer() {
		return player;
	}

	public ClientConnection getThis() {
		return this;
	}

	public void receivePacket(byte id, String data){
		
		packetMan.getPacket(id).onRecieve(id, data, this);
		
	}
	
	public void sendPacket(Packet pack) {
		server.sendPacket(pack, this);
	}

	public SocketAddress getAddress(){
		return address;
	}
	
	public void disconnect() {
		System.out.println("User " + getPlayer().getUsername() + " has left!");

		UpdateThread.ents.remove(this.getPlayer());

		PlayerLoader.savePlayer(this.getPlayer());

		Server.clients.remove(this);

	}

}
