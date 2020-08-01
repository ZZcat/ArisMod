package com.aris.challenges;

import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class StrongMobs implements Listener {
	@EventHandler
	public void creatureSpawnEvent(CreatureSpawnEvent event) {
		
		if (event.getEntityType() == EntityType.CREEPER) {
			
			Creeper creeper = (Creeper) event.getEntity();
			
			creeper.setPowered(true);
		}
		
		if (event.getEntityType() == EntityType.ZOMBIE) {
			
			Zombie zombie = (Zombie) event.getEntity();
			
			zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			
			ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
			sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
			
			zombie.getEquipment().setItemInMainHand(sword);
		}
	}
}
