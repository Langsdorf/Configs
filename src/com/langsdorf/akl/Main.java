package com.langsdorf.akl;

import java.io.IOException;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	
	@Override
	public void onEnable() {
		Configs orkutyml = criarConfig("orkut.yml");
		
		Configs facebookyml = criarConfig("facebook.yml");
		
		Configs orkuttxt = criarConfig("orkut.txt");
		
		Configs facebooktxt = criarConfig("facebook.txt");
		
		writeInYML(orkutyml, "Orkut.Langsdorf.ID", 1);
		writeInYML(orkutyml, "Orkut.Langsdorf.Name", "Langsdorf");
		writeInYML(orkutyml, "Orkut.Langsdorf.Double", 1.0200023);
		
		writeInYML(facebookyml, "Facebook.Langsdorf.ID", 1);
		writeInYML(facebookyml, "Facebook.Langsdorf.Name", "Langsdorf");
		writeInYML(facebookyml, "Facebook.Langsdorf.Double", 1.0200023);
		
		write(orkuttxt, "Orkut.Langsdorf.ID: 1" + System.getProperty("line.separator"));
		write(orkuttxt, "Orkut.Langsdorf.Name: Langsdorf" + System.getProperty("line.separator"));
		write(orkuttxt, "Orkut.Langsdorf.Double: 1.992" + System.getProperty("line.separator"));
		
		write(facebooktxt, "Facebook.Langsdorf.ID: 1" + System.getProperty("line.separator"));
		write(facebooktxt, "Facebook.Langsdorf.Name: Langsdorf" + System.getProperty("line.separator"));
		write(facebooktxt, "Facebook.Langsdorf.Double: 1.0200023" + System.getProperty("line.separator"));
	}
	
	public void writeInYML(Configs c, String key, Object o) {
		try {
			c.write(true, key, o);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(Configs c, Object o) {
		try {
			c.write(false, o);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Configs criarConfig(String nome) {
		Configs c = new Configs(this, nome);
		try {
			c.saveDefault(true, false);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return c;
	}
	


}
