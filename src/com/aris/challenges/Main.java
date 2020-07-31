package com.aris.challenges;

import org.bukkit.event.Listener;

import java.util.Random;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.OctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

// Import aris scripts



public class Main extends JavaPlugin{

	@Override
	public void onEnable () {
		getLogger().info("Aris has been enabled!");
	}
	
	@Override
	public void onDisable () {
		getLogger().info("Aris is offline ;(");
	}
	
	
}