package com.tywilly.bowhunter.net.protocol.packets;

import com.tywilly.bowhunter.Driver;
import com.tywilly.bowhunter.entity.Entity;
import com.tywilly.bowhunter.entity.player.PlayerLoader;
import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.update.UpdateThread;

public class LoginPacket extends Packet {

	public LoginPacket() {
		super((byte) 01, "");
	}

	public LoginPacket(String UUID) {
		super((byte) 01, UUID);
	}

	@Override
	public void onRecieve(byte id, String payload, ClientConnection con) {
		// TODO Auto-generated method stub

		System.out.println("User " + payload + " logged in!");

		String[] payloadS = payload.split(" ");

		con.getPlayer().setUsername(payloadS[0]);

		PlayerLoader.loadPlayer(con.getPlayer());

		con.getPlayer().setUUID(Entity.generateUUID());

		UpdateThread.ents.add(con.getPlayer());

		con.sendPacket(new LoginPacket(con.getPlayer().getUUID()));

		con.sendPacket(new WorldPacket(Driver.world));
		
		con.sendPacket(new MovePacket(con.getPlayer().getUUID(),
				con.getPlayer().getXLocation() + " " + con.getPlayer().getYLocation()));

		for (int i = 0; i < Server.clients.size(); i++) {
			ClientConnection cli = Server.clients.get(i);

			if (cli != con) {
				con.sendPacket(new SpawnPlayerPacket(cli.getPlayer().getUUID(),
						cli.getPlayer().getUsername(), cli.getPlayer()
								.getXLocation()
								+ " "
								+ cli.getPlayer().getYLocation()));

				cli.sendPacket(new SpawnPlayerPacket(con.getPlayer().getUUID(),
						con.getPlayer().getUsername(), con.getPlayer()
								.getXLocation()
								+ " "
								+ con.getPlayer().getYLocation()));
			}
			
			cli.sendPacket(new ChatPacket(con.getPlayer().getUsername() + " has logged in!"));
			
		}

	}

}
