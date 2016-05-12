package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.entity.Arrow;
import com.tywilly.bowhunter.entity.Entity;
import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.update.UpdateThread;

public class ActionPacket extends Packet {

	public ActionPacket() {
		super((byte) 9, "");
	}

	@Override
	public void onRecieve(byte id, String payload, ClientConnection connection) {
		// TODO Auto-generated method stub

		String[] pay = payload.split(" ");

		switch (Integer.parseInt(pay[1])) {

		case 1:
			// Use item in hand

			Arrow arrow = new Arrow(Entity.generateUUID(), connection.getPlayer().getUUID());

			arrow.setXLocation(connection.getPlayer().getXLocation());
			arrow.setYLocation(connection.getPlayer().getYLocation());

			arrow.setXDirection(Float.parseFloat(pay[3]));
			arrow.setYDirection(Float.parseFloat(pay[4]));

			UpdateThread.ents.add(arrow);

			for (int i = 0; i < Server.clients.size(); i++) {
				ClientConnection cli = Server.clients.get(i);

				cli.sendPacket(new SpawnEntityPacket(arrow.getUUID(), "assets/weapons/bullets/arrow_right.png", arrow
						.getXDirection() + " " + arrow.getYDirection(), 0));

			}

			break;

		default:

			connection.disconnect();

			break;
		}

	}

}
