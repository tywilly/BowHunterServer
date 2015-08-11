package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;

public class MovePacket extends Packet {

	public MovePacket() {
		super((byte) 03, "");
	}

	public MovePacket(String UUID, String location) {
		super((byte) 03, UUID + " " + location);
	}

	@Override
	public void onRecieve(byte id, String payload, ClientConnection con) {
		// TODO Auto-generated method stub

		String[] split = payload.split(" ");

		con.getPlayer().setXLocation(Float.parseFloat(split[0]));
		con.getPlayer().setYLocation(Float.parseFloat(split[1]));

		for (int i = 0; i < Server.clients.size(); i++) {
			ClientConnection cli = Server.clients.get(i);
			if (cli != con) {
				cli.sendPacket(new MovePacket(con.getPlayer().getUUID(), con
						.getPlayer().getXLocation()
						+ " "
						+ con.getPlayer().getYLocation()));

//				if ((Math.abs(con.getPlayer().getXLocation()
//						- cli.getPlayer().getXLocation()) <= 500)
//						|| Math.abs(con.getPlayer().getYLocation()
//								- cli.getPlayer().getYLocation()) <= 500) {
//
//					con.sendPacket(new SpawnPlayerPacket(cli.getPlayer()
//							.getUUID(), cli.getPlayer().getUsername(), cli
//							.getPlayer().getXLocation()
//							+ " "
//							+ cli.getPlayer().getYLocation()));
//
//				}

			}

		}

	}
}
