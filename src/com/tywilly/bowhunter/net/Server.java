package com.tywilly.bowhunter.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

import com.tywilly.bowhunter.entity.player.Player;
import com.tywilly.bowhunter.net.protocol.PacketManager;
import com.tywilly.bowhunter.net.protocol.packets.Packet;

public class Server {

	DatagramSocket sock;

	byte[] recBuffer = new byte[1024];
	byte[] sendBuffer = new byte[2048];

	DatagramPacket recPacket = new DatagramPacket(recBuffer, recBuffer.length);
	DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length);

	public static ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();

	public Server(int portNum) {
		try {
			sock = new DatagramSocket(portNum);

			new PacketManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ClientConnection getClientConnectionByUsername(String username) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getPlayer().getUsername() == username) {
				return clients.get(i);
			}
		}

		return null;

	}

	public static ClientConnection getClientConnectionByAddress(SocketAddress address) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getAddress().equals(address)) {
				return clients.get(i);
			}
		}

		return null;

	}

	public static Player getPlayerByUUID(String UUID) {

		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getPlayer().getUUID() == UUID) {
				return clients.get(i).getPlayer();
			}
		}

		return null;
	}

	public static Player getPlayerByName(String username) {

		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getPlayer().getUsername() == username) {
				return clients.get(i).getPlayer();
			}
		}

		return null;

	}

	public void sendPacket(Packet pack, ClientConnection cli) {
		try {
			sendPacket.setData(pack.getData().getBytes());
			// sendPacket.setLength(pack.getData().getBytes().length);
			sendPacket.setSocketAddress(cli.getAddress());

			sock.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		System.out.println("Started listening on port " + sock.getLocalPort() + "!");

		while (!sock.isClosed()) {

			try {
				sock.receive(recPacket);

				String line = new String(recPacket.getData(), 0, recPacket.getLength());

				byte id = Byte.parseByte(line.substring(0, line.indexOf(" ")));

				String payload = line.substring(line.indexOf(" ") + 1, line.length());

				if (id == 01) {
					clients.add(new ClientConnection(recPacket.getSocketAddress(), this));
				}
				ClientConnection con = Server.getClientConnectionByAddress(recPacket.getSocketAddress());
				if (con != null) {
					con.receivePacket(id, payload);
				} else {
					System.err.println("Unknown connection!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
