package com.tywilly.bowhunter;

import java.io.IOException;

import com.tywilly.bowhunter.config.ConfigFile;
import com.tywilly.bowhunter.net.Server;
import com.tywilly.bowhunter.net.webserver.UpdateFileParser;
import com.tywilly.bowhunter.net.webserver.UpdateWebServer;
import com.tywilly.bowhunter.update.UpdateThread;
import com.tywilly.bowhunter.world.World;

public class Driver {

	Server server;

	UpdateThread updateThread;

	UpdateWebServer updateServer;
	
	public static World world = new World("default");
	
	static ConfigFile config = new ConfigFile("config.dat");
	
	public Driver() {
		
		System.out.println("Loading config...");
		
		config.load();
		
		UpdateFileParser ufp;
		try {
			ufp = new UpdateFileParser();
			ufp.generateRevisionFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		updateServer = new UpdateWebServer();
		
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
		
		new Driver();

	}

}
