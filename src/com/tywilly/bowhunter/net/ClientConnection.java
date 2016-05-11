package com.tywilly.bowhunter.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.tywilly.bowhunter.entity.player.Player;
import com.tywilly.bowhunter.entity.player.PlayerLoader;
import com.tywilly.bowhunter.net.protocol.PacketManager;
import com.tywilly.bowhunter.net.protocol.packets.Packet;
import com.tywilly.bowhunter.update.UpdateThread;

public class ClientConnection {

	Socket sock;
	BufferedReader in;
	PrintWriter out;
	PacketManager packetMan;

	Player player = new Player();

	public ClientConnection(Socket sock) {
		this.sock = sock;
		packetMan = new PacketManager();
		this.run();
	}

	public Socket getSocket() {
		return sock;
	}

	public Player getPlayer() {
		return player;
	}

	public ClientConnection getThis() {
		return this;
	}

	public void run() {
		// TODO Auto-generated method stub

		try {

			in = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream());

			Thread inStream = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					while (!sock.isClosed()) {

						try {

							String inputLine;

							inputLine = in.readLine();

							byte id = Byte.parseByte(inputLine.substring(0,
									inputLine.indexOf(' ')));

							String payload = inputLine.substring(
									inputLine.indexOf(' ')+1, inputLine.length());

							PacketManager.getPacket(id).onRecieve(id, payload,
									getThis());

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
							disconnect();
						}

					}
					
				}
			});

			inStream.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			disconnect();
			
		}

	}

	public void sendPacket(Packet pack){
		out.println(pack.getData());
		out.flush();
	}
	
	public void disconnect(){
		
		try {
			sock.close();
			
			System.out.println("User " + getPlayer().getUsername() + " has left!");
			
			UpdateThread.ents.remove(this.getPlayer());
			
			PlayerLoader.savePlayer(this.getPlayer());
			
			Server.clients.remove(this);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
