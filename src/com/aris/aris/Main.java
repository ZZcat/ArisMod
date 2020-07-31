package com.aris.aris;

import org.bukkit.event.Listener;

import java.util.Random;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.OctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.aris.challenges.God;
import com.aris.challenges.HealCommand;
import com.aris.challenges.MyFirstListener;
import com.aris.aris.PlayerHandler;
import com.aris.challenges.ArisCommands;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

// Import aris scripts



public class Main extends JavaPlugin{
	public MyFirstListener myfirstl;
	public static PlayerHandler playerHandler = new PlayerHandler();
	
	@Override
	public void onEnable () {
		getLogger().info("Aris has been enabled!");
		
		// ARIS command
    	this.getCommand("aris").setExecutor(new ArisCommands());
		
		// Start myfirstl listener
		myfirstl = new MyFirstListener();
    	getServer().getPluginManager().registerEvents(myfirstl, this);
    	
    	// Start god command listener
    	this.getCommand("god").setExecutor(new God());

    	// Start heal command listener
    	this.getCommand("heal").setExecutor(new HealCommand());
	}
	
	@Override
	public void onDisable () {
		getLogger().info("Aris is offline ;(");
	}
	
	
}