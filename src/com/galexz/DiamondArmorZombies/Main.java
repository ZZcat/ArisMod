package com.galexz.DiamondArmorZombies;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		instance = this;
		
    	getServer().getPluginManager().registerEvents(new MyFirstListener(), this);
    	this.getCommand("heal").setExecutor((CommandExecutor)new HealCommand());
	}
	
	public void onDisable() {
		instance = null;
	}

}

