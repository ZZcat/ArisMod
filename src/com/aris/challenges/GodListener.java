package com.aris.challenges;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.aris.aris.Main;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class GodListener implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player)event.getEntity();
        
        if((boolean) Main.playerHandler.getData(player).get("god")) {
            event.setCancelled(true);
            event.setDamage(0);
        }
        
        /*//NOFIRE:
         * if(Main.playerHandler.noFire(player)) {
            player.setFireTicks(0);
            if(event.getCause().equals(DamageCause.FIRE) || event.getCause().equals(DamageCause.FIRE_TICK))
                event.setCancelled(true);
        }
        */
    }
	
}
