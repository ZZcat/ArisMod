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
 
public class God implements CommandExecutor {
    private Main plugin;
 
    public God(Main plugin) {
        this.plugin = plugin;
        /*if (!plugin.registerCommand(this, "god")) {
            return;
        }*/
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
            plugin.sendMessage(sender, "Lang.ERROR_COMMAND_NO_PERMISSION.get()");
            return true;
        }
        if (args.length > 0) {
            if (!sender.hasPermission("others.god")) {
                plugin.sendMessage(sender, "Lang.ERROR_COMMAND_NO_PERMISSION.get()");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                plugin.sendMessage(sender, "Lang.ERROR_PLAYER_NOT_ONLINE.get()");
                return true;
            }
            PlayerConfig config = PlayerConfig.getConfig(target);
            if (!config.getBoolean("god")) {
                config.set("god", true);
                plugin.sendMessage(target, "Lang.GOD_ENABLED_BY.get().replace('{player}', sender.getName())");
                plugin.sendMessage(sender, "Lang.GOD_ENABLED_FOR.get().replace('{player}', target.getName())");
            } else {
                config.set("god", false);
                plugin.sendMessage(target, "ang.GOD_DISABLED_BY.get().replace('{player}', sender.getName())");
                plugin.sendMessage(sender, "Lang.GOD_DISABLED_FOR.get().replace('{player}', target.getName())");
            }
            config.forceSave();
            return true;
        }
        if (!(sender instanceof Player)) {
            plugin.sendMessage(sender, Lang.ERROR_PLAYER_COMMAND.get());
            return true;
        }
        Player player = (Player) sender;
        PlayerConfig config = PlayerConfig.getConfig(player);
        if (!config.getBoolean("god")) {
            config.set("god", true);
            plugin.sendMessage(sender, Lang.GOD_ENABLED.get());
        } else {
            config.set("god", false);
            plugin.sendMessage(sender, Lang.GOD_DISABLED.get());
        }
        config.forceSave();
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
        if (!PlayerConfig.getConfig(player).getBoolean("god", false)) {
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