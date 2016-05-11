package com.tywilly.bowhunter.net.webserver;

import java.io.File;
import java.io.IOException;

import org.jibble.simplewebserver.SimpleWebServer;

import com.tywilly.bowhunter.Driver;

public class UpdateWebServer {
	
	SimpleWebServer webServer;
	
	public UpdateWebServer(){
		
		try {
			System.out.println("WebServer: Starting on port " + Driver.getConfig().getInt("server.update.port") + "!");
			webServer = new SimpleWebServer(new File("./assets"), Driver.getConfig().getInt("server.update.port"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void stop(){
		System.out.println("WebServer: Stopping!");
		webServer.stop();
	}
	
}
