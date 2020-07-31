package com.SpinCat360.arismod;

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
import com.SpinCat360.arismod.ArisCommands;


public class Main extends JavaPlugin{

	@Override
	public void onEnable () {
		getCommand("aris").setExecutor(new ArisCommands());
		getLogger().info("Aris has been enabled!");
	}
	
	@Override
	public void onDisable () {
		getLogger().info("Aris is offline ;(");
	}
	

	public class CustomChunkGenerator extends ChunkGenerator {
		int currentHeight = 50;

		@Override
		public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
			SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
			ChunkData chunk = createChunkData(world);
			generator.setScale(0.005D);

			for (int X = 0; X < 16; X++)
				for (int Z = 0; Z < 16; Z++) {
					currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D+50D);
					chunk.setBlock(X, currentHeight, Z, Material.GRASS);
					chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);
					for (int i = currentHeight-2; i > 0; i--)
						chunk.setBlock(X, i, Z, Material.STONE);
					chunk.setBlock(X, 0, Z, Material.BEDROCK);
				}
			return chunk;
		}
	}


	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new CustomChunkGenerator();
	}
	
}