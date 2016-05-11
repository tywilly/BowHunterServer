package com.tywilly.bowhunter.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.tywilly.bowhunter.entity.player.Player;

public class Server {

	ServerSocket sock;
	
	public static ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
	
	
	
	public Server(int portNum){
		try {
			sock = new ServerSocket(portNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ClientConnection getClientConnectionByUsername(String username){
		for(int i=0;i<clients.size();i++){
			if(clients.get(i).getPlayer().getUsername() == username){
				return clients.get(i);
			}
		}
		
		return null;
		
	}
	
	public static Player getPlayerByUUID(String UUID){

		for(int i=0;i<clients.size();i++){
			if(clients.get(i).getPlayer().getUUID() == UUID){
				return clients.get(i).getPlayer();
			}
		}
		
		return null;
	}
	
	public static Player getPlayerByName(String username){
		
		for(int i=0;i<clients.size();i++){
			if(clients.get(i).getPlayer().getUsername() == username){
				return clients.get(i).getPlayer();
			}
		}
		
		return null;
		
	}
	
	public void run(){
		
		System.out.println("Started listeing on port " + sock.getLocalPort() + "!");
		
		while(!sock.isClosed()){
			
			try {
				clients.add(new ClientConnection(sock.accept()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
