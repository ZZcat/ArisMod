package com.aris.challenges;

// From: https://pastebin.com/wpszEVJZ

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
    private Main plugin;

    public static PlayerHandler playerHandler;
    public static PlayerData playerData;
    
 
    public God() {
        //this.plugin = plugin;

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        Listener listener = new Listener() {
        };
        EventExecutor executor = new EventExecutor() {
            public void execute(Listener listener, Event event) {
                godMode(event);
            }
        };
 
        List<Class<? extends Event>> eventsToListenFor = new ArrayList<Class<? extends Event>>();
        eventsToListenFor.add(EntityDamageEvent.class);
        eventsToListenFor.add(FoodLevelChangeEvent.class);
        eventsToListenFor.add(EntityTargetEvent.class);
        eventsToListenFor.add(EntityTargetLivingEntityEvent.class);
 
        List<EventPriority> eventPrioritiesToListenOn = new ArrayList<EventPriority>();
        eventPrioritiesToListenOn.add(EventPriority.HIGHEST);
        eventPrioritiesToListenOn.add(EventPriority.LOWEST);
 
        for (Class<? extends Event> iClass : eventsToListenFor) {
            for (EventPriority iPriority : eventPrioritiesToListenOn) {
                pluginManager.registerEvent(iClass, listener, iPriority, executor, plugin, true);
            }
        }
    }
 
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("command.god")) {
        	sender.sendMessage("Lang.ERROR_COMMAND_NO_PERMISSION.get()");
            return true;
        }
        if (args.length > 0) {
            if (!sender.hasPermission("others.god")) {
            	sender.sendMessage("Lang.ERROR_COMMAND_NO_PERMISSION.get()");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
            	sender.sendMessage("Lang.ERROR_PLAYER_NOT_ONLINE.get()");
                return true;
            }
            PlayerData config = playerHandler.getData(target);
            if (!config.get("god")) {
            //if (!playerHandler.isGod(target)){
                config.set("god", true);
                target.sendMessage("Lang.GOD_ENABLED_BY.get().replace('{player}', sender.getName())");
                sender.sendMessage("Lang.GOD_ENABLED_FOR.get().replace('{player}', target.getName())");
            } else {
                config.set("god", false);
                target.sendMessage("Lang.GOD_DISABLED_BY.get().replace('{player}', sender.getName())");
                sender.sendMessage("Lang.GOD_DISABLED_FOR.get().replace('{player}', target.getName())");
            }
            config.save();
            return true;
        }
        if (!(sender instanceof Player)) {
        	sender.sendMessage("Lang.ERROR_PLAYER_COMMAND.get()");
            return true;
        }
        Player player = (Player) sender;
        PlayerData config = playerHandler.getData(player);
        if (!config.get("god")) {
            config.set("god", true);
            sender.sendMessage("Lang.GOD_ENABLED.get()");
        } else {
            config.set("god", false);
            sender.sendMessage("Lang.GOD_DISABLED.get()");
        }
        config.save();
        return true;
    }
 
    private void godMode(Event event) {
        Entity entity;
        if (event instanceof EntityTargetEvent) {
            entity = ((EntityTargetEvent) event).getTarget();
        } else {
            entity = ((EntityEvent) event).getEntity();
        }
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;
        if (! (boolean) playerHandler.getData(player).get("god")) { // May have changed incorrectly
            return;
        }
        if (event instanceof FoodLevelChangeEvent) {
            ((FoodLevelChangeEvent) event).setFoodLevel(20);
            player.setSaturation(20F);
        }
        if (event instanceof EntityTargetEvent) {
            ((EntityTargetEvent) event).setTarget(null);
        }
        ((Cancellable) event).setCancelled(true);
    }
}