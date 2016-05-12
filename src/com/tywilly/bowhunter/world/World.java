package com.tywilly.bowhunter.world;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.tywilly.bowhunter.config.ConfigFile;

public class World {

	private float xSpawnLocation = 00.0f;
	private float ySpawnLocation = 00.0f;
	
	private int width = 0;
	private int height = 0;
	
	ConfigFile worldFile;
	
	byte[][] worldTileType;
	
	String worldName = "";
	
	public World(String worldName){
		this.worldName = worldName;
		worldFile = new ConfigFile("assets" + File.separator + "world" + File.separator + worldName + ".dat");
		loadConfig();
		
		worldTileType = new byte[height][width - 1];
		
	}
	
	private void loadConfig(){
		
		worldFile.load();
		
		xSpawnLocation = worldFile.getFloat("world.spawn.x");
		ySpawnLocation = worldFile.getFloat("world.spawn.y");
		
		width = worldFile.getInt("world.width");
		height = worldFile.getInt("world.height");
		
	}
	
	public void loadWorld(){
		
		File worldFile = new File("assets" + File.separator + "world" + File.separator + worldName + ".world");
		
		try {
			Scanner scan = new Scanner(worldFile);
			
			int count = 0;
			
			while(scan.hasNextLine()){
				
				String line = scan.nextLine();
				
				for(int i=0;i<line.length();i++){
					
					worldTileType[count][i] = Byte.parseByte(line.charAt(i) + "");
					
				}
				
				count++;
				
			}
			
			scan.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getXSpawnLocation(){
		return xSpawnLocation;
	}
	
	public float getYSpawnLocation(){
		return ySpawnLocation;
	}
	
	public String toString(){
		
		String world = "";
		
		for(int i=0;i<worldTileType.length;i++){
			for(int x=0;x<worldTileType[i].length;x++){
				
				world += worldTileType[i][x];
				
			}
			world += ":";
		}
		
		return world;
	}
	
}
