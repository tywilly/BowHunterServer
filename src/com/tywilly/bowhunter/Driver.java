package com.tywilly.bowhunter;

import com.tywilly.bowhunter.config.ConfigFile;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.net.webserver.UpdateWebServer;
import com.tywilly.bowhunter.update.UpdateThread;
import com.tywilly.bowhunter.world.World;

public class Driver {

	Server server;

	UpdateThread updateThread;

	public static World world = new World("main");
	
	static ConfigFile config = new ConfigFile("config.dat");
	
	public Driver() {
		
		System.out.println("Loading config...");
		
		config.load();
		
		System.out.println("Loading world...");
		
		world.loadWorld();
		
		System.out.println("DONE!");
		
		server = new Server(config.getInt("server.port"));

		updateThread = new UpdateThread();

		updateThread.start();
		server.run();
		
	}
	
	public static ConfigFile getConfig(){
		return config;
	}

	public static void main(String[] args) {

		System.out.println("Starting server...");

		UpdateWebServer updateServer = new UpdateWebServer();
		
		new Driver();

	}

}
