package com.tywilly.bowhunter.net.webserver;

import java.io.File;
import java.io.IOException;

import org.jibble.simplewebserver.SimpleWebServer;

public class UpdateWebServer {
	
	SimpleWebServer webServer;
	
	public UpdateWebServer(){
		
		try {
			System.out.println("WebServer: Starting!");
			webServer = new SimpleWebServer(new File("./assets"), 8080);
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
