package com.aris.challenges;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if (player.isOp()) {
				if (args.length == 0) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.setSaturation(20);
			        player.setFireTicks(0);  // Set full-norm fire
					
					for (Player players : Bukkit.getOnlinePlayers()) {
						players.sendMessage(player.getName() + " healed successfuly");
					}
				} else {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target == null) {
						player.sendMessage(args[0] + " is not online");
					} else {
						for (Player players : Bukkit.getOnlinePlayers()) {
							players.sendMessage(target.getName() + " healed successfuly");
						}
						target.setHealth(20);
					}
				}
			}
			else {
				player.sendMessage("You must be opped to use this command");
			}
		}
		else {
			sender.sendMessage("You can't use this command in the terminal");
		}
		return true;
	}
}
