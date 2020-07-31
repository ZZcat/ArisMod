package com.aris.aris;

import org.bukkit.event.Listener;

import java.util.Random;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.OctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.aris.challenges.God;
import com.galexz.DiamondArmorZombies.HealCommand;
import com.galexz.DiamondArmorZombies.MyFirstListener;

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
	
	@Override
	public void onEnable () {
		getLogger().info("Aris has been enabled!");
		
		myfirstl = new MyFirstListener();
    	getServer().getPluginManager().registerEvents(myfirstl, this);
    	
    	this.getCommand("god").setExecutor(new God());
	}
	
	@Override
	public void onDisable () {
		getLogger().info("Aris is offline ;(");
	}
	
	
}