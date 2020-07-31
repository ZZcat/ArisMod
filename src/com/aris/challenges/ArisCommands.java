package com.aris.challenges;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


class ArisCommands implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		Player player = (Player) sender;
		player.sendMessage("Hello " + player.getName() + " aris is currently loaded and active!");
        player.setHealth(20.0);  // Set full-norm player health
        player.setFoodLevel(20); // Set full-norm player food
        player.setSaturation(20);
        player.setFireTicks(0);  // Set full-norm fire
		return true;
	}
	
}
