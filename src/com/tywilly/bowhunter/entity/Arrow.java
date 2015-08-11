package com.tywilly.bowhunter.entity;

import com.tywilly.bowhunter.entity.player.Player;
import com.tywilly.bowhunter.net.ClientConnection;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.net.protocol.packets.DespawnEntityPacket;
import com.tywilly.bowhunter.net.protocol.packets.MovePacket;
import com.tywilly.bowhunter.update.UpdateThread;

public class Arrow extends Entity {

	private float xDir = 0.0f;
	private float yDir = 0.0f;

	private float speed = 20.5f;

	private int ticksAlive = 0;

	private int damage = 10;

	private String shooterUUID = "";

	public Arrow(String UUID, String shooterUUID) {
		this.setUUID(UUID);
		this.setShooterUUID(shooterUUID);
	}

	public String getShooterUUID() {
		return shooterUUID;
	}

	public void setShooterUUID(String shooterUUID) {
		this.shooterUUID = shooterUUID;
	}

	public void setXDirection(float xDir) {
		this.xDir = xDir;
	}

	public float getXDirection() {
		return xDir;
	}

	public void setYDirection(float yDir) {
		this.yDir = yDir;
	}

	public float getYDirection() {
		return yDir;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	@Override
	public void update() {

		if (ticksAlive >= 60) {
			for (int i = 0; i < Server.clients.size(); i++) {
				ClientConnection cli = Server.clients.get(i);

				cli.sendPacket(new DespawnEntityPacket(this.getUUID()));

			}
			UpdateThread.ents.remove(this);
		}

		if (ticksAlive % 4 == 0) {

			for (int i = 0; i < UpdateThread.ents.size(); i++) {

				Entity ent = UpdateThread.ents.get(i);

				if (ent instanceof Player
						&& !((Player) ent).getUUID().equals(
								this.getShooterUUID())) {

					if ((Math.abs(ent.getXLocation() - this.getXLocation()) <= 64)
							&& (Math.abs(ent.getYLocation()
									- this.getYLocation()) <= 64)) {

						
						
						if(((Player)ent).getHealth() - this.getDamage() <= 0){
							
							//Death!
							Player shooter = Server.getPlayerByUUID(shooterUUID);
							
							shooter.setExp(shooter.getExp() + 100);
							
							((Player)ent).setHealth(0);
							
						}else{
							((Player) ent).setHealth(((Player) ent).getHealth()
									- this.getDamage());
						}
						
						for (int x = 0; x < Server.clients.size(); x++) {
							ClientConnection cli = Server.clients.get(x);

							cli.sendPacket(new DespawnEntityPacket(this
									.getUUID()));

						}

						UpdateThread.ents.remove(this);

					}

				}

			}

		}

		this.setXLocation(getXLocation() + (xDir * speed));
		this.setYLocation(getYLocation() + (yDir * speed));

		for (int i = 0; i < Server.clients.size(); i++) {
			ClientConnection cli = Server.clients.get(i);

			cli.sendPacket(new MovePacket(this.getUUID(), this.getXLocation()
					+ " " + this.getYLocation()));
		}

		ticksAlive++;

	}

}
