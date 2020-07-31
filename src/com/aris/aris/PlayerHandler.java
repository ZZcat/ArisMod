package com.aris.aris;


import java.util.HashMap;

import org.bukkit.entity.Player;
import com.aris.aris.PlayerData;


import java.util.logging.Logger;

public class PlayerHandler {

    public static Logger log = Logger.getLogger("Minecraft");
    private HashMap<Player,PlayerData> players;
    
    public PlayerHandler () {
        //plugin = p;
        players = new HashMap<Player, PlayerData>();
    }

    public boolean playerIsRegistered(Player p) {
    	return players.containsKey(p);
    }
    
    public void registerPlayer(Player p) {
    	players.put(p, new PlayerData());
    }
    
    public PlayerData getData(Player p) {
    	if (!playerIsRegistered(p)) {
    		log.info("reging......................");
    		registerPlayer(p);
    	}
    	return players.get(p);
    }
    
}
