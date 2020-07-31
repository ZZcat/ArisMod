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

    public PlayerData getData(Player p) {
    	return players.get(p);
    }
    
    public boolean isPlayer(Player p) {
        return players.containsKey(p);
    }
}
