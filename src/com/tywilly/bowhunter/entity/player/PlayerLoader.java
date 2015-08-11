package com.tywilly.bowhunter.entity.player;

import java.io.File;

import com.tywilly.bowhunter.config.ConfigFile;

public class PlayerLoader {

	static ConfigFile playerFile;

	public static void loadPlayer(Player player) {
		playerFile = new ConfigFile("players" + File.separator
				+ player.getUsername() + ".dat");

		if (playerFile.load()) {
			player.setXLocation(playerFile.getFloat("world.location.x"));
			player.setYLocation(playerFile.getFloat("world.location.y"));
		}
	}

	public static void savePlayer(Player player) {

		playerFile = new ConfigFile("players" + File.separator
				+ player.getUsername() + ".dat");

		playerFile.putFloat("world.location.x", player.getXLocation());
		playerFile.putFloat("world.location.y", player.getYLocation());

		playerFile.save();

	}
}
