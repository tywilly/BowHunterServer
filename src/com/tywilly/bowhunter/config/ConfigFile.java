package com.tywilly.bowhunter.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ConfigFile{

	HashMap<String, String> properties = new HashMap<String, String>();

	Scanner scan;
	
	File file;

	public ConfigFile(String arg0) {
		file = new File(arg0);
	}

	public void putString(String key, String val){
		properties.put(key, val);
	}
	
	public void putInt(String key, int val){
		properties.put(key, val + "");
	}
	
	public void putFloat(String key, float val){
		properties.put(key, val + "");
	}
	
	public void putBoolean(String key, boolean val){
		properties.put(key, val + "");
	}
	
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(properties.get(key.toLowerCase()));
	}

	public float getFloat(String key) {
		return Float.parseFloat(properties.get(key.toLowerCase()));
	}

	public int getInt(String key) {
		return Integer.parseInt(properties.get(key.toLowerCase()));
	}

	public String getString(String key) {
		return properties.get(key);
	}

	public boolean load() {

		if (file.exists()) {

			try {
				scan = new Scanner(file);

				while (scan.hasNextLine()) {

					String line = scan.nextLine();

					properties.put(
							line.toLowerCase().substring(0, line.indexOf(":")),
							line.substring(line.indexOf(":") + 1, line.length()));
					
				}

				scan.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return true;
			
		}else{
			System.out.println("Registering new player!");
			return false;
		}
	}
	
	public void save(){
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter write = new FileWriter(file);
			
			String[] keys = properties.keySet().toArray(new String[properties.size()]);
			String[] val = properties.values().toArray(new String[properties.size()]);
			
			for(int i=0;i<properties.size();i++){
				
				write.write(keys[i] + ":" + val[i] + "\n");
				
			}
			
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
