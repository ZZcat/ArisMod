package com.aris.aris;


import java.util.HashMap;

import org.bukkit.entity.Player;
import com.aris.aris.PlayerData;

public class PlayerHandler {
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
    		registerPlayer(p);
    	}
    	return players.get(p);
    }
    
}
