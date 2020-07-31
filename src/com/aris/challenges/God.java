package com.aris.challenges;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.PluginManager;

import com.aris.aris.Main;
import com.aris.aris.PlayerData;
import com.aris.aris.PlayerHandler;
 
public class God implements CommandExecutor {
    public static PlayerData playerData;
    
 
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command!");
			return false;
		}
		Player player = (Player) sender;
		player.sendMessage("Toogling god for you " + player.getName());
		PlayerData config = Main.playerHandler.getData(player);
		if ((boolean) config.get("god")) {
			player.sendMessage("Turning god off");
			config.set("god", false);
		} else {
			player.sendMessage("Turning god on");
			config.set("god", true);
		}
		return true;
	}
	
}