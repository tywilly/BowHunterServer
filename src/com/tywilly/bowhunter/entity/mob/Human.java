package com.tywilly.bowhunter.entity.mob;

import java.util.Calendar;
import java.util.Random;

import com.tywilly.bowhunter.Driver;
import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.net.protocol.packets.MovePacket;

public class Human extends Mob {

	int ticksAlive = 1;

	int ticksToWalk = 0;

	public Human(String UUID, float xLocation, float yLocation) {
		super(2);

		this.setUUID(UUID);
		this.setXLocation(xLocation);
		this.setYLocation(yLocation);
	}

	private void calcNewDirection() {
		Random r = new Random();

		ticksAlive = 1;

		ticksToWalk = r.nextInt(60) + 60;

		if (r.nextBoolean()) {
			this.setXDir(1.0f);
		} else {
			this.setXDir(-1.0f);
		}

		if (r.nextBoolean()) {
			this.setYDir(1.0f);
		} else {
			this.setYDir(-1.0f);
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		if (ticksToWalk <= ticksAlive) {
			calcNewDirection();
		} else {

			if (this.getXLocation() <= 0 || this.getXLocation() >= Driver.world.getWidth() * 64) {

				if (this.getYLocation() <= 0 || this.getYLocation() >= Driver.world.getHeight() * 64) {
					calcNewDirection();
				}

			}

			this.setXLocation(this.getXLocation() + this.getXDir());
			this.setYLocation(this.getYLocation() + this.getYDir());

			for (int i = 0; i < Server.clients.size(); i++) {

				ClientConnection cli = Server.clients.get(i);

				cli.sendPacket(new MovePacket(this.getUUID(), this.getXLocation() + " " + this.getYLocation()));

			}

		}

		ticksAlive++;

	}

}
